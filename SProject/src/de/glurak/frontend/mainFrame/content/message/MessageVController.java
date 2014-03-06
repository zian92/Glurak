package de.glurak.frontend.mainFrame.content.message;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

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

    /**
     * Konstruktor
     */
    public MessageVController() {
        messview = new MessageView();
        messview.b_send.addActionListener(this);
        messview.b_cancel.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == messview.b_send) {
            SessionThing session = SessionThing.getInstance();
            HibernateDB db = session.getDatabase();
            // Abfrage, ob ein Empfaenger eingegebeFn wurde
            if (messview.t_receiver.getText() == "") {
                JOptionPane.showMessageDialog(messview, "Sie haben noch keinen Empfänger eingegeben. Bitte fügen sie einen Empfänger hinzu!", "Fehlermeldung", JOptionPane.ERROR_MESSAGE);
            } else {
                // Abfrage, ob der Empfaenger nicht existiert
                if (!db.hasUser(messview.t_receiver.getText())) {
                    JOptionPane.showMessageDialog(messview, "Dieser Empfänger existiert nicht. Bitte geben sie einen existierenden Empfänger an!", "Fehlermeldung", JOptionPane.ERROR_MESSAGE);
                } else {
                    // Abfarge ob der sender der empfaenger ist
                    if (session.getSessionUser().equals(db.getUserByUsername(messview.t_receiver.getText()))) {
                        JOptionPane.showMessageDialog(messview, "Du kannst dir nicht selber eine Nachricht schreiben. Bitte geben sie einen anderen Empfänger an!", "Fehlermeldung", JOptionPane.ERROR_MESSAGE);
                    } else {
                        // Abfrage, ob die Nachricht leer ist
                        if (messview.t_message.getText() == "") {
                            JOptionPane.showMessageDialog(messview, "Sie haben keine Nachricht eingegeben. Bitte schreiben sie zuerst ihre Nachricht!", "Fehlermeldung", JOptionPane.ERROR_MESSAGE);
                        } else {
                            db.createMessage(session.getSessionUser(), db.getUserByUsername(messview.t_receiver.getText()), messview.t_message.getText(), false, null);
                        }
                    }
                }
            }
        } else
            if (e.getSource() == messview.b_cancel) {
                // TODO Das Panel schliessen, im Mainframe zerstoeren
                messview.t_message.setText("");
                messview.t_receiver.setText("");
            }
    }

    /**
     * @return Gibt das Messageviewpanel zurueck.
     */
    public JPanel getView() {
        return messview;
    }

}
