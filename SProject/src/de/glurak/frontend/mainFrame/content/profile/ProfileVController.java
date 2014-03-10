package de.glurak.frontend.mainFrame.content.profile;

import java.awt.event.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Observable;
import javax.swing.*;
import de.glurak.data.Playlist;
import de.glurak.data.User.User;
import de.glurak.frontend.SessionThing;
import de.glurak.frontend.mainFrame.ContentController;
import de.glurak.frontend.mainFrame.NextContent;
import de.glurak.frontend.mainFrame.content.message.MessageVController;
import de.glurak.frontend.mainFrame.content.profile.ProfileEditVController;

/**
 * Diese Klasse stellt dem ProfileView die Funktionalität zur Verfügung.
 * @author Christopher Distelkämper
 * Date: 28.02.2014
 */
public class ProfileVController extends Observable implements ActionListener, ContentController, NextContent {
	
	private ProfileView profileview;
	private ContentController nextContent;
	private User user;
	private boolean own;
	

	public ProfileVController (User user) {
		
		// parameter überprüfen
		if (user==null || user==SessionThing.getInstance().getSessionUser()) {
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
		
		profileview = new ProfileView(this.user, getTopFiveHatedPlaylists(), false);
		
		// Hinzufügen der ActionListener
		if (own) {
			profileview.b_edit.addActionListener(this);
		}
		else{
			profileview.b_follow.addActionListener(this);
			profileview.b_message.addActionListener(this);
		}
	}
	
	/**
	 * Holt aus den eigenen Playlists die 5 mit den meisten Hates
	 * @return Playlist[] max size 5
	 */
	public List<Playlist> getTopFiveHatedPlaylists() {
		List<Playlist> myPlaylists = this.user.getMyPlaylists();
		
		Collections.sort(myPlaylists);
		
		List<Playlist> returnList = new ArrayList<Playlist>();
		
		for (int i=0; i<myPlaylists.size(); i++) {
			if (myPlaylists.get(i)!= null) {
				returnList.add(myPlaylists.get(i));
			}
		}
		
		return returnList;
	}

	
	public void actionPerformed(ActionEvent e){
		Object obj = e.getSource();
		
		if (obj == profileview.b_message){
			nextContent = new MessageVController();
			((MessageVController) nextContent).setMessage(this.user.getUsername(), "");
			setChanged();
			notifyObservers();
		} else if (obj == profileview.b_follow){
			if (!SessionThing.getInstance().getSessionUser().getFollowing().contains(this.user)) { 
				SessionThing.getInstance().getSessionUser().follow(this.user);
			}
		} else if (obj == profileview.b_edit){
			nextContent = new ProfileEditVController();
			setChanged();
			notifyObservers();
		} else {
			
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
