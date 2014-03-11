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
import java.awt.event.FocusListener;
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
    private JPasswordField t_password_2 = new JPasswordField();
    private JTextField t_birthdate_day = new JTextField();
    private JTextField t_birthdate_month = new JTextField();
    private JTextField t_birthdate_year = new JTextField();
    private JTextField t_prename = new JTextField();
    private JTextField t_surname = new JTextField();
    private JTextField t_mailaddr = new JTextField();
    private JTextField t_addr = new JTextField();
	//Dropdown Menue um das Herkunftsland auszuwaehlen
    private JComboBox<String> d_homecountry;
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
     * @param actionlistener der Actionlistener für die Button. null für keinen
     * @param focuslistener der FocusListener für das Enter. null für keinen
	 */
    public RegistrationView(ActionListener actionlistener, FocusListener focuslistener){
        setLayout(new BorderLayout());
        setBackground(panColor);
        //Die Panels initialisieren
        JPanel pan_input=new JPanel();
        pan_input.setLayout(new GridLayout(0,1));
        pan_input.setBackground(panColor);
        JPanel pan_buttons=new JPanel();
        pan_buttons.setLayout(new FlowLayout());
        pan_buttons.setBackground(panColor);

        //Buttons initialisieren
        b_register = new JButton("Registrieren");
        if (actionlistener!=null)
            b_register.addActionListener(actionlistener);
        b_register.setActionCommand("registrate");
        b_cancel = new JButton("Abbrechen");
        if (actionlistener !=null)
            b_cancel.addActionListener(actionlistener);
        b_cancel.setActionCommand("cancel");



        //Titel
        JLabel l_note = new JLabel("<HTML><BODY>Füllen sie folgendes Formular<BR> aus, um sich zu registrieren: </BODY></HTML>");
        l_note.setForeground(Color.WHITE);
        add(l_note, BorderLayout.NORTH);

        //Dropdownmenue fuer das Herkunftsland anlegen
        String[] countries = new String[] {"", "Belgium", "China", "Dänemark", "Deutschland", "England", "Finnland",
                "Frankreich", "Irland", "Italien", "Japan", "Luxemburg", "Niederlande", "Norwegen", "Österreich",
                "Polen", "Portugal", "Russland", "Schweden", "Schweiz", "Spanien", "Tschechien", "Vereinigte Staaten",
                "anderes Land"};
        d_homecountry = new JComboBox(countries);

        //Inputsachen
        pan_input.add(addAEntry("Benutzername:", t_username));
        pan_input.add(addAEntry("Passwort:", t_password));

        pan_input.add(addAEntry("Passwort wiederholen:", t_password_2));
        pan_input.add(addAEntry("Mail-Addresse:", t_mailaddr));
        pan_input.add(addAEntry("Vorname:", t_prename));
        pan_input.add(addAEntry("Nachname:", t_surname));
        pan_input.add(addAEntry("Geburtsdatum:", birthdayPanel()));
        pan_input.add(addAEntry("Adresse:", t_addr));
        pan_input.add(addAEntry("Herkunfsland:", d_homecountry));
        pan_input.add(addAEntry("Geschlecht:", getGenderRadio()));

        //Buttons in Buttonpanel einfuegen
        pan_buttons.add(b_register);
        pan_buttons.add(b_cancel);

        add(pan_buttons,BorderLayout.SOUTH);
        add(pan_input,BorderLayout.NORTH);

        //Sonstiges
        if (focuslistener!=null){
            t_username.addFocusListener(focuslistener);
            t_prename.addFocusListener(focuslistener);
            t_surname.addFocusListener(focuslistener);
            t_password.addFocusListener(focuslistener);
            t_password_2.addFocusListener(focuslistener);
            t_birthdate_day.addFocusListener(focuslistener);
            t_birthdate_month.addFocusListener(focuslistener);
            t_birthdate_year.addFocusListener(focuslistener);
            t_mailaddr.addFocusListener(focuslistener);
            t_addr.addFocusListener(focuslistener);
        }

    }

    private JPanel getGenderRadio(){
        //Initialisierung der Radionbuttons
        r_gender_m = new JRadioButton("männlich");
        r_gender_f = new JRadioButton("weiblich");

        //Die Radiobuttons gruppieren
        ButtonGroup r_group = new ButtonGroup();
        r_group.add(r_gender_m);
        r_group.add(r_gender_f);
        JPanel pan_radio = new JPanel();
        pan_radio.setBackground(panColor);
        r_gender_m.setBackground(panColor);
        r_gender_f.setBackground(panColor);
        r_gender_m.setForeground(Color.WHITE);
        r_gender_f.setForeground(Color.WHITE);
        pan_radio.add(r_gender_m);
        pan_radio.add(r_gender_f);
        return pan_radio;
    }

    private JPanel birthdayPanel(){
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
        return birthday;

    }

    public JPanel addAEntry(String description, JComponent other){
        JPanel res = new JPanel();
        res.setBackground(panColor);
        res.setLayout(new GridLayout());
        JLabel l = new JLabel(description);
        l.setForeground(Color.WHITE);
        res.add(l);
        res.add(other);
        return res;
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
        String pass2= new String(t_password_2.getPassword()).trim();
        if (pass.isEmpty()|| !pass.equals(pass2)) return null;
        try {
            res.setPassword(pass);
        } catch (NoSuchAlgorithmException e) {
            SessionThing s = SessionThing.getInstance();
            s.handleException(e);
        }
        String prename = t_prename.getText().trim();
        if (t_prename.getText().trim().isEmpty()) return null;
        String surname = t_surname.getText().trim();
        ListenerProfile p = new ListenerProfile();

        
        p.setFirstname(prename);
        p.setLastname(surname);
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
        p.setEmail(t_mailaddr.getText());
        p.setAddress(t_addr.getText());

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
		JComponent newContentPane = new RegistrationView(null, null);
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

    public void selectAll(){
        t_username.selectAll();
        t_prename.selectAll();
        t_surname.selectAll();
        t_password.selectAll();
        t_password_2.selectAll();
        t_birthdate_day.selectAll();
        t_birthdate_month.selectAll();
        t_birthdate_year.selectAll();
        t_mailaddr.selectAll();
        t_addr.selectAll();
    }
	
	public static void main(String[] args){
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowView();
            }
        });
	}
	
}
