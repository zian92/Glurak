package de.glurak.frontend.mainFrame.content.message;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import de.glurak.data.User.Label;
import de.glurak.database.HibernateDB;
import de.glurak.frontend.SessionThing;
import de.glurak.frontend.mainFrame.ContentController;

/**
 * Der Kontroller fuer die Applicationview.
 * 
 * @author Simon
 * 
 */
public class ApplicationVController implements ActionListener, ContentController {

    private ApplicationView appliview;
    private String errorMsgBoxName = "Fehlermeldung";
    private SessionThing session = SessionThing.getInstance();
    private HibernateDB db = session.getDatabase();
    private Label label;

    /**
     * Konstruktor
     */
    public ApplicationVController(Label label) {
        appliview = new ApplicationView();
        appliview.b_send.addActionListener(this);
        appliview.b_cancel.addActionListener(this);
        this.label = label;

        appliview.t_receiver.setText(label.getProfile().getName());
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == appliview.b_send) {
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

    public void reload() {
        // TODO Auto-generated method stub

    }

}
