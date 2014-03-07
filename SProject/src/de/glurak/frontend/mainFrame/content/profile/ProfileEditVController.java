package de.glurak.frontend.mainFrame.content.profile;

import java.awt.event.*;
import java.security.NoSuchAlgorithmException;
import java.util.Observable;

import javax.swing.*;

import de.glurak.data.User.Profile;
import de.glurak.data.User.User;
import de.glurak.frontend.mainFrame.ContentController;
import de.glurak.frontend.mainFrame.NextContent;

/**
 * Diese Klasse stellt dem ProfileEditView die Funktionalität zur Verfügung.
 * @author Christopher Distelkämper		
 * Date: 28.02.2014
 */
public class ProfileEditVController extends Observable implements ActionListener, ContentController, NextContent {
	
	private ProfileView profileEditView;
	private ContentController nextContent;
	private User user;
	
	public ProfileEditVController(User user){
		
		this.user = user;
		profileEditView = new ProfileView(user, 0,  true);
		
		// Setzen der ActionListener
		profileEditView.b_edit.addActionListener(this);
//		profileEditView.b_uploadpic.addActionListener(this);
		
		// Daten in die Textfelder schreiben.
		/*
		 profileEditView.t_username.setText("");
		 profileEditView.t_firstname.setText("");
		 profileEditView.t_lastname.setText("");
		 profileEditView.t_birthdate.setText("");
		 profileEditView.t_homecountry.setText("");
		*/
	}
	
	public void actionPerformed(ActionEvent e){
		Object obj = e.getSource();
		
		if (obj == profileEditView.b_edit){
			
			try {
				if (!profileEditView.t_password.getText().isEmpty()) {
					user.setPassword(profileEditView.t_password.getText());
				}
			} catch (NoSuchAlgorithmException e1) {
				e1.printStackTrace();
			}
			user.getProfile().setFirstname(profileEditView.t_firstname.getText());
			user.getProfile().setLastname(profileEditView.t_lastname.getText());
			user.getProfile().setEmail(profileEditView.t_email.getText());
			user.getProfile().setCountry(profileEditView.t_homecountry.getText());
			nextContent = new ProfileVController(null);
			setChanged();
			notifyObservers();
		} 
//		else if (obj == profileEditView.b_uploadpic){
//			// TODO
//		}
	}

	public JComponent getView() {
		return profileEditView;
	}

	public ContentController getNextContent() {
		return nextContent;
	}

}
