package de.glurak.frontend.mainFrame.content.profile;

import java.awt.event.*;
import javax.swing.*;

import de.glurak.frontend.mainFrame.ContentController;

/**
 * Diese Klasse stellt dem ProfileView die Funktionalität zur Verfügung.
 * @author Christopher Distelkämper
 * Date: 28.02.2014
 */
public class ProfileVController implements ActionListener, ContentController {
	
	private ProfileView profileview;
	
	public ProfileVController(boolean own){
		profileview = new ProfileView(own);
		
		// Hinzufügen der ActionListener
		profileview.b_moreplaylists.addActionListener(this);
		if (own){
			profileview.b_edit.addActionListener(this);
		}
		else{
			profileview.b_follow.addActionListener(this);
			profileview.b_message.addActionListener(this);
		}
		
		// Daten in die Textfelder schreiben
		/*
		profileview.t_username.setText("");
		profileview.t_firstname.setText("");
		profileview.t_lastname.setText("");
		profileview.t_birthdate.setText("");
		profileview.t_homecountry.setText("");
		*/
		
	}
	
	public void actionPerformed(ActionEvent e){
		
	}

	public JComponent getView() {
		return profileview;
	}

}
