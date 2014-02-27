package de.glurak.database.test;


import de.glurak.data.Genre;
import de.glurak.data.Medium;
import de.glurak.data.User.User;
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
public class MediumTest {
    private HibernateDB db;
    private User dUser;
    private Genre rock,pop,tpop;
    @Before
    public void before(){
        db = new HibernateDB();
        EntityTransaction tr = db.getEnityManager().getTransaction();
        tr.begin();
        registrateGenre(tr);
        dUser = new User();
        dUser.setUsername("Peter");
        db.registrateUser(dUser,tr);
        Medium m = new Medium();
        m.setTitel("Rocktitle");
        m.setLocked(false);
        m.setOwner(dUser);
        m.setMyGenre(rock);
        db.registrateMedium(m,tr);
        m=new Medium();
        m.setTitel("TollerTitle");
        m.setLocked(true);
        m.setOwner(dUser);
        m.setMyGenre(tpop);
        db.registrateMedium(m,tr);
        m=new Medium();
        m.setTitel("PopTitle");
        m.setOwner(dUser);
        m.setMyGenre(pop);
        db.registrateMedium(m,tr);
    }

    private void registrateGenre( EntityTransaction tr){
        pop = db.addGenre("Pop",null,tr);
        tpop=db.addGenre("Toller Pop",pop,tr);
        rock=db.addGenre("Rock",null,tr);
    }

    @After
    public void after(){
        db.getEnityManager().getTransaction().rollback();
    }

    private Medium findInList(List<Medium> l, String title){
         for (Medium m: l){
             if (m.getTitel().equals(title))
                 return m;
         }
        return null;
    }

    @Test
    public void dUserTest(){
        List<Medium> medien = db.getMedienFromUser(dUser);
        assertTrue(medien.size()==3);
        Medium a = findInList(medien,"Rocktitle");
        assertTrue(a!=null);
        assertTrue(a.isLocked()==false);
        assertTrue(a.getMyGenre().getId()==rock.getId());
        assertTrue(a.getOwner().getId()==dUser.getId());

        a=findInList(medien,"TollerTitle");
        assertTrue(a!=null);
        assertTrue(a.isLocked());
        assertTrue(a.getOwner().getId()==dUser.getId());
        assertTrue(a.getMyGenre().getId()==tpop.getId());

        a=findInList(medien,"PopTitle");
        assertTrue(a!=null);
        assertTrue(!a.isLocked());
        assertTrue(a.getOwner().getId()==dUser.getId());
        assertTrue(a.getMyGenre().getId()==pop.getId());
    }

    @Test
    public void dUserChaneTest(){

        List<Medium> medien = db.getMedienFromUser(dUser);
        assertTrue(medien.size()==3);
        Medium a = findInList(medien,"Rocktitle");
        assertTrue(a!=null);
        assertTrue(a.isLocked()==false);
        assertTrue(a.getMyGenre().getId()==rock.getId());
        assertTrue(a.getOwner().getId()==dUser.getId());
        a.setLocked(true);

        medien = db.getMedienFromUser(dUser);
        assertTrue(medien.size()==3);
        a = findInList(medien,"Rocktitle");
        assertTrue(a!=null);
        assertTrue(a.isLocked());
        assertTrue(a.getMyGenre().getId()==rock.getId());
        assertTrue(a.getOwner().getId()==dUser.getId());

        a=findInList(medien,"TollerTitle");
        assertTrue(a!=null);
        assertTrue(a.isLocked());
        assertTrue(a.getOwner().getId()==dUser.getId());
        assertTrue(a.getMyGenre().getId()==tpop.getId());

        a=findInList(medien,"PopTitle");
        assertTrue(a!=null);
        assertTrue(!a.isLocked());
        assertTrue(a.getOwner().getId()==dUser.getId());
        assertTrue(a.getMyGenre().getId()==pop.getId());
    }
}
