package de.glurak.frontend.mainFrame.content.message;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import de.glurak.database.HibernateDB;
import de.glurak.frontend.SessionThing;

/**
 * Der Kontroller fuer die Applicationview.
 * 
 * @author Simon
 * 
 */
public class ApplicationVController implements ActionListener {

    private ApplicationView appliview;
    private String errorMsgBoxName = "Fehlermeldung";

    /**
     * Konstruktor
     */
    public ApplicationVController() {
        appliview = new ApplicationView();
        appliview.b_send.addActionListener(this);
        appliview.b_cancel.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == appliview.b_send) {
            SessionThing session = SessionThing.getInstance();
            HibernateDB db = session.getDatabase();
            // Abfrage, ob ein Empfaenger eingegeben wurde
            if (appliview.t_receiver.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Sie haben noch keinen Empfänger eingegeben. Bitte fügen sie einen Empfänger hinzu!", "Fehlermeldung", JOptionPane.ERROR_MESSAGE);
            } else {
                // Abfrage, ob der Empfaenger existiert
                if (!db.hasUser(appliview.t_receiver.getText())) {
                    JOptionPane.showMessageDialog(null, "Dieser Empfänger existiert nicht. Bitte geben sie einen existierenden Empfänger an!", "Fehlermeldung", JOptionPane.ERROR_MESSAGE);
                } else {
                    // Abfrage, ob die Nachricht leer ist
                    if (appliview.t_application.getText().equals("")) {
                        JOptionPane.showMessageDialog(null, "Sie haben keine Nachricht eingegeben. Bitte schreiben sie zuerst ihre Nachricht!", errorMsgBoxName, JOptionPane.ERROR_MESSAGE);
                    } else {
                        db.createMessage(session.getSessionUser(), db.getUserByUsername(appliview.t_receiver.getText()), appliview.t_application.getText(), false, null);
                    }
                }
            }
        } else {
            if (e.getSource() == appliview.b_cancel) {
                appliview.t_application.setText("");
            }
        }
    }

    /**
     * @return Gibt das Applicationviewpanel zurueck
     */
    public JPanel getView() {
        return appliview;
    }

}
