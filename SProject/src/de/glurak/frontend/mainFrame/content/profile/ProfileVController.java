package de.glurak.frontend.mainFrame.content.profile;

import java.awt.event.*;
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
		
		Playlist[] top5 = getTopFiveHatedPlaylists();
		profileview = new ProfileView(this.user, top5, false);
		
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
	private Playlist[] getTopFiveHatedPlaylists() {
		List<Playlist> myPlaylists = this.user.getMyPlaylists();
		Playlist temp = null;
		
		// Playlists nach most hates sortieren
		for (int i=0; i<myPlaylists.size(); i++) // bubble sort outer loop
        {
            for (int j=0; j < myPlaylists.size()-j; j++) {
                if (myPlaylists.get(j).hateCount() > myPlaylists.get(j+1).hateCount())
                {
                    temp = myPlaylists.get(j);
                    myPlaylists.set(j,myPlaylists.get(j+1) );
                    myPlaylists.set(j+1, temp);
                }
            }
        }
		Playlist[] returnArray;
		
		// die 5 top lists in returnArray schreiben
		if ( myPlaylists.size() < 5){
			returnArray = new Playlist[myPlaylists.size()];
			for (int i=0;i<myPlaylists.size();i++) {
				returnArray[i] = myPlaylists.get(i);
			}
		}else {
			returnArray = new Playlist[5];
			for (int i=0;i<5;i++) {
				returnArray[i] = myPlaylists.get(i);
			}
		}
		return returnArray;
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
			nextContent = new ProfileEditVController(this.user, getTopFiveHatedPlaylists());
			setChanged();
			notifyObservers();
		} else {
			for (int i=0;i<getTopFiveHatedPlaylists().length;i++) {
				if (obj == profileview.getB_playlistArray()[i]) {
					System.out.println(i);
				}
			}
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
