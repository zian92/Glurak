package de.glurak.database.test;

import de.glurak.data.User.ArtistProfile;
import de.glurak.data.User.User;
import de.glurak.database.HibernateDB;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityTransaction;
import java.security.NoSuchAlgorithmException;

import static org.junit.Assert.assertTrue;

/**
 * @author Entscheider
 * @author dadomadi
 */
public class Usertest {
    private HibernateDB db;

    @Before
    public void beforeAll() throws NoSuchAlgorithmException {
        db = new HibernateDB();
        EntityTransaction ac = db.getEnityManager().getTransaction();
        ac.begin();
        User user1 = new User();
        ArtistProfile a = new ArtistProfile();
        user1.setLocked(false);
        user1.setUsername("Olaf");
        user1.setPassword("MyOlaf");
        db.registrateProfile(a,ac);
        user1.setProfile(a);
        User user2 = new User();
        a = new ArtistProfile();
        db.registrateProfile(a,ac);
        user2.setProfile(a);
        user2.setLocked(true);
        user2.setUsername("Olm");
        user2.setPassword("123");
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
