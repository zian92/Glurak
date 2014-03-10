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
import java.util.List;

/**
 * Created by rnr on 06.03.14.
 */
public class DBSearch {
    private HibernateDB db;
    private EntityManager em;
    public DBSearch(HibernateDB db){
        this.db = db;
        em = db.getEnityManager();
    }

    public List<Genre> searchForGenre(String g){
        TypedQuery<Genre> q1 = em.createQuery(
                "SELECT k FROM Genre k WHERE UPPER(k.title) Like UPPER('%"+g+"%')", Genre.class);
        //q1.setParameter("n",g);
        return q1.getResultList();
    }

    public List<Medium> searchForMusicByTitle(String title){
        TypedQuery<Medium> q1 = em.createQuery(
                "SELECT k FROM Medium k WHERE UPPER(k.titel) Like UPPER('%"+title+"%')", Medium.class);
        //q1.setParameter("n",g);
        return q1.getResultList();
    }

    public List<Medium> searchForMusicByInterpret(String name){
        List<User> userList = searchForUserByUsername(name);
        List<Medium> res = new ArrayList<Medium>();
        for (User u : userList){
            List<Medium> other =db.getMedienFromUser(u);
            res.addAll(other);
        }
        return res;
    }

    public List<User> searchForUserByUsername(String name){
        TypedQuery<User> q1 = em.createQuery(
                "SELECT k FROM User k WHERE UPPER(k.username) Like UPPER('%"+name+"%')" , User.class);
        //q1.setParameter("n",g);
        return q1.getResultList();
    }

    public List<UserProfile> searchForProfileByName(String firstname){
        TypedQuery<UserProfile> q1 = em.createQuery("SELECT k FROM UserProfile k WHERE UPPER(CONCAT(k.firstname,' ',k.lastname)) Like UPPER('%"+firstname+"%')" , UserProfile.class);
        return q1.getResultList();
    }

    public List<User> searchUserBySomething(String something){
        List<User> l = searchForUserByUsername(something);
        List<UserProfile> other = searchForProfileByName(something);
        List<User> other_user = new ArrayList<User>();
        for (UserProfile prof: other){
            other_user.add(prof.belongTo());
        }
        l.addAll(other_user);
        return l;
    }

    public List<Playlist> searchPlaylistByName(String name){
            TypedQuery<Playlist> q1 = em.createQuery(
                    "SELECT k FROM Playlist k WHERE UPPER(k.name) Like UPPER('%"+name+"%')", Playlist.class);
            //q1.setParameter("n",g);
            return q1.getResultList();
    }

    public List<LabelProfile> searchLabelByName(String name){
        TypedQuery<LabelProfile> q1 = em.createQuery(
                "SELECT k FROM LabelProfile k WHERE UPPER(k.name) Like UPPER('%"+name+"%')", LabelProfile.class);
        //q1.setParameter("n",g);
        return q1.getResultList();
    }
}
