package de.glurak.frontend.mainFrame.content.profile;

import java.awt.event.*;
import javax.swing.*;

import de.glurak.frontend.mainFrame.ContentController;
import de.glurak.frontend.mainFrame.content.message.MessageVController;
import de.glurak.frontend.mainFrame.content.playlist.PlaylistVController;
import de.glurak.frontend.mainFrame.content.profile.ProfileEditVController;

/**
 * Diese Klasse stellt dem ProfileView die Funktionalität zur Verfügung.
 * @author Christopher Distelkämper
 * Date: 28.02.2014
 */
public class ProfileVController implements ActionListener, ContentController {
	
	private ProfileView profileview;
	
	/**
	 * Constructor
	 * @param own Wird das eigene Profil angezeigt?
	 * @param anzPlaylists <= 5, falls ein User mehr Playlisten hat sind diese über den "More"-Button verfügbar.
	 */
	public ProfileVController(boolean own, int anzPlaylists){
		profileview = new ProfileView(own, anzPlaylists);
		
		// Hinzufügen der ActionListener
		profileview.b_moreplaylists.addActionListener(this);
		if (own){
			profileview.b_edit.addActionListener(this);
		}
		else{
			profileview.b_follow.addActionListener(this);
			profileview.b_message.addActionListener(this);
		}
		
		// Daten in die Textfelder schreiben
		/*
		profileview.t_username.setText("");
		profileview.t_firstname.setText("");
		profileview.t_lastname.setText("");
		profileview.t_birthdate.setText("");
		profileview.t_homecountry.setText("");
		*/
		
	}
	
	public ProfileVController(boolean own) {
		this(own,0);
	}
	
	public void actionPerformed(ActionEvent e){
		Object obj = e.getSource();
		
		if (obj == profileview.b_moreplaylists){
	//		setContentController(new PlaylistVController());
		} else if (obj == profileview.b_message){
	//		setContentController(new MessageVController());
		} else if (obj == profileview.b_follow){
			
		} else if (obj == profileview.b_edit){
	//      setContentController(new ProfileEditVController());		
		}
	}

	public JComponent getView() {
		return profileview;
	}

}
