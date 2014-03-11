package de.glurak.database;
import de.glurak.data.*;
import de.glurak.data.User.*;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Entscheider
 * @author dadomadi
 */
public class HibernateDB {
    private EntityManagerFactory emf = Persistence
            .createEntityManagerFactory("GlurakPersistanceUnit");
    private EntityManager em = emf.createEntityManager();

    public EntityManager getEnityManager(){
        return em;
    }

    /**
     * Gibt den Benutzer mit Benutzernamen username zurück.
     * @param username der zu suchende Benutzername
     * @return null, falls nichts gefunden, sonst den User
     */
    public User getUserByUsername(String username){
        TypedQuery<User> q1 = em.createQuery(
                "SELECT k FROM User k WHERE k.username = :n", User.class);
        q1.setParameter("n",username);
        try{
            User res = q1.getSingleResult();
            return res;
        }catch(NoResultException ex){
            return null;
        }
    }

    /**
     * Registiert ein neues Profil
     * @param p das neue Profil
     * @param ac die Transaktion die benutzt wird. Bei null wird automatisch eine neue aufgemacht
     */
    public void registrateProfile(Profile p,EntityTransaction ac){
        if (ac==null)
            em.getTransaction().begin();
         em.persist(p);
        if (ac==null)
            em.getTransaction().commit();
    }

    /**
     * Gibt das Labelprofil mit den Namen name zurück
     * @param name der Name des Profils
     * @return das LabelProfil
     */
    public LabelProfile labelProfileByName(String name){
        TypedQuery<LabelProfile> q1 = em.createQuery(
                "SELECT k FROM LabelProfile k WHERE k.name = :n", LabelProfile.class);
        q1.setParameter("n",name);
        try{
            LabelProfile res = q1.getSingleResult();
            return res;
        }catch(NoResultException ex){
            return null;
        }
    }

    /**
     * Sieht nach ob das LabelProfile mit den Namen name gibt
     * @param name der Name des Profils
     * @return true falls ex. sonst false
     */
    public boolean hasLabelProfileWithName(String name){
        TypedQuery<LabelProfile> q1 = em.createQuery(
                "SELECT k FROM LabelProfile k WHERE k.name = :n", LabelProfile.class);
        q1.setParameter("n",name);
        return q1.getResultList().size()>0;
    }

    public void registrateUser(User newUser, EntityTransaction ac){
        registrateReachable(newUser,ac);
    }

    /**
     * Registiert ein neuen Reachable, also Label oder Benutzer
     * @param newUser der neue Reachable
     * @param ac die Transaktion die benutzt wird. Bei null wird automatisch eine neue aufgemacht
     */
    public void registrateReachable(Reachable newUser, EntityTransaction ac){
        if (ac==null)
            em.getTransaction().begin();
        if (newUser.getProfile() != null){
           registrateProfile(newUser.getProfile(),ac==null?em.getTransaction():ac);
        }
        em.persist(newUser);
        if (ac==null)
            em.getTransaction().commit();
    }

    /**
     * Gibt zurück, ob es schon einen User mit den Namen gibt.
     * @param username
     * @return true falls schon einer existiert, sonst false
     */
    public boolean hasUser(String username){
        TypedQuery<User> q1 = em.createQuery(
                "SELECT k FROM User k WHERE UPPER(k.username) = UPPER(:n)", User.class);
        q1.setParameter("n",username);
        List<User> res = q1.getResultList();
        return res.size() > 0;
    }

    /**
     * Gibt alle Genres zurück
     * @return liste aller Genres
     */
    public List<Genre> allGenres(){
        TypedQuery<Genre> q1 = em.createQuery(
                "SELECT k FROM Genre k", Genre.class);
        return q1.getResultList();
    }

    /**
     * Fügt ein neues Genre hinzu
     * @param title der Title des Genres
     * @param parent der oberGenre, null falls kein Obergenre
     * @param ac die Transaktion die benutzt wird. Bei null wird automatisch eine neue aufgemacht
     * @return das neu hinzugefügte Genre
     */
    public Genre addGenre(String title, Genre parent, EntityTransaction ac){
        if (ac==null)
            em.getTransaction().begin();
        Genre g = new Genre();
        g.setTitle(title);
        g.setParentGenre(parent);
        em.persist(g);
        if (ac==null)
            em.getTransaction().commit();
        return g;
    }

    /**
     * Prüft, ob das Genre existiert
     * Groß-Kleinschreibung ist egal.
     * @param title der Title des Genres
     * @return true, falls Genre existiert. false sonst.
     */
    public boolean hasGenre(String title){
        TypedQuery<Genre> q1 = em.createQuery(
                "SELECT k FROM Genre k WHERE UPPER(k.title) = UPPER(:n)", Genre.class);
        q1.setParameter("n",title);
        return q1.getResultList().size()>0;
    }

    /**
     * Gibt das Genre mit den Title title zurück.
     * Groß-Kleinschreibung ist egal.
     * @param title der Title
     * @return null, wenn nichts gefunden, sonst das Genre
     */
    public Genre genreByTitle(String title){
        TypedQuery<Genre> q1 = em.createQuery(
                "SELECT k FROM Genre k WHERE UPPER(k.title) = UPPER(:n)", Genre.class);
        q1.setParameter("n",title);
        try{
            Genre res = q1.getSingleResult();
            return res;
        }catch(NoResultException ex){
            return null;
        }
    }

    /**
     * Gibt alle Narichten zurück, die an den User u gehen
     * @param u der User
     * @return Liste aller Narichten
     */
    public List<Message> messageByReceiver(Reachable u){
        TypedQuery<Message> q1 = em.createQuery(
                "SELECT k FROM Message k WHERE k.receiver.id = :n", Message.class);
        q1.setParameter("n",u.getId());
        return q1.getResultList();
    }

    public List<Message> getUnreadMessageFromReceiver(Reachable rec){
        TypedQuery<Message> q1 = em.createQuery(
                "SELECT k FROM Message k WHERE k.receiver.id = :n AND k.isAlreadyRead = 0", Message.class);
        q1.setParameter("n",rec.getId());
        return q1.getResultList();
    }

    /**
     * Gibt alle Narichten zurück, die vom den User u erstellt wurden
     * @param u der User
     * @return Liste aller Narichten
     */
    public List<Message> messageBySender(User u){
        TypedQuery<Message> q1 = em.createQuery(
                "SELECT k FROM Message k WHERE k.sender = :n", Message.class);
        q1.setParameter("n",u.getId());
        return q1.getResultList();
    }

    /**
     * Erstellt eine Naricht. Gesendet vom sender und zum receiver
     * @param sender der Verfasser der Naricht
     * @param receiver der Empfänger der Naricht
     * @param message die Naricht
     * @param isApplication ist es eine Bewerbung?
     * @param ac die Transaktion die benutzt wird. Bei null wird automatisch eine neue aufgemacht
     * @return die Naricht die erstellt und der Datenbank hinzugefügt worden ist
     */
    public Message createMessage(User sender, Reachable receiver, String message, boolean isApplication,EntityTransaction ac){
        if (ac==null)
            em.getTransaction().begin();
        Message m = new Message();
        m.setAlreadyRead(false);
        m.setApplication(isApplication);
        m.setSender(sender);
        m.setReceiver(receiver);
        m.setMessage(message);
        em.persist(m);
        if (ac== null)
            em.getTransaction().commit();
        return m;
    }

    /**
     * Speichert die Daten ins Dateisystem
     */
    public void save(){
        em.getTransaction().begin();
        em.getTransaction().commit();
    }

    /**
     * Gibt alle Medien von dem Benutzer u aus
     * @param u der Benutzer
     * @return alle seine Medien
     */
    public List<Medium> getMedienFromUser(User u){
        TypedQuery<Medium> q1 = em.createQuery(
                "SELECT k FROM Medium k WHERE k.owner.id = :n", Medium.class);
        q1.setParameter("n", u.getId());
        return q1.getResultList();
    }

    /**
     * Fügt eine neue Playlist hinzu
     * ACHTUNG! Jedes Medium in der Playlist (und der Nutzer natürlich) muss registriert sein in der Datenbank
     * @param list die Playlist
     * @param ac die Transaktion die benutzt wird. Bei null wird automatisch eine neue aufgemacht
     */
    public void addPlaylist(Playlist list,EntityTransaction ac){
        if (ac==null)
            em.getTransaction().begin();
        em.persist(list);
        if (ac==null)
            em.getTransaction().commit();
    }

    /**
     * Gibt alle Playlist von den Benutzer u zurück.
     * @param u der Benutzer
     * @return Liste aller seiner Playlists
     */
    public List<Playlist> getPlaylistFromListener(User u){
        TypedQuery<Playlist> q1 = em.createQuery(
                "SELECT k FROM Playlist k WHERE k.owner_of_playlist.id = :n", Playlist.class);
        q1.setParameter("n", u.getId());
        return q1.getResultList();
    }

    /**
     * Fügt das Medium in die Datenbank ein
     * @param m das Medium
     * @param tr die Transaktion die benutzt wird. Bei null wird automatisch eine neue aufgemacht.
     */
    public void registrateMedium(Medium m,EntityTransaction tr){
        if (tr==null)
            em.getTransaction().begin();
        em.persist(m);
        if (tr==null)
            em.getTransaction().commit();
    }

    /**
     * Fügt eine Ankündigung den Profil u hinzu
     * @param an die Ankündigung
     * @param p das schon registrierte Profil
     * @param tr die Transaktion die benutzt wird. Bei null wird automatisch eine neue aufgemacht.
     */
    public void addAnnouncement(Announcement an, Profile p, EntityTransaction tr){
       if (tr==null)
           em.getTransaction().begin();
        em.persist(an);
        p.addAnnouncement(an);
        if (tr==null)
            em.getTransaction().commit();
    }

    public void addNewsEntry(NewsEntry entry, EntityTransaction tr){
        if (tr==null)
            em.getTransaction().begin();
        em.persist(entry);
        if (tr == null)
            em.getTransaction().commit();
    }

    public List<NewsEntry> getAllEntries(){
        TypedQuery<NewsEntry> q1 = em.createQuery(
                "SELECT k FROM NewsEntry k", NewsEntry.class);
        return q1.getResultList();
    }

    public void removePlaylist(Playlist l, EntityTransaction tr){
        if (tr==null)
            em.getTransaction().begin();
        l.setMediumList(new ArrayList<Medium>());
        em.remove(l);
        if (tr == null)
            em.getTransaction().commit();
    }

    /**
     * Speichert und schließt die Datenbank
     * Unbedingt aufrufen, falls beendet wird!!!
     */
    public void close(){
        save();
        em.close();
        emf.close();
    }
}
