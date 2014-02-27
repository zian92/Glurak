package de.glurak.database;
import de.glurak.data.Genre;
import de.glurak.data.Medium;
import de.glurak.data.Message;
import de.glurak.data.Playlist;
import de.glurak.data.User.Profile;
import de.glurak.data.User.User;

import javax.persistence.*;
import java.util.List;

/**
 *
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
     * Registiert ein neuen User. Das Profil wird automatisch mit gespeichert
     * @param newUser der neue Benutzer
     * @param ac die Transaktion die benutzt wird. Bei null wird automatisch eine neue aufgemacht
     */
    public void registrateUser(User newUser, EntityTransaction ac){
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
     * @return
     */
    public boolean hasUser(String username){
        TypedQuery<User> q1 = em.createQuery(
                "SELECT k FROM User k WHERE k.username = :n", User.class);
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
    public List<Message> messageByReceiver(User u){
        TypedQuery<Message> q1 = em.createQuery(
                "SELECT k FROM Message k WHERE k.receiver.id = :n", Message.class);
        q1.setParameter("n",u.getId());
        return q1.getResultList();
    }

    public List<Message> getUnreadMessageFromReceiver(User rec){
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
    public Message createMessage(User sender, User receiver, String message, boolean isApplication,EntityTransaction ac){
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
    /*
    public void setMessageRead(Message m){
        //TODO: Ist automatisch gesetzt?
    } */


    public void createTestData(){
        Medium m = new Medium();
        m.setOwner(null);
        m.setFileName("olaf.mp3");
        m.setTitel("Der OlagSong von Olaf.");

        Playlist p = new Playlist();
        p.addMedium(m);

        Medium m2= new Medium();
        m2.setOwner(null);
        m2.setFileName("olaf2.mp3");
        m2.setTitel("Olaf kommt zurück");
        p.addMedium(m2);

        em.getTransaction().begin();
        em.persist(m);em.persist(m2);
        em.persist(p);
        em.getTransaction().commit();
    }

    public void checkTestData(){
        TypedQuery<Playlist> q1 = em.createQuery(
                "SELECT k FROM Playlist k ", Playlist.class);
        List<Playlist> contacts = q1.getResultList();
        if (contacts.isEmpty()){System.out.println("??");}
        for (Playlist p: contacts){
            for (Medium m: p.getMediumList()){
                System.out.println(m.getFileName());
            }
        }
    }

    /**
     * Speichert die Daten ins Dateisystem
     * Unbedingt aufrufen, falls beendet wird!!!
     */
    public void save(){
        em.close();
        emf.close();
    }


    public static void main(String[] args){
        HibernateDB db = new HibernateDB();
        db.createTestData();
        db.checkTestData();
        db.save();
    }
}
