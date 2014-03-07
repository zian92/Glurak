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
	private boolean own;
	
	/**
	 * Constructor
	 * @param own der Nutzer der angezeigt wird. Falls null der aktuelel User
	 * @param anzPlaylists <= 5, falls ein User mehr Playlisten hat sind diese über den "More"-Button verfügbar.
	 */
	public ProfileVController(User user, int anzPlaylists){
		
		// parameter überprüfen
		if (user==null) {
			this.user = SessionThing.getInstance().getSessionUser();
			own = true;
		} else {
			// überprüfen ob es das eigene Profil ist
			if (user==SessionThing.getInstance().getSessionUser()) {
				own = true;
			} else {
				own = false;
				this.user = user;
			}
		}
		
		profileview = new ProfileView(this.user, own, anzPlaylists, false);
		
		// Hinzufügen der ActionListener
		if (own) {
			profileview.b_edit.addActionListener(this);
		}
		else{
			profileview.b_follow.addActionListener(this);
			profileview.b_message.addActionListener(this);
		}
	}
	
	public ProfileVController(User own) {
		this(own,0);
	}
	
	public void actionPerformed(ActionEvent e){
		Object obj = e.getSource();
		
		if (obj == profileview.b_message){
//			nextContent = new MessageVController(this.user);
			setChanged();
			notifyObservers();
		} else if (obj == profileview.b_follow){
			SessionThing.getInstance().getSessionUser().follow(this.user);
			System.out.println(SessionThing.getInstance().getSessionUser().getFollowing().size());
		} else if (obj == profileview.b_edit){
			nextContent = new ProfileEditVController(this.user);
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
