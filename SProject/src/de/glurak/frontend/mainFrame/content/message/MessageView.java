package de.glurak.frontend.mainFrame.content.message;

import de.glurak.data.Message;
import de.glurak.database.HibernateDB;
import de.glurak.frontend.SessionThing;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.*;

/**
 * MessageView zeigt das Fenster an, wenn man einen anderen User des Systems eine Nachricht schreiben moechte.
 * 
 * @author Entscheider
 * 
 */
public class MessageView extends JPanel {
    private MessageViewWriter writer;
    private MessageViewList list;
    private DefaultListModel<Message> model;

    /**
     * Konstruktor
     * @param l der Listener für die Knöpfe im MessageViewWriter, null für keinen
     */
    public MessageView(ActionListener l){
        writer = new MessageViewWriter(l);
        setLayout(new BorderLayout());
        add(writer, BorderLayout.SOUTH);
        list = new MessageViewList();
        initModel();
        list.setModel(model);
        add(new JScrollPane(list), BorderLayout.CENTER);
    }

    private void initModel(){
        model = new DefaultListModel<Message>();
        HibernateDB db = SessionThing.getInstance().getDatabase();
        List<Message> myMessage = db.getUnreadMessageFromReceiver(SessionThing.getInstance().getSessionUser());
        for (Message m: myMessage){
            model.addElement(m);
        }
    }

    /**
     * Gibt die Naricht zurück. Diese Message ist temporär und
     * nicht in der Datenbank. Sie ist dafür da sofort wieder gelöscht zu werden
     * @return die Naricht
     */
    public Message getEnteredMessage(){
        return writer.createMessage();
    }

    /**
     * Setzt die eingeben Naricht
     * @param m die Naricht, null für ein leeres Feld
     */
    public void setEnteredMessage(Message m){
        writer.setMessage(m);
    }
}
