package de.glurak.database.test;

import de.glurak.data.Medium;
import de.glurak.data.Playlist;
import de.glurak.data.User.ArtistProfile;
import de.glurak.data.User.User;
import org.junit.Before;
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
 * @author dadomadi
 */
public class PlaylistTest {
    private HibernateDB db;

    private User u1, u2;

    @Before
    public void before(){
        db=new HibernateDB();
        EntityTransaction t =db.getEnityManager().getTransaction();
        t.begin();
        u1 = new User();
        u1.setUsername("falO");
        ArtistProfile p = new ArtistProfile();
        db.registrateProfile(p,t);
        u1.setProfile(p);
        db.registrateUser(u1,t);

        u2 = new User();
        u2.setUsername("mlO");
        p = new ArtistProfile();
        db.registrateProfile(p,t);
        u2.setProfile(p);
        db.registrateUser(u2,t);

        Medium m1=new Medium();
        m1.setTitel("Schmusesongs von falO");
        m1.setOwner(u1);
        db.registrateMedium(m1,t);
        Medium m2 = new Medium();
        m2.setTitel("Duett von falO und mlO");
        m2.setOwner(u1);
        db.registrateMedium(m2,t);
        Medium m3 = new Medium();
        m3.setTitel("Just Hatify");
        m3.setOwner(u2);
        db.registrateMedium(m3,t);

        Playlist l = new Playlist();
        l.addMedium(m1);
        l.addMedium(m2);
        l.setOwner(u1);
        l.setName("Die falO Kollektion");
        l.hate(u2);
        db.addPlaylist(l,t);
        //u1.getMyPlaylists().add(l);

        Playlist l2=new Playlist();
        l2.setName("The Hate List");
        l2.addMedium(m3);
        l2.setOwner(u2);
        l2.like(u1);
        db.addPlaylist(l2,t);
        //u2.getMyPlaylists().add(l2);

    }

    @After
    public void after(){
       db.getEnityManager().getTransaction().rollback();
    }

    @Test
    public void testPlaylist1(){
        List<Playlist> list =db.getPlaylistFromListener(u1);
        assertTrue(list.size()==1);
        Playlist l = list.get(0);
        List<Medium> s = l.getMediumList();
        assertTrue(l.getName().equals("Die falO Kollektion"));
        assertTrue(l.getOwner().equals(u1));
        assertTrue(s.size()==2);
        Medium a = s.get(0);
        assertTrue(a.getTitel().equals("Schmusesongs von falO"));
        assertTrue(a.getOwner().equals(u1));

        assertTrue(l.hateCount()==1);
        assertTrue(l.getHater().get(0).equals(u2));
        assertTrue(l.likeCount()==0);

        a= s.get(1);
        assertTrue(a.getTitel().equals("Duett von falO und mlO"));
        assertTrue(a.getOwner().equals(u1));

        l.setName("DI");
        list = db.getPlaylistFromListener(u1);
        assertTrue(list.get(0).getName().equals("DI"));
    }

    @Test
    public void testPlaylist2(){
        List<Playlist> list =db.getPlaylistFromListener(u2);
        assertTrue(list.size()==1);
        Playlist l = list.get(0);
        List<Medium> s = l.getMediumList();
        assertTrue(l.getName().equals("The Hate List"));
        assertTrue(l.getOwner().equals(u2));
        assertTrue(s.size()==1);

        assertTrue(l.likeCount()==1);
        assertTrue(l.getLiker().get(0).equals(u1));
        assertTrue(l.hateCount()==0);

        Medium a = s.get(0);
        assertTrue(a.getTitel().equals("Just Hatify"));
        assertTrue(a.getOwner().equals(u2));
    }
}
