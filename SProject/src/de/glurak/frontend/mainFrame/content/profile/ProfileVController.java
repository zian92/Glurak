package de.glurak.frontend.mainFrame.content.profile;

import java.awt.event.*;
import javax.swing.*;

/**
 * Diese Klasse stellt dem ProfileView die Funktionalität zur Verfügung.
 * @author Christopher Distelkämper
 * Date: 28.02.2014
 */
public class ProfileVController implements ActionListener{
	
	private ProfileView profileview;
	
	public ProfileVController(boolean own){
		profileview = new ProfileView(own);
		
		profileview.b_moreplaylists.addActionListener(this);
		if (own){
			profileview.b_edit.addActionListener(this);
		}
		else{
			profileview.b_follow.addActionListener(this);
			profileview.b_message.addActionListener(this);
		}
	}
	
	public void actionPerformed(ActionEvent e){
		
	}

}
