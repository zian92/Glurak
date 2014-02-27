package de.glurak.frontend.mainFrame.content.profile;

import javax.swing.*;
import java.awt.*;

/**
 * Die ProfileView zeigt dem User ein Profil an.
 * @author Christopher Distelkämper
 * Date: 26.02.2014
 */

public class ProfileView extends JPanel{

	// Panels
	private JPanel pan_profileview;
	private JPanel pan_profilepic;
	private JPanel pan_profiledata;
	private JPanel pan_topplaylists;
	private JPanel pan_likes;
	
	// Buttons
	private JButton b_message;
	private JButton b_follow;
	private JButton b_edit;
	private JButton b_moreplaylists;
	
	// TextFields profile_data
	private JTextField t_username;
	private JTextField t_firstname;
	private JTextField t_lastname;
	private JTextField t_birthdate;
	private JTextField t_homecountry;
	
	// Labels profile_data
	private JLabel l_username;
	private JLabel l_firstname;
	private JLabel l_lastname;
	private JLabel l_birthdate;
	private JLabel l_homecountry;
	
	
	/**
	 * Constructor
	 * @param own
	 */
	public ProfileView(boolean own){
		
		// Initialisieren Panel pan_profileview
		pan_profileview = new JPanel(new GridBagLayout());
		pan_profileview.setSize(800,500);
		
		// Layout-Restriktionen festlegen.
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(1,1,2,2);	
		
		// Initialisieren Panel pan_likes
		pan_likes = new JPanel();
		
		// Initialisieren Panel pan_profilepic
		pan_profilepic = new JPanel(new GridBagLayout());
		
			// Layout-Restriktionen festlegen.
			GridBagConstraints d = new GridBagConstraints();
			d.fill = GridBagConstraints.BOTH;
			d.insets = new Insets(1,1,2,2);	
				
		    // Initialisieren der Buttons b_message, b_follow, b_edit
			
			if (own){  // Falls das eigene Profil angezeigt werden soll, nur b_edit anzeigen
			
		    	d.gridx = 1;
				d.gridy = 1;
				b_edit = new JButton("Bearbeiten");
				pan_profilepic.add(b_edit, d);
				
			}
			else{      // Falls ein anderes Profil angezeigt werden soll, b_message und b_follow anzeigen
				
				d.gridx = 0;
				d.gridy = 1;
			    b_message = new JButton("Nachricht");
			    pan_profilepic.add(b_message, d);
			    
			    d.gridx = 2;
			    d.gridy = 1;
			    b_follow = new JButton("Follow");
			    pan_profilepic.add(b_follow, d);
			    
			}
		// Initialisieren Panel pan_topplaylists
		pan_topplaylists = new JPanel();	
		
			// Initialisieren des Buttons b_moreplaylists
			b_moreplaylists = new JButton("mehr");
			pan_topplaylists.add(b_moreplaylists);
					
		// Initialisieren Panel pan_profiledata
		pan_profiledata = new JPanel();		
		
			// Layout-Restriktionen festlegen.
			GridBagConstraints e = new GridBagConstraints();
			e.fill = GridBagConstraints.BOTH;
			e.insets = new Insets(1,1,2,2);	

			// Label und Textfelder hinzufügen
			// Username
			e.gridx = 0;
			e.gridy = 0;
			l_username = new JLabel("Username:");
			pan_profiledata.add(l_username, e);
			
			e.gridx = 1;
			e.gridy = 0;
			t_username = new JTextField();
			t_username.setEditable(false);
			pan_profiledata.add(t_username, e);
			
			// Vorname
			e.gridx = 0;
			e.gridy = 1;
			l_firstname = new JLabel("Vorname:");
			pan_profiledata.add(l_firstname, e);
			
			e.gridx = 1;
			e.gridy = 1;
			t_firstname = new JTextField();
			t_firstname.setEditable(false);
			pan_profiledata.add(t_firstname, e);
			
			// Nachname
			e.gridx = 0;
			e.gridy = 2;
			l_lastname = new JLabel("Nachname:");
			pan_profiledata.add(l_lastname, e);
			
			e.gridx = 1;
			e.gridy = 2;
			t_lastname = new JTextField();
			t_lastname.setEditable(false);
			pan_profiledata.add(t_lastname, e);
			
			// Geburtstag
			e.gridx = 0;
			e.gridy = 3;
			l_birthdate = new JLabel("Geburtstag:");
			pan_profiledata.add(l_birthdate, e);
			
			e.gridx = 1;
			e.gridy = 3;
			t_birthdate = new JTextField();
			t_birthdate.setEditable(false);
			pan_profiledata.add(t_birthdate, e);
			
			// Heimatland
			e.gridx = 0;
			e.gridy = 4;
			l_homecountry = new JLabel("Heimatland:");
			pan_profiledata.add(l_homecountry, e);
			
			e.gridx = 1;
			e.gridy = 4;
			t_homecountry = new JTextField();
			t_homecountry.setEditable(false);
			pan_profiledata.add(t_homecountry, e);
			
			
		// Hinzufügen der Panels zum Panel pan_profileview
		c.gridx = 0;
		c.gridy = 0;
		pan_profileview.add(pan_likes, c);
		
		c.gridx = 1;
		c.gridy = 0;
		pan_profileview.add(pan_profilepic, c);
		
		c.gridx = 0;
		c.gridy = 1;
		pan_profileview.add(pan_topplaylists);
		
		c.gridx = 1;
		c.gridy = 1;
		pan_profileview.add(pan_profiledata);
		
		// Hinzufügen des Panels zur ContentPane
		add(pan_profileview);		
		setVisible(true);
		
	}
		
	/**
	 * Erzeugt die ProfileView und zeigt sie an.
	 */
	private static void createAndShowView(){
		//Erzeugen des Frames
		JFrame profile = new JFrame("Profile");
		profile.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Registrationview in das Frame laden
		JComponent newContentPane = new ProfileView(false);
        newContentPane.setOpaque(true);
        profile.setContentPane(newContentPane);
        
        //Groesse des Frames festlegen
        profile.setPreferredSize(new Dimension(1000, 500));
        //Groesse des Frames soll nicht veraenderbar sein
        profile.setResizable(false);
        //Registrationview wird in der Mitte des Bildschirms geladen
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        profile.setLocation(dim.width/2-profile.getSize().width/2-300, dim.height/2-profile.getSize().height/2-150);
		
		//Frame anpassen und sichtbar machen
		profile.pack();
		profile.setVisible(true);
	}
	
	public static void main(String[] args){
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowView();
            }
        });
	}
}
