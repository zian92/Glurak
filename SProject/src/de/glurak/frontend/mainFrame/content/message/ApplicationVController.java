package de.glurak.frontend.mainFrame.content.message;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import de.glurak.data.User.Label;
import de.glurak.data.User.Reachable;
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
    private Reachable appliTo;
    private ContentController nextContent;

    /**
     * Konstruktor
     */
    public ApplicationVController(Reachable appliTo, ContentController nextContent) {
        appliview = new ApplicationView();
        appliview.b_send.addActionListener(this);
        appliview.b_cancel.addActionListener(this);
        this.appliTo = appliTo;
        this.nextContent = nextContent;
        appliview.t_receiver.setText(appliTo.getProfile().viewName());
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == appliview.b_send) {
            // Abfrage, ob ein Empfaenger eingegeben wurde
            // Abfrage, ob die Nachricht leer ist
            if (appliview.t_application.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Sie haben keine Nachricht eingegeben. Bitte schreiben sie zuerst ihre Nachricht!", errorMsgBoxName, JOptionPane.ERROR_MESSAGE);
            } else {
                db.createMessage(session.getSessionUser(), appliTo, appliview.t_application.getText(), true, null);
                JOptionPane.showMessageDialog(null, "Deine Nachricht wurde überstellt!", "Erfolg", JOptionPane.WARNING_MESSAGE);
                this.backToPrev();
            }
        } else {
            if (e.getSource() == appliview.b_cancel) {
                this.backToPrev();
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

    private void backToPrev() {
        setChanged();
        notifyObservers(nextContent);
    }
}
