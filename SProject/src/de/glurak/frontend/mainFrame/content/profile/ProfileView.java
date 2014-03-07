package de.glurak.frontend.mainFrame.content.profile;

import javax.swing.*;

import de.glurak.FrontendColors;
import de.glurak.data.Playlist;
import de.glurak.data.User.ListenerProfile;
import de.glurak.data.User.User;
import de.glurak.feature.IconLoader;
import de.glurak.frontend.SessionThing;
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
	protected JPanel pan_picture;
	private JPanel pan_profiledata;
	private JPanel pan_topplaylists;
	private JPanel pan_likes;
	
	// Buttons
	protected JButton b_message;
	protected JButton b_follow;
	protected JButton b_edit;
	protected JButton b_upload;
	protected JButton b_block;
	
	// TextFields profile_data
	protected JPasswordField t_password;
	protected JPasswordField t_passwordConfirm;
	protected JTextField t_firstname;
	protected JTextField t_lastname;
	protected JTextField t_email;
	protected JTextField t_homecountry;
	protected JTextField t_birthdate;
	
	// Labels profile_data
	private JLabel l_username;
	private JLabel l_firstname;
	private JLabel l_lastname;
	private JLabel l_email;
	private JLabel l_birthdate;
	private JLabel l_homecountry;
	private JLabel l_userPic;
	private JLabel l_password;
	private JLabel l_passwordConfirm;
	
	// TextField-Array für die Playlisten
	protected JTextField[] t_playlist;
	
	// Label-Array für die Playlisten
	private JLabel[] l_playlist;
	
	// User
	private User user;
	private JButton[] b_playlistArray;
	
	
	
	
	




	/**
	 * Constructor
	 * @param own Wird das eigene Profil angezeigt oder ein anderes?
	 * @param anzPlaylists <= 5, falls ein User mehr Playlisten hat, sind diese über den "More"-Button verfügbar.
	 */
	public ProfileView(User user, Playlist[] top5Playlists, boolean edit){
		if (user==null) {
			this.user = SessionThing.getInstance().getSessionUser();
		}
		this.user = user;
		
		
		// Initialisieren Panel pan_profileviewW
		pan_profileview = new JPanel(new GridBagLayout());
		pan_profileview.setPreferredSize(new Dimension(724, 545));
		pan_profileview.setBackground(Color.yellow);
		
		// Layout-Restraiktionen festlegen.
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(2,2,2,2);	
		
		// Initialisieren Panel pan_likes
		pan_likes = new JPanel(new GridBagLayout());
		pan_likes.setPreferredSize(new Dimension(350,200));
		pan_likes.setBackground(FrontendColors.DARK_GREY);
		
		// Initialisieren Panel pan_profilepic
		pan_profilepic = new JPanel(new GridBagLayout());
		pan_profilepic.setPreferredSize(new Dimension(350, 300));
		pan_profilepic.setBackground(FrontendColors.DARK_GREY);
		
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
		pan_picture.setBackground(FrontendColors.DARK_GREY);
		pan_profilepic.add(pan_picture, d);
		
		l_userPic = new JLabel(new IconLoader(200, 200, user.getProfile().getPictureFileNameOrDefaultPictureName()).getIcon());
		pan_picture.add(l_userPic);
	
	    // Initialisieren der Buttons b_message, b_follow, b_edit
		
		if (user== SessionThing.getInstance().getSessionUser()){  // Falls das eigene Profil angezeigt werden soll, nur b_edit anzeigen
			if (edit) {
				b_edit = new JButton("Save");
				b_upload = new JButton("Bild ändern");
				
				d.gridx = 2;
				d.gridy = 1;
				d.gridwidth = 1;
				d.gridheight = 1;
				pan_profilepic.add(b_upload, d);
			} else {
				b_edit = new JButton("Bearbeiten");
			}
			d.gridx = 1;
			d.gridy = 1;
			d.gridwidth = 1;
			d.gridheight = 1;
			pan_profilepic.add(b_edit, d);
			
			
		}
		else{      // Falls ein anderes Profil angezeigt werden soll, b_message und b_follow anzeigen
			
			d.gridx = 0;
			d.gridy = 1;
			d.gridwidth = 1;
			d.gridheight = 1;
		    b_message = new JButton("Nachricht");
		    pan_profilepic.add(b_message, d);
		    
		    d.gridx = 2;
		    d.gridy = 1;
		    d.gridwidth = 1;
		    d.gridheight = 1;
		    b_follow = new JButton("Follow");
		    pan_profilepic.add(b_follow, d);
		    
		    if (SessionThing.getInstance().getSessionUser().getProfile().roleName().equals("Admin")) {
	
		    	d.gridx = 3;
			    d.gridy = 1;
			    d.gridwidth = 1;
			    d.gridheight = 1;
			    b_follow = new JButton("Sperren");
			    pan_profilepic.add(b_block, d);
		    }
		}
		
	
		// Initialisieren Panel pan_topplaylists
		pan_topplaylists = new JPanel(new GridBagLayout());	
		pan_topplaylists.setPreferredSize(new Dimension(200, 200));
		pan_topplaylists.setBackground(FrontendColors.DARK_GREY);
				
		// Layout-Restriktionen festlegen.
		GridBagConstraints e = new GridBagConstraints();
		e.fill = GridBagConstraints.HORIZONTAL;
		e.insets = new Insets(2,2,2,2);	
		
		b_playlistArray = new JButton[top5Playlists.length];
		
		// Label hinzufügen
		JLabel l_top5 = new JLabel("Deine Top 5 most hated Playlists:");
		l_top5.setForeground(Color.white);
		e.gridx = 0;
		e.gridy = 0;
		e.weightx = 0.0;
		pan_topplaylists.add(l_top5, e);
		
		// Playlist buttons hinzufügen
		System.out.println("PV - 195 - Top5list length = " + top5Playlists.length );
		for (int i = 0; i < top5Playlists.length; i++){
			
			e.gridx = 0;
			e.gridy = i+1;
			e.weightx = 0.0;

		    e.gridwidth = 2;
		    e.gridheight = 1;
			b_playlistArray[i] = new JButton(top5Playlists[i].getName());
			pan_topplaylists.add(b_playlistArray[i], e);
		}
	
		// Initialisieren Panel pan_profiledata
		pan_profiledata = new JPanel(new GridBagLayout());	
		pan_profiledata.setPreferredSize(new Dimension(300, 200));
		pan_profiledata.setBackground(FrontendColors.DARK_GREY);
		pan_profiledata.setBorder(BorderFactory.createLineBorder(Color.black));
			
		// Layout-Restriktionen festlegen.
		GridBagConstraints f = new GridBagConstraints();
		f.fill = GridBagConstraints.HORIZONTAL;
		f.insets = new Insets(2,2,2,2);	

		// Label und Textfelder initalisieren
		JLabel l_userInfo = new JLabel("Benutzerdaten:");
		
		l_firstname = new JLabel("Vorname:");
		t_firstname = new JTextField(this.user.getProfile().getFirstname());
		t_firstname.setEditable(edit);
		
		l_lastname = new JLabel("Nachname:");
		t_lastname = new JTextField(user.getProfile().getLastname());
		t_lastname.setEditable(edit);
		
		l_birthdate = new JLabel("Geburtstag:");
        t_birthdate = new JTextField();
        if (user.getProfile() instanceof  ListenerProfile)
            t_birthdate.setText(((ListenerProfile) user.getProfile()).getBirthdate());
        t_birthdate.setEditable(false);
		
		l_email = new JLabel("Email:");
		t_email = new JTextField(user.getProfile().getEmail());
		t_email.setEditable(edit);
		
		l_homecountry = new JLabel("Heimatland:");
		t_homecountry = new JTextField(user.getProfile().getCountry());
		t_homecountry.setEditable(false);
		
		l_password = new JLabel("Neues Passwort:");
		t_password = new JPasswordField();
		t_password.setEditable(edit);
		
		l_passwordConfirm = new JLabel("Passwort bestätigen:");
		t_passwordConfirm = new JPasswordField();
		t_passwordConfirm.setEditable(edit);
		
		// Label und Textfelder ins Layout einfügen
		
		// Benutzerdaten
		f.gridx = 0;
		f.gridy = 0;
		f.weightx = 0.0;
		pan_profiledata.add(l_userInfo, f);

		// Vorname
		f.gridx = 0;
		f.gridy = 1;
		f.weightx = 0.0;
		pan_profiledata.add(l_firstname, f);
		
		f.gridx = 1;
		f.gridy = 1;
		f.weightx = 1.0;
		pan_profiledata.add(t_firstname, f);
		
		// Nachname
		f.gridx = 0;
		f.gridy = 2;
		f.weightx = 0.0;
		pan_profiledata.add(l_lastname, f);
		
		f.gridx = 1;
		f.gridy = 2;
		f.weightx = 1.0;
		pan_profiledata.add(t_lastname, f);
		
		// Geburtstag
		f.gridx = 0;
		f.gridy = 3;
		f.weightx = 0.0;
		pan_profiledata.add(l_birthdate, f);
		
		f.gridx = 1;
		f.gridy = 3;
		f.weightx = 1.0;
		pan_profiledata.add(t_birthdate, f);


		// Heimatland
		f.gridx = 0;
		f.gridy = 4;
		f.weightx = 0.0;
		pan_profiledata.add(l_homecountry, f);
		
		f.gridx = 1;
		f.gridy = 4;
		f.weightx = 1.0;
		pan_profiledata.add(t_homecountry, f);
		
		// email
		f.gridx = 0;
		f.gridy = 5;
		f.weightx = 0.0;
		pan_profiledata.add(l_email, f);
		
		f.gridx = 1;
		f.gridy = 5;
		f.weightx = 1.0;
		pan_profiledata.add(t_email, f);
				
		if (edit) {
			// Passwort
			f.gridx = 0;
			f.gridy = 6;
			f.weightx = 0.0;
			pan_profiledata.add(l_password, f);
			
			f.gridx = 1;
			f.gridy = 6;
			f.weightx = 1.0;
			pan_profiledata.add(t_password, f);
			
			// Passwort
			f.gridx = 0;
			f.gridy = 7;
			f.weightx = 0.0;
			pan_profiledata.add(l_passwordConfirm, f);
			
			f.gridx = 1;
			f.gridy = 7;
			f.weightx = 1.0;
			pan_profiledata.add(t_passwordConfirm, f);
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




	public JPanel getPan_profileview() {
		return pan_profileview;
	}




	public void setPan_profileview(JPanel pan_profileview) {
		this.pan_profileview = pan_profileview;
	}




	public JPanel getPan_profilepic() {
		return pan_profilepic;
	}




	public void setPan_profilepic(JPanel pan_profilepic) {
		this.pan_profilepic = pan_profilepic;
	}




	public JPanel getPan_picture() {
		return pan_picture;
	}




	public void setPan_picture(JPanel pan_picture) {
		this.pan_picture = pan_picture;
	}




	public JPanel getPan_profiledata() {
		return pan_profiledata;
	}




	public void setPan_profiledata(JPanel pan_profiledata) {
		this.pan_profiledata = pan_profiledata;
	}




	public JPanel getPan_topplaylists() {
		return pan_topplaylists;
	}




	public void setPan_topplaylists(JPanel pan_topplaylists) {
		this.pan_topplaylists = pan_topplaylists;
	}




	public JPanel getPan_likes() {
		return pan_likes;
	}




	public void setPan_likes(JPanel pan_likes) {
		this.pan_likes = pan_likes;
	}




	public JButton getB_message() {
		return b_message;
	}




	public void setB_message(JButton b_message) {
		this.b_message = b_message;
	}




	public JButton getB_follow() {
		return b_follow;
	}




	public void setB_follow(JButton b_follow) {
		this.b_follow = b_follow;
	}




	public JButton getB_edit() {
		return b_edit;
	}




	public void setB_edit(JButton b_edit) {
		this.b_edit = b_edit;
	}




	public JPasswordField getT_password() {
		return t_password;
	}




	public void setT_password(JPasswordField t_password) {
		this.t_password = t_password;
	}




	public JPasswordField getT_passwordConfirm() {
		return t_passwordConfirm;
	}




	public void setT_passwordConfirm(JPasswordField t_passwordConfirm) {
		this.t_passwordConfirm = t_passwordConfirm;
	}




	public JTextField getT_firstname() {
		return t_firstname;
	}




	public void setT_firstname(JTextField t_firstname) {
		this.t_firstname = t_firstname;
	}




	public JTextField getT_lastname() {
		return t_lastname;
	}




	public void setT_lastname(JTextField t_lastname) {
		this.t_lastname = t_lastname;
	}




	public JTextField getT_email() {
		return t_email;
	}




	public void setT_email(JTextField t_email) {
		this.t_email = t_email;
	}




	public JTextField getT_homecountry() {
		return t_homecountry;
	}




	public void setT_homecountry(JTextField t_homecountry) {
		this.t_homecountry = t_homecountry;
	}




	public JTextField getT_birthdate() {
		return t_birthdate;
	}




	public void setT_birthdate(JTextField t_birthdate) {
		this.t_birthdate = t_birthdate;
	}




	public JLabel getL_username() {
		return l_username;
	}




	public void setL_username(JLabel l_username) {
		this.l_username = l_username;
	}




	public JLabel getL_firstname() {
		return l_firstname;
	}




	public void setL_firstname(JLabel l_firstname) {
		this.l_firstname = l_firstname;
	}




	public JLabel getL_lastname() {
		return l_lastname;
	}




	public void setL_lastname(JLabel l_lastname) {
		this.l_lastname = l_lastname;
	}




	public JLabel getL_email() {
		return l_email;
	}




	public void setL_email(JLabel l_email) {
		this.l_email = l_email;
	}




	public JLabel getL_birthdate() {
		return l_birthdate;
	}




	public void setL_birthdate(JLabel l_birthdate) {
		this.l_birthdate = l_birthdate;
	}




	public JLabel getL_homecountry() {
		return l_homecountry;
	}




	public void setL_homecountry(JLabel l_homecountry) {
		this.l_homecountry = l_homecountry;
	}




	public JLabel getL_userPic() {
		return l_userPic;
	}




	public void setL_userPic(JLabel l_userPic) {
		this.l_userPic = l_userPic;
	}




	public JLabel getL_password() {
		return l_password;
	}




	public void setL_password(JLabel l_password) {
		this.l_password = l_password;
	}




	public JLabel getL_passwordConfirm() {
		return l_passwordConfirm;
	}




	public void setL_passwordConfirm(JLabel l_passwordConfirm) {
		this.l_passwordConfirm = l_passwordConfirm;
	}




	public JTextField[] getT_playlist() {
		return t_playlist;
	}




	public void setT_playlist(JTextField[] t_playlist) {
		this.t_playlist = t_playlist;
	}




	public JLabel[] getL_playlist() {
		return l_playlist;
	}




	public void setL_playlist(JLabel[] l_playlist) {
		this.l_playlist = l_playlist;
	}




	public User getUser() {
		return user;
	}




	public void setUser(User user) {
		this.user = user;
	}




	public JButton[] getB_playlistArray() {
		return b_playlistArray;
	}




	public void setB_playlistArray(JButton[] b_playlistArray) {
		this.b_playlistArray = b_playlistArray;
	}
	
	public JButton getB_upload() {
		return b_upload;
	}




	public void setB_upload(JButton b_upload) {
		this.b_upload = b_upload;
	}

}
