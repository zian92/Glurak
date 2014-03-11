package de.glurak.frontend.mainFrame.content.profile;

import javax.swing.*;

import de.glurak.FrontendColors;
import de.glurak.data.Playlist;
import de.glurak.data.User.ArtistProfile;
import de.glurak.data.User.User;
import de.glurak.feature.IconLoader;
import de.glurak.frontend.SessionThing;
import java.awt.*;
import java.util.List;

/**
 * Die ProfileView zeigt dem User ein Profil an.
 * @author Christopher Distelkämper
 * Date: 26.02.2014
 */
public class LabelProfileView extends JPanel{

	// Panels
	private JPanel pan_profileview;
	private JPanel pan_profilepic;
	protected JPanel pan_picture;
	private JPanel pan_topplaylists;
	private JPanel pan_likes;
	private JPanel pan_artists;
	private JPanel pan_labeldescription;
	
	// Buttons
	protected JButton b_message;
	protected JButton b_application;
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
	protected JTextArea t_labeldescription;
	
	// Labels profile_data
	private JLabel l_labelPic;
	
	// TextField-Array für die Playlisten
	protected JTextField[] t_playlist;
	
	// Label-Array für die Playlisten
	
	// User
	private User user;
	private JButton[] b_playlistArray;

	private JButton[] b_artistArray;
	

	/**
	 * Constructor
	 * @param own Wird das eigene Profil angezeigt oder ein anderes?
	 * @param anzPlaylists <= 5, falls ein User mehr Playlisten hat, sind diese über den "More"-Button verfügbar.
	 */
	public LabelProfileView(de.glurak.data.User.Label label, List<Playlist> top5Albums, List<ArtistProfile> top5Artists, boolean edit){
		if (user==null) {
			this.user = SessionThing.getInstance().getSessionUser();
		}
		
		
		// Initialisieren Panel pan_profileviewW
		pan_profileview = new JPanel(new GridBagLayout());
		pan_profileview.setPreferredSize(new Dimension(724, 545));
		pan_profileview.setBackground(FrontendColors.DARK_GREY);
		
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
		
		
		// Label beschreibeung hinzufügen
		GridBagConstraints h = new GridBagConstraints();
		h.fill = GridBagConstraints.HORIZONTAL;
		h.insets = new Insets(2,2,2,2);	
	
		h.gridx = 0;
		h.gridy = 0;
		h.gridwidth = 1;
		h.gridheight = 1;
	    t_labeldescription = new JTextArea("Hier Labelbeschreibung einfügen", 10, 50);
	    t_labeldescription.setMaximumSize(new Dimension(350,200));
	    t_labeldescription.setMinimumSize(new Dimension(350,200));
	    t_labeldescription.setBackground(FrontendColors.DARK_GREY);
	    t_labeldescription.setForeground(Color.white);
		t_labeldescription.setEditable(edit);
	    pan_likes.add(t_labeldescription, h);
		
		
		
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
		
		l_labelPic = new JLabel(new IconLoader(200, 200, label.getProfile().getPictureFileNameOrDefaultPictureName()).getIcon());
		pan_picture.add(l_labelPic);
		
		d.gridx = 0;
		d.gridy = 1;
		d.gridwidth = 1;
		d.gridheight = 1;
	    b_application = new JButton("Bewerbung");
	    pan_profilepic.add(b_application, d);
	
	
		// Initialisieren Panel pan_topplaylists
		pan_topplaylists = new JPanel(new GridBagLayout());	
		pan_topplaylists.setPreferredSize(new Dimension(200, 200));
		pan_topplaylists.setBackground(FrontendColors.DARK_GREY);
				
		// Layout-Restriktionen festlegen.
		GridBagConstraints e = new GridBagConstraints();
		e.fill = GridBagConstraints.HORIZONTAL;
		e.insets = new Insets(2,2,2,2);	
		
		b_playlistArray = new JButton[top5Albums.size()];
		
		// Label hinzufügen
		JLabel l_top5 = new JLabel("Top 5 most hated Albums:");
		l_top5.setForeground(Color.white);
		e.gridx = 0;
		e.gridy = 0;
		e.weightx = 0.0;
		pan_topplaylists.add(l_top5, e);
		
		
		// Album buttons hinzufügen
		for (int i = 0; i < top5Albums.size(); i++){
			
			e.gridx = 0;
			e.gridy = i+1;
			e.weightx = 0.0;

		    e.gridwidth = 2;
		    e.gridheight = 1;
			b_playlistArray[i] = new JButton(top5Albums.get(i).getName());
			pan_topplaylists.add(b_playlistArray[i], e);
		}
	
		
		
		
		
		
		
		// Initialisieren Panel pan_profiledata
		pan_artists = new JPanel(new GridBagLayout());	
		pan_artists.setPreferredSize(new Dimension(300, 200));
		pan_artists.setBackground(FrontendColors.DARK_GREY);
			
		// Layout-Restriktionen festlegen.
		GridBagConstraints f = new GridBagConstraints();
		f.fill = GridBagConstraints.HORIZONTAL;
		f.insets = new Insets(2,2,2,2);	
		
		b_artistArray = new JButton[top5Artists.size()];
		
		// Label hinzufügen
		JLabel l_top5Artists = new JLabel("Top 5 most hated Artists:");
		l_top5Artists.setForeground(Color.white);
		f.gridx = 0;
		f.gridy = 0;
		f.weightx = 0.0;
		pan_artists.add(l_top5Artists, f);
		
		// Artist buttons hinzufügen
		for (int i = 0; i < top5Artists.size(); i++){
			
			f.gridx = 0;
			f.gridy = i+1;
			f.weightx = 0.0;
		    f.gridwidth = 2;
		    f.gridheight = 1;
		    b_artistArray[i] = new JButton(top5Artists.get(i).belongTo().getUsername());
			pan_artists.add(b_artistArray[i], f);
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
		pan_profileview.add(pan_artists, c);
		
		// Hinzufügen des Panels zur ContentPane
		add(pan_profileview);		
		setVisible(true);
		
	}
	
	public JButton[] getB_artistArray() {
		return b_artistArray;
	}
	public void setB_artistArray(JButton[] b_artistArray) {
		this.b_artistArray = b_artistArray;
	}
	
	public JButton[] getB_playlistArray() {
		return b_playlistArray;
	}

	public void setB_playlistArray(JButton[] b_playlistArray) {
		this.b_playlistArray = b_playlistArray;
	}

}
