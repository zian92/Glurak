package de.glurak.frontend;

import de.glurak.data.User.User;
import de.glurak.database.HibernateDB;

import java.security.NoSuchAlgorithmException;

/**
 * Diese Klasse ist ein Singleton. Hier werden für eine Sitzung die Daten gespeichert,
 * die immer gebraucht werden
 * @author Entscheider
 */
public class SessionThing {
    private User user;
    private static SessionThing instance=null;
    private HibernateDB db;

    private SessionThing(){
        db = new HibernateDB();
    }
    public static SessionThing getInstance(){
        if (instance==null){
            instance = new SessionThing();
        }
        return instance;
    }

    public  HibernateDB getDatabase(){
        return db;
    }

    public void setSessionUser(User u){
        user = u;
    }

    public User getSessionUser(){
        return user;
    }

    public void handleException(Exception exc){
        //TODO: implement
    }
}
