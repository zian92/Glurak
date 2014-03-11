package de.glurak.frontend.mainFrame.content.message;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import de.glurak.data.User.Label;
import de.glurak.database.HibernateDB;
import de.glurak.frontend.SessionThing;
import de.glurak.frontend.mainFrame.ContentController;
import de.glurak.frontend.mainFrame.content.profile.LabelProfileVController;

/**
 * Der Kontroller fuer die Applicationview.
 * 
 * @author Simon
 * 
 */
public class ApplicationVController extends Observable implements ActionListener, ContentController {
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
            // Abfrage, ob die Nachricht leer ist
            if (appliview.t_application.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Sie haben keine Nachricht eingegeben. Bitte schreiben sie zuerst ihre Nachricht!", errorMsgBoxName, JOptionPane.ERROR_MESSAGE);
            } else {
                db.createMessage(session.getSessionUser(), label, appliview.t_application.getText(), true, null);
                JOptionPane.showMessageDialog(null, "Deine Nachricht wurde überstellt!", "Erfolg", JOptionPane.WARNING_MESSAGE);
                this.backToLabel();
            }
        } else {
            if (e.getSource() == appliview.b_cancel) {
                this.backToLabel();
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

    private void backToLabel() {
        setChanged();
        notifyObservers(new LabelProfileVController(this.label));
    }
}
