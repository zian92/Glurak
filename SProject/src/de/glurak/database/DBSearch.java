package de.glurak.database;

import de.glurak.data.Genre;
import de.glurak.data.Medium;
import de.glurak.data.Message;
import de.glurak.data.Playlist;
import de.glurak.data.User.Label;
import de.glurak.data.User.LabelProfile;
import de.glurak.data.User.User;
import de.glurak.data.User.UserProfile;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Entscheider
 */
public class DBSearch {
    private HibernateDB db;
    private EntityManager em;

    /**
     * Konstukor
     * @param db die HibernateDB die die Verbindung hält
     */
    public DBSearch(HibernateDB db){
        this.db = db;
        em = db.getEnityManager();
    }

    /**
     * Suche die Genre nach Namen
     * @param g  der Name
     * @return die gefundenden Genres
     */
    public List<Genre> searchForGenre(String g){
        TypedQuery<Genre> q1 = em.createQuery(
                "SELECT k FROM Genre k WHERE UPPER(k.title) Like UPPER('%"+g+"%')", Genre.class);
        //q1.setParameter("n",g);
        return q1.getResultList();
    }

    /**
     * Suche ein Medium nach sein Titel
     * @param title der Titel des zu suchenden Mediums
     * @return die gefundenden Medien
     */
    public List<Medium> searchForMusicByTitle(String title){
        TypedQuery<Medium> q1 = em.createQuery(
                "SELECT k FROM Medium k WHERE UPPER(k.titel) Like UPPER('%"+title+"%')", Medium.class);
        //q1.setParameter("n",g);
        return q1.getResultList();
    }


    public List<Medium> searchForMusicByGenre(String genreTitle){
        TypedQuery<Medium> q1 = em.createQuery(
                "SELECT k FROM Medium k WHERE UPPER(k.myGenre.title) Like UPPER('%"+genreTitle+"%')", Medium.class);
        //q1.setParameter("n",g);
        return q1.getResultList();
    }

    /**
     * Sucht die Musik nach den Interpreter, derjenige der sie hochgeladen hat
     * @param name der Name (username oder echte Name)
     * @return die gefundenden Medien
     */
    public List<Medium> searchForMusicByInterpret(String name){
        List<User> userList = searchUserBySomething(name);
        List<Medium> res = new ArrayList<Medium>();
        for (User u : userList){
            List<Medium> other =db.getMedienFromUser(u);
            res.addAll(other);
        }
        return res;
    }

    /**
     * Sucht den User nach seinen Benutzernamen
     * @param name der Benutzername
     * @return die gefundenden Benutzer
     */
    public List<User> searchForUserByUsername(String name){
        TypedQuery<User> q1 = em.createQuery(
                "SELECT k FROM User k WHERE UPPER(k.username) Like UPPER('%" + name + "%')", User.class);
        //q1.setParameter("n",g);
        return q1.getResultList();
    }

    /**
     * Sucht ein Benutzerprofil nach seinen Namen
     * @param name der echte Name
     * @return die gefundenden Userprofile
     */
    public List<UserProfile> searchForProfileByName(String name){
        TypedQuery<UserProfile> q1 = em.createQuery("SELECT k FROM UserProfile k WHERE UPPER(CONCAT(k.firstname,' ',k.lastname)) Like UPPER('%" + name + "%')", UserProfile.class);
        return q1.getResultList();
    }

    /**
     * Suche den User nach seinen Usernamen oder seinen echten Namen
     * @param something irgendetwas vom oben genannten
     * @return die Liste aller User die gefunden wurden
     */
    public List<User> searchUserBySomething(String something){
        List<User> l = searchForUserByUsername(something);
        List<UserProfile> other = searchForProfileByName(something);
        Set<User> other_user = new HashSet<User>();
        for (UserProfile prof: other){
            if (prof.belongTo()!=null)
             other_user.add(prof.belongTo());
        }
        other_user.addAll(l);
        l.addAll(other_user);
        ArrayList<User> res = new ArrayList<User>();
        res.addAll(other_user);
        return res;
    }

    /**
     * Sucht eine Playlist nach ihren Namen
     * @param name der Name der Playlist
     * @return alle gefundende Playlists
     */
    public List<Playlist> searchPlaylistByName(String name){
            TypedQuery<Playlist> q1 = em.createQuery(
                    "SELECT k FROM Playlist k WHERE UPPER(k.name) Like UPPER('%"+name+"%')", Playlist.class);
            //q1.setParameter("n",g);
            return q1.getResultList();
    }

    /**
     * Sucht ein Label nach Name
     * @param name der Name des Labels
     * @return die gefundenden LabelProfile
     */
    public List<LabelProfile> searchLabelByName(String name){
        TypedQuery<LabelProfile> q1 = em.createQuery(
                "SELECT k FROM LabelProfile k WHERE UPPER(k.name) Like UPPER('%"+name+"%')", LabelProfile.class);
        //q1.setParameter("n",g);
        return q1.getResultList();
    }
}
