package de.glurak.frontend.mainFrame.navigation;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneLayout;

import de.glurak.Query;

/**
 * In der Navigationview werden das Profilbild des Users und seine Funktionen,
 * wie z.B. Profil bearbeiten, und seine Playlists angezeigt.
 * @author Simon
 *
 */
public class NavigationView extends JPanel{

	private JPanel profilePicture, buttonPanel;
	private JLabel userPicture;
	private JButton editProfile, showPlaylists, showNews, showMessages, upload;
	private JScrollPane scrollbar;
	
	/**
	 * Konstruktor
	 */
	public NavigationView(){
		
		editProfile = new JButton("Profil");
		showPlaylists = new JButton("Playlist");
		showNews = new JButton("News");
		showMessages = new JButton("Nachrichten");
		upload  = new JButton("Upload");
		
		buttonPanel = new JPanel();
		
		profilePicture = new JPanel();
		profilePicture.setPreferredSize(new Dimension(200,200));
		profilePicture.setBackground(Color.BLACK);
		
		//Layout des Panels
		this.setLayout(new BorderLayout());
		buttonPanel.setLayout(new GridLayout(10,5));
		buttonPanel.add(showNews);
		buttonPanel.add(editProfile);
		buttonPanel.add(showPlaylists);
		buttonPanel.add(showMessages);
		
		
		BufferedImage img = null;
		try {
			img = ImageIO.read(new File(Query.FOLDER_PICTURE_ICONS+"userm.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		userPicture = new JLabel(new ImageIcon(img));
		profilePicture.add(userPicture);
		
		this.add(profilePicture, BorderLayout.NORTH);
		this.add(buttonPanel, BorderLayout.CENTER);

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
