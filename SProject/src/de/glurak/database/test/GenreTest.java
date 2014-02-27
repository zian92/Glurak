package de.glurak.database.test;

import de.glurak.data.Genre;
import de.glurak.database.HibernateDB;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import javax.persistence.EntityTransaction;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Eintscheider
 */
public class GenreTest {
    private HibernateDB db;
    private List<Genre> copy;
    @Before
    public void before(){
        db = new HibernateDB();
        EntityTransaction tr = db.getEnityManager().getTransaction();
        tr.begin();
        Genre pop=db.addGenre("Pop",null,tr);
        Genre rock = db.addGenre("Rock",null,tr);
        Genre hardrock = db.addGenre("Hardrock",rock,tr);
        copy = new ArrayList<Genre>();
        copy.add(pop);
        copy.add(rock);
        copy.add(hardrock);
    }

    @After
    public void after(){
        db.getEnityManager().getTransaction().rollback();
    }

    @Test
    public void hasTest(){
        assertTrue(db.hasGenre("PoP"));
        assertTrue(db.hasGenre("Rock"));
        assertTrue(db.hasGenre("Hardrock"));
        assertTrue(!db.hasGenre("Olm-Musik"));
    }

    @Test
    public void allTest(){
        List<Genre> res = db.allGenres();
        assertTrue(res.equals(copy));
    }

    @Test
    public void popTest(){
        Genre g = db.genreByTitle("poP");
        assertTrue(g.getTitle().equals("Pop"));
        assertTrue(g.getParentGenre()==null);
    }

    @Test
    public void notInTest(){
        Genre g = db.genreByTitle("OlmDerOlmer");
        assertTrue(g==null);
    }

    @Test
    public void hardRockTest(){
        Genre g = db.genreByTitle("HardRock");
        assertTrue(g.getTitle().equals("Hardrock"));
        Genre p = g.getParentGenre();
        assertTrue(p!=null);
        assertTrue(p.getTitle().equals("Rock"));
        assertTrue(p.getParentGenre()==null);
    }


}
