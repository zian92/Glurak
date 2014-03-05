package de.glurak.frontend.registration;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import javax.swing.JFrame;

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
			//Ueberpruefen, ob in der Geburtstagszeile ein Datum eingeben wurde
			try {
				int day = Integer.parseInt(regview.t_birthdate_day.getText());
				int month = Integer.parseInt(regview.t_birthdate_month.getText());
				int year = Integer.parseInt(regview.t_birthdate_year.getText());
				//Abfrage, ob alle Textfelder ausgefüllt wurden 
				if (	(regview.t_username.getText() == null) ||
						(regview.t_password.getText() == null) ||
						(regview.t_birthdate_day.getText() == null) ||
						(regview.t_birthdate_month.getText() == null) ||
						(regview.t_birthdate_year.getText() == null) ||
						(regview.d_homecountry.getSelectedIndex() == 0) ||
						(regview.r_gender_f.isSelected() == false && regview.r_gender_m.isSelected() == false)){
					JOptionPane.showMessageDialog(null, "Bitte füllen sie alle Felder aus, um sich zu registrieren!", "Fehlermeldung", JOptionPane.ERROR_MESSAGE);	
				}
				else{
					//Abfrage, ob es ein gueltiges Geburtstagsdatum ist
					if (	( day > 31) ||
							(month > 12) ||
							(year > 2014)){
						JOptionPane.showMessageDialog(null, "Bitte geben sie ein richtiges Geburtsdatum an!", "Fehlermeldung", JOptionPane.ERROR_MESSAGE);	
					}
					else{
						//Abfrage, ob es den Usernamen bereits gibt
						//TODO Usernamen mit Datenbankeintraegen vergleichen
						if(regview.t_username.getText() == "!!!Platzhalter!!!"){
							JOptionPane.showMessageDialog(null, "Dieser Username ist bereits belegt. Bitte suchen sie sich einen annderen Usernamen aus!", "Fehlermeldung", JOptionPane.ERROR_MESSAGE);	
						}
						else{
							//TODO User in der Datenbank registrieren
							/**
							 * Was soll passieren wenn man den register Button drueckt
							 */
							JOptionPane.showMessageDialog(null, "Sie haben sich erfolgreich registriert! Sie können sich nun mit ihren Account einloggen.", "Registrierung erfolgreich!", JOptionPane.PLAIN_MESSAGE);	
						}
					}
				}
			}
			catch (NumberFormatException nmf){
				JOptionPane.showMessageDialog(null, "Bitte geben sie ein gültiges Datum an!", "Fehlermeldung", JOptionPane.ERROR_MESSAGE);	
			}
		}
		else{
			if(e.getSource() == regview.b_cancel){
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

	
}
