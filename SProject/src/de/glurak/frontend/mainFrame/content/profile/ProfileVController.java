package de.glurak.frontend.mainFrame.content.profile;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Observable;

import javax.swing.JComponent;

import de.glurak.data.Playlist;
import de.glurak.data.User.Rights;
import de.glurak.data.User.User;
import de.glurak.frontend.SessionThing;
import de.glurak.frontend.mainFrame.ContentController;
import de.glurak.frontend.mainFrame.NextContent;
import de.glurak.frontend.mainFrame.content.message.MessageVController;
import de.glurak.frontend.mainFrame.content.playlist.PlaylistEditVController;

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
	private SessionThing session;
	public ProfileVController (User user) {
		session=SessionThing.getInstance();
		// parameter überprüfen
		if (user==null) {
			this.user = session.getSessionUser();
			own = true;
		} else {
			// überprüfen ob es das eigene Profil ist
			if (user==session.getSessionUser()) {
				own = true;
				this.user = user;
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
		if (own && session.getSessionUser().getProfile().hasRight(Rights.LOCK_OTHER_USER)) {
		    profileview.b_block.setVisible(false);
		} else {
		    profileview.b_block.addActionListener(this);
		    profileview.b_block.setVisible(true);
		}
		
		for (int i=0;i<profileview.b_playlistArray.length; i++) {
			profileview.b_playlistArray[i].addActionListener(this);
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

	
    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();

        if (obj == profileview.b_message) {
            nextContent = new MessageVController();
            ((MessageVController) nextContent).setMessage(this.user.getUsername(), "");
            setChanged();
            notifyObservers();
        } else
            if (obj == profileview.b_follow) {
                if (!session.getSessionUser().getFollowing().contains(this.user)) {
                    // follow
                    session.getSessionUser().follow(this.user);
                    this.profileview.setFollowButtonToUnfollow();
                } else {
                    // unfollow
                    session.getSessionUser().getFollowing().remove(this.user);
                    this.profileview.setFollowButtonToFollow();
                }
            } else
                if (obj == profileview.b_edit) {
                    nextContent = new ProfileEditVController(this.user);
                    setChanged();
                    notifyObservers();
                } else
                    if (obj == profileview.b_block) {
                        if (user.isLocked()) {
                            // entsperren
                            user.setLocked(false);
                            this.profileview.setBlockButtontToBock();
                        } else {
                            // sperren
                            user.setLocked(true);
                            this.profileview.setBlockButtonToUnblock();
                        }
                    } else {
                        for (int i = 0; i < profileview.b_playlistArray.length; i++) {
                            if (obj == profileview.b_playlistArray[i]) {
                                nextContent = new PlaylistEditVController(getTopFiveHatedPlaylists().get(i), this);
                                setChanged();
                                notifyObservers();
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

	public void reload() {
		// TODO Auto-generated method stub
		
	}

}
