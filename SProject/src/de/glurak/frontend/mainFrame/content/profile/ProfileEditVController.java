package de.glurak.frontend.mainFrame.content.profile;

import java.awt.event.*;
import javax.swing.*;

/**
 * Diese Klasse stellt dem ProfileEditView die Funktionalität zur Verfügung.
 * @author Christopher Distelkämper		
 * Date: 28.02.2014
 */
public class ProfileEditVController implements ActionListener{
	
	private ProfileEditView profileEditView;
	
	public ProfileEditVController(){
		
		profileEditView = new ProfileEditView();
		
		profileEditView.b_save.addActionListener(this);
		profileEditView.b_uploadpic.addActionListener(this);
		
	}
	
	public void actionPerformed(ActionEvent e){
		
	}

}
