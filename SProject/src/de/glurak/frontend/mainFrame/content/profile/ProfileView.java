package de.glurak.frontend.mainFrame.content.profile;

import javax.swing.*;
import java.awt.*;

/**
 * Die ProfileView zeigt dem User ein Profil an.
 * @author Christopher Distelkämper
 * Date: 26.02.2014
 */

public class ProfileView extends JFrame{

	// Panels
	private JPanel pan_profileview;
	private JPanel pan_profilepic;
	private JPanel pan_profiledata;
	private JPanel pan_topplaylists;
	private JPanel pan_likes;
	
	// Buttons
	private JButton b_message;
	private JButton b_follow;
	private JButton b_edit;
	private JButton b_moreplaylists;
	
	/**
	 * Constructor
	 */
	public ProfileView(){
		
		// Initialisieren Panel pan_profileview
		pan_profileview = new JPanel(new GridBagLayout());
		pan_profileview.setSize(800,500);
		
		// Layout-Restriktionen festlegen.
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(1,1,2,2);	
		
		// Initialisieren Panel pan_likes
		pan_likes = new JPanel();
		
		// Initialisieren Panel pan_profilepic
		pan_profilepic = new JPanel(new GridBagLayout());
		
			// Layout-Restriktionen festlegen.
			GridBagConstraints d = new GridBagConstraints();
			d.fill = GridBagConstraints.BOTH;
			d.insets = new Insets(1,1,2,2);	
				
		    // Initialisieren der Buttons b_message, b_follow, b_edit
			d.gridx = 0;
			d.gridy = 1;
		    b_message = new JButton("Nachricht");
		    pan_profilepic.add(b_message, d);
		    
		    d.gridx = 1;
		    d.gridy = 1;
		    b_follow = new JButton("Follow");
		    pan_profilepic.add(b_follow, d);
		    
		    d.gridx = 2;
		    d.gridy = 1;
		    b_edit = new JButton("Bearbeiten");
		    pan_profilepic.add(b_edit, d);
		
		// Initialisieren Panel pan_topplaylists
		pan_topplaylists = new JPanel();	
		
		// Initialisieren Panel pan_profiledata
		pan_profiledata = new JPanel();			
		
		// Hinzufügen der Panels zum Panel pan_profileview
		c.gridx = 0;
		c.gridy = 0;
		pan_profileview.add(pan_likes, c);
		
		c.gridx = 1;
		c.gridy = 0;
		pan_profileview.add(pan_profilepic, c);
		
		c.gridx = 0;
		c.gridy = 1;
		pan_profileview.add(pan_topplaylists);
		
		c.gridx = 1;
		c.gridy = 1;
		pan_profileview.add(pan_profiledata);
		
		// Beschaffen der ContentPane
		java.awt.Container content = getContentPane();
				
		// Hinzufügen des Panels zur ContentPane
		content.add(pan_profileview);		
		setVisible(true);
		
	}
		
}
