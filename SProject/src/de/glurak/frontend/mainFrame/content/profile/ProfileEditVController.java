package de.glurak.frontend.mainFrame.content.profile;

import java.awt.event.*;
import java.util.Observable;

import javax.swing.*;

import de.glurak.frontend.mainFrame.ContentController;
import de.glurak.frontend.mainFrame.NextContent;

/**
 * Diese Klasse stellt dem ProfileEditView die Funktionalität zur Verfügung.
 * @author Christopher Distelkämper		
 * Date: 28.02.2014
 */
public class ProfileEditVController extends Observable implements ActionListener, ContentController, NextContent {
	
	private ProfileEditView profileEditView;
	
	public ProfileEditVController(){
		
		profileEditView = new ProfileEditView();
		
		// Setzen der ActionListener
		profileEditView.b_save.addActionListener(this);
		profileEditView.b_uploadpic.addActionListener(this);
		
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
		
		if (obj == profileEditView.b_save){
			
		} else if (obj == profileEditView.b_uploadpic){
			
		}
	}

	public JComponent getView() {
		return profileEditView;
	}

	public ContentController getNextContent() {
		// TODO Auto-generated method stub
		return null;
	}

}
