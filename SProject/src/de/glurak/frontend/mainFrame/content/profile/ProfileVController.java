package de.glurak.frontend.mainFrame.content.profile;

import java.awt.event.*;
import java.util.Observable;

import javax.swing.*;

import org.hibernate.Session;

import de.glurak.data.User.ListenerProfile;
import de.glurak.data.User.Profile;
import de.glurak.data.User.User;
import de.glurak.data.User.UserProfile;
import de.glurak.frontend.SessionThing;
import de.glurak.frontend.mainFrame.ContentController;
import de.glurak.frontend.mainFrame.NextContent;
import de.glurak.frontend.mainFrame.content.message.MessageVController;
import de.glurak.frontend.mainFrame.content.playlist.PlaylistVController;
import de.glurak.frontend.mainFrame.content.profile.ProfileEditVController;

/**
 * Diese Klasse stellt dem ProfileView die Funktionalität zur Verfügung.
 * @author Christopher Distelkämper
 * Date: 28.02.2014
 */
public class ProfileVController extends Observable implements ActionListener, ContentController, NextContent {
	
	private ProfileView profileview;
	private ListenerProfile profile;
	private ContentController nextContent;
	private User user;
	
	/**
	 * Constructor
	 * @param own der Nutzer der angezeigt wird. Falls null der aktuelel User
	 * @param anzPlaylists <= 5, falls ein User mehr Playlisten hat sind diese über den "More"-Button verfügbar.
	 */
	public ProfileVController(User own, int anzPlaylists){
		
		if (own==null) {
			user = SessionThing.getInstance().getSessionUser();
            own = user;
		} else {
			user = own;
		}
		
		profileview = new ProfileView(user, anzPlaylists, false);
		
		// Hinzufügen der ActionListener
		profileview.b_moreplaylists.addActionListener(this);
		if (own==null||own==SessionThing.getInstance().getSessionUser()){
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
	
	public ProfileVController(User own) {
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
			nextContent = new ProfileEditVController(user);
			setChanged();
			notifyObservers();
		}
	}

	public JComponent getView() {
		return profileview;
	}

	public ContentController getNextContent() {
		return nextContent;
	}

	public void setNextContent(ContentController nextContent) {
		this.nextContent = nextContent;
	}

}
