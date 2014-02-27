package de.glurak.database.test;

import de.glurak.data.Message;
import de.glurak.data.User.User;
import de.glurak.database.HibernateDB;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import javax.persistence.EntityTransaction;
import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
 * @author Entscheider
 */
public class MessageTest {
    private HibernateDB db;
    private User rec, sender;

    private User getOlaf() throws NoSuchAlgorithmException {
        User res = new User();
        res.setUsername("Olaf");
        res.setPassword("Oli");
        res.setLocked(false);
        return res;
    }

    private User getOlm() throws NoSuchAlgorithmException {
        User res = new User();
        res.setUsername("Olm");
        res.setPassword("Oli");
        res.setLocked(false);
        return res;
    }

    @Before
    public void before() throws NoSuchAlgorithmException {
        db=new HibernateDB();
        EntityTransaction tr = db.getEnityManager().getTransaction();
        tr.begin();
        rec= getOlaf();
        sender = getOlm();
        db.registrateUser(rec,tr);
        db.registrateUser(sender,tr);
        db.createMessage(rec,sender,"Hey alter!",false,tr);
        db.createMessage(rec,sender,"Antworte!",true,tr);
        db.createMessage(sender,rec,"Lass mich. Sonst hol ich Brüder!",false,tr);
        Message m=db.createMessage(sender,rec,"Hab es doch nicht so gemeint. Hab dich lieb.",false,tr);
        m.setAlreadyRead(true);
    }

    @After
    public void after(){
        db.getEnityManager().getTransaction().rollback();
    }

    @Test
    public void olafUnreadMessages(){
        List<Message> m=db.getUnreadMessageFromReceiver(rec);
        assertTrue(m.size()== 1);
        Message a=m.get(0);
        assertTrue(a.getMessage().equals("Lass mich. Sonst hol ich Brüder!"));
        assertTrue(a.isAlreadyRead()==false);
        assertTrue(a.isApplication()==false);
        assertTrue(a.getReceiver().getId()==rec.getId());
        assertTrue(a.getSender().getId()==sender.getId());
    }

    @Test
    public void olafMessages(){
        List<Message> m=db.messageByReceiver(rec);
        assertTrue(m.size()== 2);
        Message a=m.get(0);
        assertTrue(a.getMessage().equals("Lass mich. Sonst hol ich Brüder!"));

        Message b=m.get(1);
        assertTrue(b.getMessage().equals("Hab es doch nicht so gemeint. Hab dich lieb."));
        assertTrue(b.isAlreadyRead()==true);
        assertTrue(b.isApplication()==false);
        assertTrue(b.getReceiver().getId()==rec.getId());
        assertTrue(b.getSender().getId()==sender.getId());
    }

    @Test
    public void olmMessages(){
        List<Message> m=db.messageByReceiver(sender);
        List<Message> m2=db.getUnreadMessageFromReceiver(sender);
        assertTrue(m.equals(m2));
        assertTrue(m.size()== 2);
        Message a=m.get(0);
        assertTrue(a.getMessage().equals("Hey alter!"));
        assertTrue(a.isAlreadyRead()==false);
        assertTrue(a.isApplication()==false);
        assertTrue(a.getReceiver().getId()==sender.getId());
        assertTrue(a.getSender().getId()==rec.getId());

        Message b=m.get(1);
        assertTrue(b.getMessage().equals("Antworte!"));
        assertTrue(b.isAlreadyRead()==false);
        assertTrue(b.isApplication()==true);
        assertTrue(b.getReceiver().getId()==sender.getId());
        assertTrue(b.getSender().getId()==rec.getId());
    }
}
