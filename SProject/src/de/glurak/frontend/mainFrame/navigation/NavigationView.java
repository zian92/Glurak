package de.glurak.frontend.mainFrame.navigation;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;

import de.glurak.Query;
import de.glurak.frontend.SessionThing;

/**
 * In der Navigationview werden das Profilbild des Users und seine Funktionen,
 * wie z.B. Profil bearbeiten, und seine Playlists angezeigt.
 * @author Simon, Zengo
 *
 */
public class NavigationView extends JPanel{

	private JPanel profilePicture, buttonPanel;
	private JLabel userPicture;
    private ActionListener l;
	
	/**
	 * Konstruktor
     * @param l der Aktionlistener für die Buttons, null falls keine Aktion
	 */
	public NavigationView(ActionListener l, String username, ImageIcon profilPicture){
        this.l=l;

		
		buttonPanel = new JPanel();
		
		profilePicture = new JPanel();
		profilePicture.setPreferredSize(new Dimension(200,200));
		profilePicture.setBackground(Color.BLACK);
		
		//Layout des Panels
		this.setLayout(new BorderLayout());
        GridLayout lay= new GridLayout(10,5);
		buttonPanel.setLayout(lay);

		
		userPicture = new JLabel(profilPicture);
		profilePicture.add(userPicture);
		
		this.add(profilePicture, BorderLayout.NORTH);
		this.add(buttonPanel, BorderLayout.CENTER);

	}

    public void addButton(String name){
        JButton b = new JButton(name);
        b.setActionCommand(name);
        if (l!=null)
            b.addActionListener(l);
        buttonPanel.add(b);
    }

	public JPanel getProfilePicture() {
		return profilePicture;
	}

	public void setProfilePicture(JPanel profilPicture) {
		this.profilePicture = profilPicture;
	}
	
}
