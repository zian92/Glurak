package de.glurak.frontend.mainFrame.content.profile;

import javax.imageio.ImageIO;
import javax.swing.*;

import de.glurak.Query;
import de.glurak.data.User.ListenerProfile;
import de.glurak.data.User.Profile;
import de.glurak.data.User.User;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Die ProfileView zeigt dem User ein Profil an.
 * @author Christopher Distelkämper
 * Date: 26.02.2014
 */

public class ProfileView extends JPanel{

	// Panels
	private JPanel pan_profileview;
	private JPanel pan_profilepic;
	protected JPanel pan_picture;
	private JPanel pan_profiledata;
	private JPanel pan_topplaylists;
	private JPanel pan_likes;
	
	// Buttons
	protected JButton b_message;
	protected JButton b_follow;
	protected JButton b_edit;
	protected JButton b_moreplaylists;
	
	// TextFields profile_data
	protected JTextField t_password;
	protected JTextField t_firstname;
	protected JTextField t_lastname;
	protected JTextField t_email;
	protected JTextField t_homecountry;
	
	// Labels profile_data
	private JLabel l_username;
	private JLabel l_firstname;
	private JLabel l_lastname;
	private JLabel l_birthdate;
	private JLabel l_homecountry;
	private JLabel l_userPic;
	
	// TextField-Array für die Playlisten
	protected JTextField[] t_playlist;
	
	// Label-Array für die Playlisten
	private JLabel[] l_playlist;
	
	private boolean edit;
	
	
	
	/**
	 * Constructor
	 * @param own Wird das eigene Profil angezeigt oder ein anderes?
	 * @param anzPlaylists <= 5, falls ein User mehr Playlisten hat, sind diese über den "More"-Button verfügbar.
	 */
	public ProfileView(boolean own, int anzPlaylists, User user, boolean edit){
		
		
		// Initialisieren Panel pan_profileview
		pan_profileview = new JPanel(new GridBagLayout());
		pan_profileview.setPreferredSize(new Dimension(724, 545));
		pan_profileview.setBackground(Color.blue);
		
		// Layout-Restraiktionen festlegen.
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(2,2,2,2);	
		
		// Initialisieren Panel pan_likes
		pan_likes = new JPanel(new GridBagLayout());
		pan_likes.setPreferredSize(new Dimension(350,200));
		pan_likes.setBackground(Color.green);
		pan_likes.setBorder(BorderFactory.createLineBorder(Color.black));
		
		// Initialisieren Panel pan_profilepic
		pan_profilepic = new JPanel(new GridBagLayout());
		pan_profilepic.setPreferredSize(new Dimension(350, 300));
		pan_profilepic.setBackground(Color.yellow);
		pan_profilepic.setBorder(BorderFactory.createLineBorder(Color.black));
		
			// Layout-Restriktionen festlegen.
			GridBagConstraints d = new GridBagConstraints();
			d.fill = GridBagConstraints.HORIZONTAL;
			d.insets = new Insets(2,2,2,2);	
			
			// Initialisieren des Profilebildes
			d.gridx = 0;
			d.gridy = 0;
			d.gridwidth = 3;
			d.gridheight = 1;
			pan_picture = new JPanel();
			pan_picture.setPreferredSize(new Dimension(200,200));
			pan_picture.setBackground(Color.pink);
			pan_profilepic.add(pan_picture, d);
			
			BufferedImage img = null;
			try {
				img = ImageIO.read(new File(user.getProfile().getPictureFileNameOrDefaultPictureName()));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			l_userPic = new JLabel(new ImageIcon(img));
			pan_picture.add(l_userPic);
		
		    // Initialisieren der Buttons b_message, b_follow, b_edit
			
			if (own){  // Falls das eigene Profil angezeigt werden soll, nur b_edit anzeigen
				if (edit) {
					b_edit = new JButton("Save");
				} else {
					b_edit = new JButton("Bearbeiten");
				}
				d.gridx = 1;
				d.gridy = 1;
				d.gridwidth = 1;
				d.gridheight = 1;
				
				b_edit.setBackground(Color.gray);
				b_edit.setForeground(Color.white);
				pan_profilepic.add(b_edit, d);
			}
			else{      // Falls ein anderes Profil angezeigt werden soll, b_message und b_follow anzeigen
				
				d.gridx = 0;
				d.gridy = 1;
				d.gridwidth = 1;
				d.gridheight = 1;
			    b_message = new JButton("Nachricht");
			    b_message.setBackground(Color.black);
			    b_message.setForeground(Color.white);
			    pan_profilepic.add(b_message, d);
			    
			    d.gridx = 2;
			    d.gridy = 1;
			    d.gridwidth = 1;
			    d.gridheight = 1;
			    b_follow = new JButton("Follow");
			    b_follow.setBackground(Color.black);
			    b_follow.setForeground(Color.white);
			    pan_profilepic.add(b_follow, d);
			    
			}
		// Initialisieren Panel pan_topplaylists
		pan_topplaylists = new JPanel(new GridBagLayout());	
		pan_topplaylists.setPreferredSize(new Dimension(350, 200));
		pan_topplaylists.setBackground(Color.cyan);
		pan_topplaylists.setBorder(BorderFactory.createLineBorder(Color.black));
		
			// Layout-Restriktionen festlegen.
			GridBagConstraints e = new GridBagConstraints();
			e.fill = GridBagConstraints.HORIZONTAL;
			e.insets = new Insets(2,2,2,2);	
			
			l_playlist = new JLabel[5];
			t_playlist = new JTextField[5];
			
			for (int i = 1; i <= anzPlaylists; i++){
				
				// Labels und Textfelder hinzufügen
				e.gridx = 0;
				e.gridy = i-1;
				e.weightx = 0.0;
				e.gridheight = 1;
				e.gridwidth = 1;
				l_playlist[i] = new JLabel("Playlist " + i + ":");
				l_playlist[i].setForeground(Color.white);
				pan_topplaylists.add(l_playlist[i], e);
				
				e.gridx = 1;
				e.gridy = i-1;
				e.weightx = 1.0;
				e.gridheight = 1;
				e.gridwidth = 1;
				t_playlist[i] = new JTextField();
				t_playlist[i].setBackground(Color.black);
				t_playlist[i].setForeground(Color.white);
				t_playlist[i].setEditable(false);
				pan_topplaylists.add(t_playlist[i], e);
				
			}
			
			// Initialisieren des Buttons b_moreplaylists
			e.gridx = 0;
			e.gridy = anzPlaylists;
			e.gridheight = 1;
			e.gridwidth = 2;
			e.weightx = 0.0;
			b_moreplaylists = new JButton("mehr");
			b_moreplaylists.setBackground(Color.black);
			b_moreplaylists.setForeground(Color.white);
			pan_topplaylists.add(b_moreplaylists, e);
					
		// Initialisieren Panel pan_profiledata
		pan_profiledata = new JPanel(new GridBagLayout());	
		pan_profiledata.setPreferredSize(new Dimension(350, 200));
		pan_profiledata.setBackground(Color.RED);
		pan_profiledata.setBorder(BorderFactory.createLineBorder(Color.black));
		
			// Layout-Restriktionen festlegen.
			GridBagConstraints f = new GridBagConstraints();
			f.fill = GridBagConstraints.HORIZONTAL;
			f.insets = new Insets(2,2,2,2);	

			// Label und Textfelder hinzufügen
						
			// Vorname
			f.gridx = 0;
			f.gridy = 0;
			f.weightx = 0.0;
			l_firstname = new JLabel("Vorname:");
			l_firstname.setForeground(Color.white);
			pan_profiledata.add(l_firstname, f);
			
			f.gridx = 1;
			f.gridy = 0;
			f.weightx = 1.0;
			t_firstname = new JTextField(user.getProfile().getFirstname());
			t_firstname.setEditable(edit);
			t_firstname.setBackground(Color.black);
			t_firstname.setForeground(Color.white);
			pan_profiledata.add(t_firstname, f);
			
			// Nachname
			f.gridx = 0;
			f.gridy = 1;
			f.weightx = 0.0;
			l_lastname = new JLabel("Nachname:");
			l_lastname.setForeground(Color.white);
			pan_profiledata.add(l_lastname, f);
			
			f.gridx = 1;
			f.gridy = 1;
			f.weightx = 1.0;
			t_lastname = new JTextField(user.getProfile().getLastname());
			t_lastname.setEditable(edit);
			t_lastname.setBackground(Color.black);
			t_lastname.setForeground(Color.white);
			pan_profiledata.add(t_lastname, f);
			
			// email
			f.gridx = 0;
			f.gridy = 2;
			f.weightx = 0.0;
			l_birthdate = new JLabel("Email:");
			l_birthdate.setForeground(Color.white);
			pan_profiledata.add(l_birthdate, f);
			
			f.gridx = 1;
			f.gridy = 2;
			f.weightx = 1.0;
			t_email = new JTextField(user.getProfile().getEmail());
			t_email.setEditable(edit);
			t_email.setBackground(Color.black);
			t_email.setForeground(Color.white);
			pan_profiledata.add(t_email, f);
			
			// Heimatland
			f.gridx = 0;
			f.gridy = 3;
			f.weightx = 0.0;
			l_homecountry = new JLabel("Heimatland:");
			l_homecountry.setForeground(Color.white);
			pan_profiledata.add(l_homecountry, f);
			
			f.gridx = 1;
			f.gridy = 3;
			f.weightx = 1.0;
			t_homecountry = new JTextField(user.getProfile().getCountry());
			t_homecountry.setEditable(edit);
			t_homecountry.setBackground(Color.black);
			t_homecountry.setForeground(Color.white);
			pan_profiledata.add(t_homecountry, f);
			
			
			if (edit) {
				// Passwort
				f.gridx = 0;
				f.gridy = 4;
				f.weightx = 0.0;
				l_username = new JLabel("Neues Passwort:");
				l_username.setForeground(Color.white);
				pan_profiledata.add(l_username, f);
				
				f.gridx = 1;
				f.gridy = 4;
				f.weightx = 1.0;
				t_password = new JTextField();
				t_password.setEditable(edit);
				t_password.setBackground(Color.black);
				t_password.setForeground(Color.white);
				pan_profiledata.add(t_password, f);
			}
			
			
		// Hinzufügen der Panels zum Panel pan_profileview
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 0.5;
		c.weighty = 0.5;
		pan_profileview.add(pan_likes, c);
		
		c.gridx = 1;
		c.gridy = 0;
		c.weightx = 0.5;
		c.weighty = 0.0;
		pan_profileview.add(pan_profilepic, c);
		
		c.gridx = 0;
		c.gridy = 1;
		c.weightx = 0.5;
		c.weighty = 0.5;
		pan_profileview.add(pan_topplaylists, c);
		
		c.gridx = 1;
		c.gridy = 1;
		c.weightx = 0.5;
		c.weighty = 1.0;
		pan_profileview.add(pan_profiledata, c);
		
		// Hinzufügen des Panels zur ContentPane
		add(pan_profileview);		
		setVisible(true);
		
	}
	
}
