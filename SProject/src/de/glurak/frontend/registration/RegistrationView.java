package de.glurak.frontend.registration;

import de.glurak.data.User.ListenerProfile;
import de.glurak.data.User.User;
import de.glurak.frontend.SessionThing;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.security.NoSuchAlgorithmException;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JComponent;
import javax.swing.JPasswordField;

/**
 * In der RegistrationView kann ein Benutzer des System sich einen neuen Account anlegen.
 * Um sich zu registrieren muss man die geforderten Daten angeben.
 * @author Simon
 *
 */
public class RegistrationView extends JPanel{

	//Textfelder fuer die Registrierung
	private JTextField t_username = new JTextField();
	private JPasswordField t_password = new JPasswordField();
	private JTextField t_birthdate_day = new JTextField();
	private JTextField t_birthdate_month = new JTextField();
	private JTextField t_birthdate_year = new JTextField();
	//Dropdown Menue um das Herkunftsland auszuwaehlen
	protected JComboBox<String> d_homecountry;
	//Radiobuttons um das Geschlecht auszuwaehlen
	private JRadioButton r_gender_m;
	private JRadioButton r_gender_f;
	//Buttons zum bestaetigen oder abbrechen
	private JButton b_register;
	private JButton b_cancel;
	//Farbe fuer das Panel
	private Color  panColor = new Color(7354141);
	
	/**
	 * Konstruktor.
     * @param listener der Actionlistener für die Button. null für keinen
	 */
	public RegistrationView(ActionListener listener){
		
		//Layout des Frames festlegen
		setLayout(new BorderLayout());
		
		//Panels Initialisieren
		JPanel pan_input = new JPanel();
		JPanel pan_buttons = new JPanel();
		
		//Initialisierung der Buttons
		b_register = new JButton("Registrieren");
        if (listener!=null)
        b_register.addActionListener(listener);
        b_register.setActionCommand("registrate");
		b_cancel = new JButton("Abbrechen");
        if (listener !=null)
        b_cancel.addActionListener(listener);
        b_cancel.setActionCommand("cancel");
		
		//Initialisierung der Radionbuttons
		r_gender_m = new JRadioButton("männlich");
		r_gender_f = new JRadioButton("weiblich");
		
		//Die Radiobuttons gruppieren
		ButtonGroup r_group = new ButtonGroup();
		r_group.add(r_gender_m);
		r_group.add(r_gender_f);
		
		//Die Radiobuttons in ein Panel zusammenfuegen
		JPanel pan_radio = new JPanel();
		pan_radio.setBackground(panColor);
		r_gender_m.setBackground(panColor);
		r_gender_f.setBackground(panColor);
		r_gender_m.setForeground(Color.WHITE);
		r_gender_f.setForeground(Color.WHITE);
		pan_radio.add(r_gender_m);
		pan_radio.add(r_gender_f);
		
		//Initialisierung der Labels
		JLabel l_note = new JLabel("<HTML><BODY>Füllen sie folgendes Formular<BR> aus, um sich zu registrieren: </BODY></HTML>");
		JLabel l_username = new JLabel("Username: ");
		JLabel l_password = new JLabel("Passwort: ");
		JLabel l_birthdate = new JLabel("Geburtsdatum: ");
		JLabel l_homecountry = new JLabel("Herkunftsland: ");
		JLabel l_gender = new JLabel("Geschlecht: ");
		
		//Labels in weisser Schrift
		l_note.setForeground(Color.WHITE);
		l_username.setForeground(Color.WHITE);
		l_password.setForeground(Color.WHITE);
		l_birthdate.setForeground(Color.WHITE);
		l_homecountry.setForeground(Color.WHITE);
		l_gender.setForeground(Color.WHITE);
		
		//Layout der Panels festlegen
		pan_input.setLayout(new GridLayout(5, 1, 10, 15));
		pan_buttons.setLayout(new FlowLayout());
		
		//Buttons in Buttonpanel einfuegen
		pan_buttons.add(b_register);
		pan_buttons.add(b_cancel);
		
		//Dropdownmenue fuer das Herkunftsland anlegen
		String[] countries = new String[] {"", "Belgium", "China", "Dänemark", "Deutschland", "England", "Finnland", 
				"Frankreich", "Irland", "Italien", "Japan", "Luxemburg", "Niederlande", "Norwegen", "Österreich", 
				"Polen", "Portugal", "Russland", "Schweden", "Schweiz", "Spanien", "Tschechien", "Vereinigte Staaten", 
				"anderes Land"};
		d_homecountry = new JComboBox(countries);
		
		//Geburtstagspanel erzeugen
		JPanel birthday = new JPanel();
		birthday.setBackground(panColor);
		birthday.setLayout(new GridLayout(1,0));
		JLabel dd = new JLabel(" (dd) . ");
		dd.setForeground(Color.WHITE);
		birthday.add(t_birthdate_day);
		birthday.add(dd);
		JLabel mm = new JLabel(" (mm) . ");
		mm.setForeground(Color.WHITE);
		birthday.add(t_birthdate_month);
		birthday.add(mm);
		birthday.add(t_birthdate_year);
		JLabel yyyy = new JLabel(" (yyyy)");
		yyyy.setForeground(Color.WHITE);
		birthday.add(yyyy);
		
		//Labels, Textfelder, Dropdownmenue und Radiobuttons in das Input Panel einfuegen
		pan_input.add(l_username);
		pan_input.add(t_username);
		pan_input.add(l_password);
		pan_input.add(t_password);
		pan_input.add(l_birthdate);
		pan_input.add(birthday);
		pan_input.add(l_homecountry);
		pan_input.add(d_homecountry);
		pan_input.add(l_gender);
		pan_input.add(pan_radio);
		
		//Panels Hintergrundfarbe hinzufuegen
		pan_buttons.setBackground(panColor);
		pan_input.setBackground(panColor);
		setBackground(panColor);
		
		//Panel in das Frame einfuegen
		add(pan_input, BorderLayout.CENTER);
		add(pan_buttons, BorderLayout.SOUTH);
		add(l_note, BorderLayout.NORTH);
	}

    /**
     * Gibt den User zurück der von den Werten in der Eingabe erzeugt wird.
     * Er muss noch in der Datenbank registriert werden.
     * @author Entscheider
     * @return der neue Benutzer, null falls irgendwechle Daten fehlen
     */
    public User getEnteredUser(){
        User res = new User();
        res.setUsername(t_username.getText().trim());
        if (t_username.getText().trim().isEmpty()) return null;
        String pass = new String(t_password.getPassword()).trim();
        if (pass.isEmpty()) return null;
        try {
            res.setPassword(pass);
        } catch (NoSuchAlgorithmException e) {
            SessionThing s = SessionThing.getInstance();
            s.handleException(e);
        }
        ListenerProfile p = new ListenerProfile();


        int day = Integer.valueOf(t_birthdate_day.getText());
        int month = Integer.valueOf(t_birthdate_month.getText());
        int year = Integer.valueOf(t_birthdate_year.getText());
        if (!(1<=day&& day<=31 && 1<=month&& month<=12 && 1900<=year) )
            return null;
        p.setBirthdate(day+"."+month+"."+year);
        res.setProfile(p);
        p.setFemale(r_gender_f.isSelected());
        p.setCountry(d_homecountry.getSelectedItem().toString());
        //TODO: andere Felder
        return res;
    }
	
	
	/**
	 * Erzeugt die Registrationview und zeigt sie an.
	 */
	private static void createAndShowView(){
		//Erzeugen des Frames
		JFrame register = new JFrame("Registrierung");
		register.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Registrationview in das Frame laden
		JComponent newContentPane = new RegistrationView(null);
        newContentPane.setOpaque(true);
        register.setContentPane(newContentPane);
        
        //Groesse des Frames festlegen
        //register.setPreferredSize(new Dimension(600,300));
        //Groesse des Frames soll nicht veraenderbar sein
        //register.setResizable(false);
        //Registrationview wird in der Mitte des Bildschirms geladen
        /** Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        register.setLocation(dim.width/2-register.getSize().width/2-300, dim.height/2-register.getSize().height/2-150);**/
		
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
