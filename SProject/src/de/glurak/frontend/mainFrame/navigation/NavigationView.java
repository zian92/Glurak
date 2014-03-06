package de.glurak.frontend.mainFrame.navigation;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;


/**
 * In der Navigationview werden das Profilbild des Users und seine Funktionen,
 * wie z.B. Profil bearbeiten, und seine Playlists angezeigt.
 * @author Simon, Zengo
 *
 */
public class NavigationView extends JPanel{

	private JPanel profilePicture, buttonPanel;
	private JLabel userPicture, userName;
    private ActionListener l;
	
	/**
	 * Konstruktor
     * @param l der Aktionlistener für die Buttons, null falls keine Aktion
     * @param username der Benutzername der angezeigt werden soll
     * @param profilPicture das Bild das angezeigt werden soll
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

		JPanel northPanel = new JPanel();
        northPanel.setLayout(new BorderLayout());
		userPicture = new JLabel(profilPicture);
		profilePicture.add(userPicture);
        northPanel.add(userPicture,BorderLayout.CENTER);

        userName=new JLabel(username);
        userName.setForeground(Color.WHITE);
        userName.setHorizontalAlignment(JLabel.CENTER);
        northPanel.add(userName,BorderLayout.SOUTH);
        northPanel.setBackground(Color.BLACK);
		
		this.add(northPanel, BorderLayout.NORTH);
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
