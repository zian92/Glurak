package de.glurak.frontend.mainFrame.content.profile;

import javax.swing.*;
import java.awt.*;

/**
 * Die ProfileEditView bietet dem Benutzer die Möglichkeit zum Bearbeiten seines Profils.
 * @author Christopher Distelkämper
 * Date: 27.02.2014
 */
public class ProfileEditView extends JPanel{

		// Panels
		private JPanel pan_profileeditview;
		private JPanel pan_profilepic;
		private JPanel pan_profiledata;
		private JPanel pan_likes;
		
		// Buttons
		private JButton b_save;
		private JButton b_uploadpic;
		
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
		public ProfileEditView(){
			
			// Initialisieren Panel pan_profileview
			pan_profileeditview = new JPanel(new GridBagLayout());
			pan_profileeditview.setSize(800,500);
			
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
					
			    // Initialisieren des Buttons b_uploadpic
				d.gridx = 0;
				d.gridy = 1;
				b_uploadpic = new JButton("Bild hochladen");
				pan_profilepic.add(b_uploadpic);
							
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
				pan_profiledata.add(t_firstname, e);
				
				// Nachname
				e.gridx = 0;
				e.gridy = 2;
				l_lastname = new JLabel("Nachname:");
				pan_profiledata.add(l_lastname, e);
				
				e.gridx = 1;
				e.gridy = 2;
				t_lastname = new JTextField();
				pan_profiledata.add(t_lastname, e);
				
				// Geburtstag
				e.gridx = 0;
				e.gridy = 3;
				l_birthdate = new JLabel("Geburtstag:");
				pan_profiledata.add(l_birthdate, e);
				
				e.gridx = 1;
				e.gridy = 3;
				t_birthdate = new JTextField();
				pan_profiledata.add(t_birthdate, e);
				
				// Heimatland
				e.gridx = 0;
				e.gridy = 4;
				l_homecountry = new JLabel("Heimatland:");
				pan_profiledata.add(l_homecountry, e);
				
				e.gridx = 1;
				e.gridy = 4;
				t_homecountry = new JTextField();
				pan_profiledata.add(t_homecountry, e);
				
				// Einfügen des Buttons b_save
				e.gridx = 0;
				e.gridy = 5;
				b_save = new JButton("Speichern");
				pan_profiledata.add(b_save);
				
				
			// Hinzufügen der Panels zum Panel pan_profileview
			c.gridx = 0;
			c.gridy = 0;
			pan_profileeditview.add(pan_likes, c);
			
			c.gridx = 1;
			c.gridy = 0;
			pan_profileeditview.add(pan_profilepic, c);
						
			c.gridx = 1;
			c.gridy = 1;
			pan_profileeditview.add(pan_profiledata);
			
			// Hinzufügen des Panels zur ContentPane
			add(pan_profileeditview);		
			setVisible(true);
			
		}
			
		/**
		 * Erzeugt die ProfileView und zeigt sie an.
		 */
		private static void createAndShowView(){
			//Erzeugen des Frames
			JFrame profileedit = new JFrame("Profile");
			profileedit.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			//Registrationview in das Frame laden
			JComponent newContentPane = new ProfileEditView();
	        newContentPane.setOpaque(true);
	        profileedit.setContentPane(newContentPane);
	        
	        //Groesse des Frames festlegen
	        profileedit.setPreferredSize(new Dimension(1000, 500));
	        //Groesse des Frames soll nicht veraenderbar sein
	        profileedit.setResizable(false);
	        //Registrationview wird in der Mitte des Bildschirms geladen
	        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	        profileedit.setLocation(dim.width/2-profileedit.getSize().width/2-300, dim.height/2-profileedit.getSize().height/2-150);
			
			//Frame anpassen und sichtbar machen
			profileedit.pack();
			profileedit.setVisible(true);
		}
		
		public static void main(String[] args){
			javax.swing.SwingUtilities.invokeLater(new Runnable() {
	            public void run() {
	                createAndShowView();
	            }
	        });
		}
}
