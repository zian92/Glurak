package de.glurak.frontend.login;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class LoginView extends JFrame{
	
	private JTextField t_name;
	private JTextField t_passwort;
	
	public LoginView(String Titel){
		super(Titel);
		
		// set layout
		setLayout(new GridBagLayout());
		
		// create swing components
		JPanel pan_login = new JPanel();
		JPanel pan_advertisment = new JPanel();
		
		
		
		java.awt.Container content = getContentPane();
		
		// add swing components to contentPane
		
		content.add(pan_login);
		content.add(pan_advertisment);
		
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		
	}
	
}
