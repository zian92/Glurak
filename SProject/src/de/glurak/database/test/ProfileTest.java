package de.glurak.database.test;

import de.glurak.data.User.*;
import de.glurak.database.HibernateDB;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityTransaction;

import static org.junit.Assert.assertTrue;
/**
 * @author Entscheider
 * @author dadomadi
 */
public class ProfileTest {
    private HibernateDB db;
    private User user1;
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
        Label l = new Label();
        db.registrateReachable(l,tr);
        pr.setMyLabel(l);
        pr.setFirstname("Schnee");
        pr.setLastname("Mann");
        db.registrateProfile(lb,tr);
        l.setProfile(lb);
        db.registrateProfile(pr, tr);

        user1.setProfile(pr);
    }


    @After
    public void after(){
        db.getEnityManager().getTransaction().rollback();
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

        lp.setName("Sh");
        lp = db.labelProfileByName("Sh");
        assertTrue(lp.getName().equals("Sh"));
        assertTrue(pp.getMyLabel().getProfile().getName().equals("Sh"));
    }

    @Test
    public void existsTest(){
        assertTrue(db.hasLabelProfileWithName("Shelden"));
        assertTrue(!db.hasLabelProfileWithName("Keine Ahnung"));
    }
}
