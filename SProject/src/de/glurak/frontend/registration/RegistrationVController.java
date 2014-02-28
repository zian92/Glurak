package de.glurak.frontend.registration;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;

/**
 * Der Kontroller für die Registrationview.
 * @author Simon
 *
 */
public class RegistrationVController implements ActionListener{

	private RegistrationView regview;
	
	/**
	 * Konstruktor
	 */
	public RegistrationVController(){
		regview = new RegistrationView();
		regview.b_cancel.addActionListener(this);
		regview.b_register.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e){
		if (e.getSource() == regview.b_register){
			/**
			 * Was soll passiren wenn man die register Button drueckt
			 */
		}
		else{
			if(e.getSource() == regview.b_cancel){
				/**
				 * Was soll passieren wenn man den abbrechen button drueckt
				 */
			}
		}
	}
	
	/**
	 * @return Gibt das Registrationviewpanel zurueck
	 */
	public JPanel getView(){
		return regview;
	}

	
}
