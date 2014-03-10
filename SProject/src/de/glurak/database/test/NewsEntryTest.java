package de.glurak.database.test;

import java.util.List;
import de.glurak.data.Medium;
import de.glurak.data.NewsEntry;
import de.glurak.database.HibernateDB;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import javax.persistence.EntityTransaction;

/**
 * @author Entscheider
 */
public class NewsEntryTest {
    private  HibernateDB db;
    @Before
    public void before(){
        db=new HibernateDB();
        EntityTransaction tr = db.getEnityManager().getTransaction();
        tr.begin();

        NewsEntry a = new NewsEntry();
        Medium m = new Medium();
        m.setTitel("Hi");
        db.registrateMedium(m,tr);
        a.setMessage("Hi");
        a.setSource(m);
        db.addNewsEntry(a,tr);
    }

    @After
    public void after(){
       db.getEnityManager().getTransaction().rollback();
    }
    @Test
    public void test(){
       List<NewsEntry> res = db.getAllEntries();
       assertTrue(res.size()==1);
       NewsEntry a = res.get(0);
       assertTrue(a.getSource()!=null);
    }
}
