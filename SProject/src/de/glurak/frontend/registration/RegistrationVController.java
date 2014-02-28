package de.glurak.frontend.registration;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;

public class RegistrationVController implements ActionListener{

	private RegistrationView regview;
	
	/**
	 * Konstruktor
	 */
	public RegistrationVController(){
		regview = new RegistrationView();
		regview.b_cancel.addActionListener(this);
		regview.b_register.addActionListener(this);
		JFrame regframe = new JFrame("Test");
		regframe.add(regview);
		regframe.setVisible(true);
		regframe.pack();
	}
	
	public void actionPerformed(ActionEvent e){
		if (e.getSource() == regview.b_register){
			regview.t_username.setText("Test register");
		}
		else{
			if(e.getSource() == regview.b_cancel){
				regview.t_username.setText("Test cancel");
			}
		}
	}

	public static void main(String[] args){
		RegistrationVController rwc = new RegistrationVController();
	}
	
}
