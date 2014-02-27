package de.glurak.database.test;

import de.glurak.data.User.User;
import de.glurak.database.HibernateDB;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityTransaction;
import javax.swing.text.html.parser.Entity;

import static org.junit.Assert.*;

import java.security.NoSuchAlgorithmException;

/**
 * @author Entscheider
 */
public class Usertest {
    private HibernateDB db;

    @Before
    public void beforeAll() throws NoSuchAlgorithmException {
        db = new HibernateDB();
        User user1 = new User();
        user1.setLocked(false);
        user1.setUsername("Olaf");
        user1.setPassword("MyOlaf");
        User user2 = new User();
        user2.setLocked(true);
        user2.setUsername("Olm");
        user2.setPassword("123");
        EntityTransaction ac = db.getEnityManager().getTransaction();
        ac.begin();
        db.registrateUser(user1,ac);
        db.registrateUser(user2,ac);
    }

    @After
    public void afterTest(){
        db.getEnityManager().getTransaction().rollback();
    }

    @Test
    public void checkUser1Test() throws NoSuchAlgorithmException {
        User u =db.getUserByUsername("Olaf");
        assertTrue(u.getUsername().equals("Olaf"));
        assertTrue(u.checkPassword("MyOlaf"));
        assertTrue(!u.isLocked());
    }

    @Test
    public void checkHasUser(){
        assertTrue(db.hasUser("Olaf"));
        assertTrue(db.hasUser("Olm"));
        assertTrue(!db.hasUser("WhoDoYouMean?"));
    }

    @Test
    public void checkUser2Test() throws NoSuchAlgorithmException {
        User u =db.getUserByUsername("Olm");
        assertTrue(u.getUsername().equals("Olm"));
        assertTrue(u.checkPassword("123"));
        assertTrue(u.isLocked());
    }

    @Test
    public void noUserTest(){
        User u = db.getUserByUsername("NooneWhoExists");
        assertTrue(u==null);
    }

}
