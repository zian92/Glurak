package de.glurak.database.test;

import de.glurak.data.Message;
import de.glurak.data.User.ListenerProfile;
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
 * @author dadomadi
 */
public class MessageTest {
    private HibernateDB db;
    private User rec, sender;

    private User getReceiver(EntityTransaction tr) throws NoSuchAlgorithmException {
        User res = new User();
        ListenerProfile p = new ListenerProfile();
        db.registrateProfile(p,tr);
        res.setUsername("Receiver");
        res.setPassword("Oli");
        res.setLocked(false);
        res.setProfile(p);
        db.registrateUser(res,tr);
        return res;
    }

    private User getSender(EntityTransaction tr) throws NoSuchAlgorithmException {
        User res = new User();
        res.setUsername("Sender");
        res.setPassword("Oli");
        res.setLocked(false);
        ListenerProfile p = new ListenerProfile();
        res.setProfile(p);
        db.registrateProfile(p,tr);
        db.registrateUser(res,tr);
        return res;
    }

    @Before
    public void before() throws NoSuchAlgorithmException {
        db=new HibernateDB();
        EntityTransaction tr = db.getEnityManager().getTransaction();
        tr.begin();
        rec= getReceiver(tr);
        sender = getSender(tr);
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
    public void recUnreadMessages(){
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
    public void receiverMessages(){
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
    public void senderMessages(){
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
