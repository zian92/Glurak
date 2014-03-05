package de.glurak.database.test;

import static org.junit.Assert.assertTrue;

import javax.persistence.EntityTransaction;

import de.glurak.data.User.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.glurak.database.HibernateDB;
/**
 * @author Entscheider
 * @author dadomadi
 */
public class ProfileTest {
    private HibernateDB db;
    private User user1,artist;
    private ArtistProfile artistProfile;
    private Label l;
    @Before
    public void before(){
        db = new HibernateDB();
        EntityTransaction tr = db.getEnityManager().getTransaction();
        tr.begin();

        user1 = new User();
        user1.setUsername("Olaf der Schneemann");
        db.registrateUser(user1, tr);

        LabelProfile lb = new LabelProfile();
        lb.setName("Shelden");
        LabelManagerProfile pr = new LabelManagerProfile();
        l = new Label();
        db.registrateReachable(l,tr);
        pr.setMyLabel(l);
        pr.setFirstname("Schnee");
        pr.setLastname("Mann");
        db.registrateProfile(lb,tr);
        l.setProfile(lb);
        db.registrateProfile(pr, tr);

        user1.setProfile(pr);

        artist = new User();
        artistProfile=new ArtistProfile();
        artist.setUsername("Artist");
        db.registrateUser(artist,tr);
        db.registrateProfile(artistProfile,tr);
        artist.setProfile(artistProfile);
        artistProfile.setMyLabel(lb);
    }


    @After
    public void after(){
        db.getEnityManager().getTransaction().rollback();
    }

    @Test
    public void aritstTest(){
        User a = db.getUserByUsername("Artist");
        assertTrue(a!=null);
        assertTrue(a==artist);
        UserProfile p = a.getProfile();
        assertTrue(p!=null);
        assertTrue(p.roleName().equals("Artist"));
        ArtistProfile aProfile = (ArtistProfile) p;
        assertTrue(aProfile.getMyLabel()!=null);
        assertTrue(aProfile.getMyLabel().getMyLabel()==l);
    }

    @Test
    public void labelTest(){
        User a = db.getUserByUsername("Olaf der Schneemann");
        assertTrue(a==user1);
        Profile p =a.getProfile();
        assertTrue(p.roleName().equals("LabelManager"));
        LabelManagerProfile pp = (LabelManagerProfile)  p;

        LabelProfile lp = db.labelProfileByName("Shelden") ;
        assertTrue(lp.getId() == pp.getMyLabel().getProfile().getId());
        assertTrue(lp.getName().equals("Shelden"));
        assertTrue(lp.getMyLabel()!=null);
        assertTrue(lp.getMyLabel().getId()==l.getId());

        lp.setName("Sh");
        lp = db.labelProfileByName("Sh");
        assertTrue(lp.getName().equals("Sh"));
        assertTrue(pp.getMyLabel().getProfile().getName().equals("Sh"));
    }

    @Test
    public void removeLabelLabelManagerTest1(){
        User a = db.getUserByUsername("Olaf der Schneemann");
        Profile p =a.getProfile();
        LabelManagerProfile pp = (LabelManagerProfile)  p;
        LabelProfile lp = db.labelProfileByName("Shelden") ;

        assertTrue(lp.getMyLabel().getManager().contains(pp));

        lp.getMyLabel().removeLabelManager(pp);
        assertTrue(!lp.getMyLabel().getManager().contains(pp));
        assertTrue(pp.getMyLabel()==null);
    }

    @Test
    public void removeLabelLabelManagerTest2(){
        User a = db.getUserByUsername("Olaf der Schneemann");
        Profile p =a.getProfile();
        LabelManagerProfile pp = (LabelManagerProfile)  p;
        LabelProfile lp = db.labelProfileByName("Shelden") ;

        assertTrue(lp.getMyLabel().getManager().contains(pp));

        pp.setMyLabel(null);
        assertTrue(!lp.getMyLabel().getManager().contains(pp));
        assertTrue(pp.getMyLabel()==null);
    }

    @Test
    public void removeArtistLabelTest1(){
        User a = db.getUserByUsername("Artist");
        UserProfile p = a.getProfile();
        ArtistProfile aProfile = (ArtistProfile) p;
        assertTrue(l.getProfile().getMyartists().contains(aProfile));
        aProfile.setMyLabel(null);

        assertTrue(aProfile.getMyLabel()==null);
        assertTrue(!l.getProfile().getMyartists().contains(aProfile));
    }

    @Test
    public void removeArtistLabelTest2(){
        User a = db.getUserByUsername("Artist");
        UserProfile p = a.getProfile();
        ArtistProfile aProfile = (ArtistProfile) p;
        assertTrue(l.getProfile().getMyartists().contains(aProfile));
        aProfile.getMyLabel().removeArtist(aProfile);

        assertTrue(aProfile.getMyLabel()==null);
        assertTrue(!l.getProfile().getMyartists().contains(aProfile));
    }

    @Test
    public void existsTest(){
        assertTrue(db.hasLabelProfileWithName("Shelden"));
        assertTrue(!db.hasLabelProfileWithName("Keine Ahnung"));
    }
}
