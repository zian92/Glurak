package de.glurak.frontend.mainFrame.navigation;

import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.JButton;

/**
 * In der Navigationview werden das Profilbild des Users und seine Funktionen,
 * wie z.B. Profil bearbeiten, und seine Playlists angezeigt.
 * @author Simon
 *
 */
public class NavigationView extends JPanel{

	private JPanel profilePicture;
	private JButton editProfile, showPlaylists, showNews, showMessages, upload;
	
	/**
	 * Konstruktor
	 */
	public NavigationView(){
		
		editProfile = new JButton("Profil");
		showPlaylists = new JButton("Playlist");
		showNews = new JButton("News");
		showMessages = new JButton("Nachrichten");
		upload  = new JButton("Upload");
		profilePicture = new JPanel();
		
		//Layout des Panels
		this.setLayout(new GridLayout(10,1));
		
		this.add(editProfile);
		this.add(showPlaylists);
		this.add(showNews);
		this.add(showMessages);
		this.add(upload);
		this.add(profilePicture);
		
	}

	public JPanel getProfilePicture() {
		return profilePicture;
	}

	public void setProfilePicture(JPanel profilPicture) {
		this.profilePicture = profilPicture;
	}

	public JButton getEditProfile() {
		return editProfile;
	}

	public void setEditProfile(JButton editProfile) {
		this.editProfile = editProfile;
	}

	public JButton getShowPlaylists() {
		return showPlaylists;
	}

	public void setShowPlaylists(JButton showPlaylists) {
		this.showPlaylists = showPlaylists;
	}

	public JButton getShowNews() {
		return showNews;
	}

	public void setShowNews(JButton showNews) {
		this.showNews = showNews;
	}

	public JButton getShowMessages() {
		return showMessages;
	}

	public void setShowMessages(JButton showMessages) {
		this.showMessages = showMessages;
	}

	public JButton getUpload() {
		return upload;
	}

	public void setUpload(JButton upload) {
		this.upload = upload;
	}
	
	
}
