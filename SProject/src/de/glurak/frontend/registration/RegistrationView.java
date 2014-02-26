package de.glurak.frontend.registration;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;

/**
 * In der RegistrationView kann ein Benutzer des System sich einen neuen Account anlegen.
 * Um sich zu registrieren muss man die geforderten Daten angeben.
 * @author Simon
 *
 */
public class RegistrationView extends JFrame{

	//Textfelder fuer die Registrierung
	private JTextField t_username;
	private JTextField t_password;
	private JTextField t_birthdate;
	//Dropdown Menue um das Herkunftsland auszuwählen
	private JComboBox d_homecountry;
	//Radiobuttons um das Geschlecht auszuwählen
	private JRadioButton r_gender_m;
	private JRadioButton r_gender_f;
	//Buttons zum bestaetigen oder abbrechen
	private JButton b_register;
	private JButton b_cancel;
	
	/**
	 * Konstruktor
	 */
	public RegistrationView(){
		super("Registrierung");	
		
		//Initialisierung der Buttons
		b_register = new JButton("Registrieren");
		b_cancel = new JButton("Abbrechen");
		
		//Initialisierung der Radionbuttons
		r_gender_m = new JRadioButton("männlich");
		r_gender_f = new JRadioButton("weiblich");
		
		//Die Radiobuttons gruppieren
		ButtonGroup r_group = new ButtonGroup();
		r_group.add(r_gender_m);
		r_group.add(r_gender_f);
		
		//Initialisierung der Labels
		JLabel l_username = new JLabel("Username: ");
		JLabel l_password = new JLabel("Passwort: ");
		JLabel l_birthdate = new JLabel("Geburtsdatum: ");
		JLabel l_homecountry = new JLabel("Herkunftsland: ");
		JLabel l_gender = new JLabel("Geschlecht");	
		
	}
	
}
