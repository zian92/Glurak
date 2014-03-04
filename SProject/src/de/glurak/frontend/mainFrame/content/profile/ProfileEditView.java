package de.glurak.frontend.mainFrame.content.profile;

import javax.swing.*;
import javax.swing.border.Border;

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
		protected JButton b_save;
		protected JButton b_uploadpic;
		
		// TextFields profile_data
		protected JTextField t_username;
		protected JTextField t_firstname;
		protected JTextField t_lastname;
		protected JTextField t_birthdate;
		protected JTextField t_homecountry;
		
		// Labels profile_data
		private JLabel l_username;
		private JLabel l_firstname;
		private JLabel l_lastname;
		private JLabel l_birthdate;
		private JLabel l_homecountry;
		
		
		/**
		 * Constructor
		 */
		public ProfileEditView(){
			
			// Initialisieren Panel pan_profileview
			pan_profileeditview = new JPanel(new GridBagLayout());
			pan_profileeditview.setSize(800,500);
			pan_profileeditview.setBackground(Color.black);
			
			// Layout-Restriktionen festlegen.
			GridBagConstraints c = new GridBagConstraints();
			c.fill = GridBagConstraints.HORIZONTAL;
			c.insets = new Insets(2,2,2,2);	
			
			// Initialisieren Panel pan_likes
			pan_likes = new JPanel();
			pan_likes.setSize(350,200);
			pan_likes.setBorder(BorderFactory.createLineBorder(Color.black));
			pan_likes.setBackground(Color.black);
			
			// Initialisieren Panel pan_profilepic
			pan_profilepic = new JPanel(new GridBagLayout());
			pan_profilepic.setSize(350,200);
			pan_profilepic.setBorder(BorderFactory.createLineBorder(Color.black));
			pan_profilepic.setBackground(Color.black);
			
				// Layout-Restriktionen festlegen.
				GridBagConstraints d = new GridBagConstraints();
				d.fill = GridBagConstraints.HORIZONTAL;
				d.insets = new Insets(2,2,2,2);	
					
			    // Initialisieren des Buttons b_uploadpic
				d.gridx = 0;
				d.gridy = 1;
				b_uploadpic = new JButton("Bild hochladen");
				b_uploadpic.setBackground(Color.black);
				b_uploadpic.setForeground(Color.white);
				b_uploadpic.setBorder(BorderFactory.createLineBorder(Color.white));
				pan_profilepic.add(b_uploadpic, d);
							
			// Initialisieren Panel pan_profiledata
			pan_profiledata = new JPanel(new GridBagLayout());
			pan_profiledata.setSize(350,200);
			pan_profiledata.setBackground(Color.black);
			pan_profiledata.setBorder(BorderFactory.createLineBorder(Color.black));
			
				// Layout-Restriktionen festlegen.
				GridBagConstraints e = new GridBagConstraints();
				e.fill = GridBagConstraints.HORIZONTAL;
				e.insets = new Insets(2,2,2,2);	

				// Label und Textfelder hinzufügen
				// Username
				e.gridx = 0;
				e.gridy = 0;
				e.weightx = 0.0;
				l_username = new JLabel("Username:");
				l_username.setForeground(Color.white);
				pan_profiledata.add(l_username, e);
				
				e.gridx = 1;
				e.gridy = 0;
				e.weightx = 1.0;
				t_username = new JTextField();
				t_username.setEditable(false);
				t_username.setBackground(Color.black);
				t_username.setForeground(Color.white);
				pan_profiledata.add(t_username, e);
				
				// Vorname
				e.gridx = 0;
				e.gridy = 1;
				e.weightx = 0.0;
				l_firstname = new JLabel("Vorname:");
				l_firstname.setForeground(Color.white);
				pan_profiledata.add(l_firstname, e);
				
				e.gridx = 1;
				e.gridy = 1;
				e.weightx = 1.0;
				t_firstname = new JTextField();
				t_firstname.setBackground(Color.black);
				t_firstname.setForeground(Color.white);
				pan_profiledata.add(t_firstname, e);
				
				// Nachname
				e.gridx = 0;
				e.gridy = 2;
				e.weightx = 0.0;
				l_lastname = new JLabel("Nachname:");
				l_lastname.setForeground(Color.white);
				pan_profiledata.add(l_lastname, e);
				
				e.gridx = 1;
				e.gridy = 2;
				e.weightx = 1.0;
				t_lastname = new JTextField();
				t_lastname.setBackground(Color.black);
				t_lastname.setForeground(Color.white);
				pan_profiledata.add(t_lastname, e);
				
				// Geburtstag
				e.gridx = 0;
				e.gridy = 3;
				e.weightx = 0.0;
				l_birthdate = new JLabel("Geburtstag:");
				l_birthdate.setForeground(Color.white);
				pan_profiledata.add(l_birthdate, e);
				
				e.gridx = 1;
				e.gridy = 3;
				e.weightx = 1.0;
				t_birthdate = new JTextField();
				t_birthdate.setBackground(Color.black);
				t_birthdate.setForeground(Color.white);
				pan_profiledata.add(t_birthdate, e);
				
				// Heimatland
				e.gridx = 0;
				e.gridy = 4;
				e.weightx = 0.0;
				l_homecountry = new JLabel("Heimatland:");
				l_homecountry.setForeground(Color.white);
				pan_profiledata.add(l_homecountry, e);
				
				e.gridx = 1;
				e.gridy = 4;
				e.weightx = 1.0;
				t_homecountry = new JTextField();
				t_homecountry.setBackground(Color.black);
				t_homecountry.setForeground(Color.white);
				pan_profiledata.add(t_homecountry, e);
				
				// Einfügen des Buttons b_save
				e.gridx = 0;
				e.gridy = 5;
				e.gridwidth = 2;
				b_save = new JButton("Speichern");
				b_save.setBorder(BorderFactory.createLineBorder(Color.white));
				b_save.setBackground(Color.black);
				b_save.setForeground(Color.white);
				pan_profiledata.add(b_save, e);
				
				
			// Hinzufügen der Panels zum Panel pan_profileview
			c.gridx = 0;
			c.gridy = 0;
			c.weightx = 0.5;
			c.weighty = 0.5;
			c.gridheight = 2;
			pan_profileeditview.add(pan_likes, c);
			
			c.gridx = 1;
			c.gridy = 0;
			c.weightx = 0.5;
			c.weighty = 0.5;
			pan_profileeditview.add(pan_profilepic, c);
						
			c.gridx = 1;
			c.gridy = 1;
			c.weightx = 0.5;
			c.weighty = 0.5;
			pan_profileeditview.add(pan_profiledata, c);
			
			// Hinzufügen des Panels zur ContentPane
			add(pan_profileeditview);		
			setVisible(true);
			
		}
			
		/**
		 * Erzeugt die ProfileEditView und zeigt sie an.
		 */
		private static void createAndShowView(){
			//Erzeugen des Frames
			JFrame profileedit = new JFrame("ProfileEdit");
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
