package de.glurak.frontend.registration;

import de.glurak.data.NewsEntry;
import de.glurak.data.User.User;
import de.glurak.data.User.UserProfile;
import de.glurak.frontend.SessionThing;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusListener;
import java.awt.event.FocusEvent;

import javax.swing.JPanel;
import javax.swing.JOptionPane;

/**
 * Der Kontroller für die Registrationview.
 * @author Simon
 *
 */
public class RegistrationVController implements ActionListener, FocusListener{

	private RegistrationView regview;
	
	/**
	 * Konstruktor
	 */
	public RegistrationVController(){
		regview = new RegistrationView(this, this);
	}
	
	public void actionPerformed(ActionEvent e){
		if (e.getActionCommand().equals("registrate")){
			try {
                User u = regview.getEnteredUser();
                if (u==null){
                    JOptionPane.showMessageDialog(regview, "Bitte füllen sie alle Felder korrekt aus, um sich zu registrieren!", "Fehlermeldung", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                SessionThing s = SessionThing.getInstance();
                if (s.getDatabase().hasUser(u.getUsername())){
                    JOptionPane.showMessageDialog(regview,"Ein Benutzer mit diesen Benutzernamen existiert bereits");
                    return;
                }

                UserProfile tmp = u.getProfile();
                u.setProfile(null);
                s.getDatabase().registrateProfile(tmp, null);
                u.setProfile(tmp);
                s.getDatabase().registrateUser(u,null);
               
                // Build a new Entry
                NewsEntry entry = new NewsEntry(u);
                s.getDatabase().addNewsEntry(entry, null);
                
                regview.setVisible(false);
            }
			catch (NumberFormatException nmf){
				JOptionPane.showMessageDialog(regview, "Bitte geben sie ein gültiges Datum an!", "Fehlermeldung", JOptionPane.ERROR_MESSAGE);
			}
		}
		else{
			if(e.getActionCommand().equals("cancel")){
				regview.setVisible(false);
			}
		}
	}
	
	/**
	 * @return Gibt das Registrationviewpanel zurueck
	 */
	public JPanel getView(){
		return regview;
	}

	public void focusGained(FocusEvent e) {
        regview.selectAll();
	}

	public void focusLost(FocusEvent e) {
		// TODO Auto-generated method stub
		
	}

	
}
