package de.glurak.frontend.mainFrame.content.message;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import de.glurak.data.Message;
import de.glurak.data.User.User;
import de.glurak.database.HibernateDB;
import de.glurak.frontend.SessionThing;
import de.glurak.frontend.mainFrame.ContentController;

/**
 * Der Kontroller fuer die Messageview.
 * 
 * @author Simon
 * 
 */
public class MessageVController implements ActionListener, ContentController {

    private MessageView messview;
    private String errorMsgBoxName = "Fehlermeldung";

    /**
     * Konstruktor
     */
    public MessageVController() {
        ListSelectionListener s = new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                for (int k = e.getFirstIndex(); k<=e.getLastIndex();k++){
                     Message m = messview.messageAtPos(k);
                    if (m!=null)
                        m.setAlreadyRead(true);
                }
            }
        };
        messview = new MessageView(this,s);
    }

    /**
     * Prüft ob das temporäre Message-Objekt m gütige Angaben besitzt.
     * Außerdem wird dem Nutzer noch Dialoge angezeigt.
     * @param m die Message
     * @return true falls alles OK, sonst false
     */
    private boolean checkIfMessageValid(Message m){
        SessionThing s = SessionThing.getInstance();
        HibernateDB db = s.getDatabase();
        User u = (User) m.getReceiver();
        if (u.getUsername().trim().isEmpty()){
            JOptionPane.showMessageDialog(messview, "Sie haben noch keinen Empfänger eingegeben. Bitte fügen sie einen Empfänger hinzu!", errorMsgBoxName, JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (!db.hasUser(u.getUsername())){
            JOptionPane.showMessageDialog(messview, "Dieser Empfänger existiert nicht. Bitte geben sie einen existierenden Empfänger an!", errorMsgBoxName, JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (s.getSessionUser().getUsername().equals(u.getUsername())){
            JOptionPane.showMessageDialog(messview, "Du kannst dir nicht selber eine Nachricht schreiben. Bitte geben sie einen anderen Empfänger an!", errorMsgBoxName, JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (m.getMessage().trim().isEmpty()) {
            JOptionPane.showMessageDialog(messview, "Sie haben keine Nachricht eingegeben. Bitte schreiben sie zuerst ihre Nachricht!", errorMsgBoxName, JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("send")){
            SessionThing s = SessionThing.getInstance();
            HibernateDB db = s.getDatabase();
            Message m = messview.getEnteredMessage();
            if (!checkIfMessageValid(m)) return;
            User u = (User) m.getReceiver();
            User realUser = db.getUserByUsername(u.getUsername());
            db.createMessage(s.getSessionUser(),realUser,m.getMessage(),false,null);
            messview.setEnteredMessage(null);
        }
        if (e.getActionCommand().equals("cancel")){
            messview.setEnteredMessage(null);
        }
    }

    /**
     * @return Gibt das Messageviewpanel zurueck.
     */
    public JPanel getView() {
        return messview;
    }

}
