package de.glurak.database.test;

import de.glurak.data.Announcement;
import de.glurak.data.User.*;
import de.glurak.database.HibernateDB;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityTransaction;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;

/**
 * @author Entscheider
 */
public class AnnouncementTest {
    private HibernateDB db;
    private User u1;
    private User u2;
    private Label l1;
    @Before
    public void before(){
        db = new HibernateDB();
        EntityTransaction tr = db.getEnityManager().getTransaction();
        tr.begin();
        u1=new User();
        u2=new User();
        u1.setUsername("User1");
        u2.setUsername("User2");
        db.registrateUser(u1,tr);
        db.registrateUser(u2,tr);

        LabelManagerProfile u1_prof = new LabelManagerProfile();
        db.registrateProfile(u1_prof,tr);
        u1.setProfile(u1_prof);


        ArtistProfile u2_prof = new ArtistProfile();
        db.registrateProfile(u2_prof,tr);
        u2.setProfile(u2_prof);

        LabelProfile l_prof = new LabelProfile();
        l1=new Label();
        db.registrateReachable(l1,tr);
        db.registrateProfile(l_prof, tr);
        l1.setProfile(l_prof);
        u1_prof.setMyLabel(l1);
        //l1.addLabelManager(u1_prof);

        Announcement a = new Announcement();
        a.setTitle("Der Peter sagt:");
        a.setContent("Peter ist heute gut drauf");
        db.addAnnouncement(a, u2_prof, tr);

        a = new Announcement();
        a.setTitle("Der Peter ruft:");
        a.setContent("Peter ist lustig");
        db.addAnnouncement(a,u2_prof,tr);

        a = new Announcement();
        a.setTitle("Das Label sagt:");
        a.setContent("Auf den Strich zähl ich!");
        db.addAnnouncement(a,l_prof,tr);
    }

    @After
    public void after(){
        db.getEnityManager().getTransaction().rollback();
    }

    @Test
    public void testLabel(){
        User u = db.getUserByUsername("User1") ;
        assertTrue(u!=null);
        assertTrue(u.getId()==u1.getId());
        assertTrue(u.getId()!=u2.getId());
        assertTrue(u.getProfile()!=null);
        Profile p = u.getProfile();
        assertTrue(p.roleName().equals("LabelManager"));
        assertTrue(p.getAnnouncements().size()==0); //Das Profil des Labelmanager hat keine Ankündigungen
        LabelManagerProfile l_prof = (LabelManagerProfile) p;
        assertTrue(l_prof.getMyLabel()!=null);
        assertTrue(l_prof.getMyLabel().getManager().size()==1);
        assertTrue(l_prof.getMyLabel().getProfile()!=null);
        p = l_prof.getMyLabel().getProfile(); //Das Profils des Labels
        assertTrue(p.getAnnouncements().size()==1);
        Announcement a = p.getAnnouncements().get(0);
        assertTrue(a.getBelongsTo()!=null);
        assertTrue(a.getBelongsTo().getId()==p.getId());
        assertTrue(a.getTitle().equals("Das Label sagt:"));
        assertTrue(a.getContent().equals("Auf den Strich zähl ich!"));
    }

    @Test
    public void testArtist(){
        User u = db.getUserByUsername("User2");
        assertTrue(u!=null);
        assertTrue(u.getId()==u2.getId());
        assertTrue(u.getId()!=u1.getId());
        assertTrue(u.getProfile()!=null);
        Profile p = u.getProfile();
        assertTrue(p.roleName().equals("Artist"));
        assertTrue(p.getAnnouncements()!=null);
        assertTrue(p.getAnnouncements().size()==2);
        assertTrue(p.getAnnouncements().get(0).getTitle().equals("Der Peter sagt:"));

        Announcement announce_2= p.getAnnouncements().get(1);
        assertTrue(announce_2.getTitle().equals("Der Peter ruft:"));
        assertTrue(announce_2.getBelongsTo()!=null);
        assertTrue(announce_2.getBelongsTo().getId()==u.getId());
        assertTrue(announce_2.getContent().equals("Peter ist lustig"));
    }
}