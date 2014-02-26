package de.glurak.frontend.registration;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.Container;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JComponent;

/**
 * In der RegistrationView kann ein Benutzer des System sich einen neuen Account anlegen.
 * Um sich zu registrieren muss man die geforderten Daten angeben.
 * @author Simon
 *
 */
public class RegistrationView extends JPanel{

	//Textfelder fuer die Registrierung
	private JTextField t_username = new JTextField();
	private JTextField t_password = new JTextField();
	private JTextField t_birthdate = new JTextField();
	//Dropdown Menue um das Herkunftsland auszuwählen
	private JComboBox<String> d_homecountry;
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
		
		//Layout des Frames festlegen
		setLayout(new BorderLayout());
		
		//Panels Initialisieren
		JPanel pan_input = new JPanel();
		JPanel pan_buttons = new JPanel();
		
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
		
		//Die Radiobuttons in ein Panel zusammenfuegen
		JPanel pan_radio = new JPanel();
		pan_radio.add(r_gender_m);
		pan_radio.add(r_gender_f);
		
		//Initialisierung der Labels
		JLabel l_note = new JLabel("Füllen sie folgendes Formular aus, um sich zu registrieren: ");
		JLabel l_username = new JLabel("Username: ");
		JLabel l_password = new JLabel("Passwort: ");
		JLabel l_birthdate = new JLabel("Geburtsdatum: ");
		JLabel l_homecountry = new JLabel("Herkunftsland: ");
		JLabel l_gender = new JLabel("Geschlecht");
		
		//Layout der Panels festlegen
		pan_input.setLayout(new GridLayout(5, 0));
		pan_buttons.setLayout(new FlowLayout());
		
		//Buttons in Buttonpanel einfuegen
		pan_buttons.add(b_register);
		pan_buttons.add(b_cancel);
		
		//Dropdownmenue fuer das Herkunftsland anlegen
		String[] countries = new String[] {"Deutschland", "nicht Deutschland"};
		d_homecountry = new JComboBox(countries);
		
		//Labels, Textfelder, Dropdownmenue und Radiobuttons in das Input Panel einfuegen
		pan_input.add(l_username);
		pan_input.add(t_username);
		pan_input.add(l_password);
		pan_input.add(t_password);
		pan_input.add(l_birthdate);
		pan_input.add(t_birthdate);
		pan_input.add(l_homecountry);
		pan_input.add(d_homecountry);
		pan_input.add(l_gender);
		pan_input.add(pan_radio);
		
		//Panel in das Frame einfügen
		add(pan_input, BorderLayout.CENTER);
		add(pan_buttons, BorderLayout.SOUTH);
		add(l_note, BorderLayout.NORTH);
	}
	
	/**
	 * Erzeugt die Registrierungsview und zeigt sie an.
	 */
	private static void createAndShowView(){
		JFrame register = new JFrame("Registrierung");
		register.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JComponent newContentPane = new RegistrationView();
        newContentPane.setOpaque(true); //content panes must be opaque
        register.setContentPane(newContentPane);
		
		//Frame anpassen und sichtbar machen
		register.pack();
		register.setVisible(true);
	}
	
	public static void main(String[] args){
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowView();
            }
        });
	}
	
}
