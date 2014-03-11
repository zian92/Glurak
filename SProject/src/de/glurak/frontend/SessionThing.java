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

    /**
     * Gibt die Instanz zurück. Wenn nötig wird eine neue erzeugt.
     * @return die Instanz
     */
    public static SessionThing getInstance(){
        if (instance==null){
            instance = new SessionThing();
        }
        return instance;
    }

    /**
     * Gibt die Datenbank zurück, sie wurde werden des Konstruktors erstellt
     * @return die Datenbank
     */
    public  HibernateDB getDatabase(){
        return db;
    }

    /**
     * Setzt den aktuelle User
     * @param u der aktuelle User
     */
    public void setSessionUser(User u){
        user = u;
    }

    /**
     * Gibt den aktuellen User (vor dem Bildschirm) zurück
     * @return aktuelle User
     */
    public User getSessionUser(){
        return user;
    }

    public void handleException(Exception exc){
        exc.printStackTrace();
    }
}
