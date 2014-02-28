package de.glurak.frontend.login;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import de.glurak.feature.SliderPanelController;
import de.glurak.frontend.mainFrame.MainFrameVController;

public class LoginVController implements ActionListener {

	private LoginView startLoginScreen;
	
	public LoginVController(String viewTitel){
		// build the view
		this.startLoginScreen = new LoginView(viewTitel);
		startLoginScreen.setSize(1024, 700);
		SliderPanelController con_slider = new SliderPanelController();
		startLoginScreen.getSliderPanel().add(con_slider.getView(), BorderLayout.CENTER);
		startLoginScreen.getBt_login().addActionListener(this);
		//startLoginScreen.add(con_slider.getView());
		//startLoginScreen.setSliderPanel(con_slider.getView());
	
	}
	
	public JFrame getView(){
		return startLoginScreen;
	}
	public static boolean authenticate(String username, String passwort){
		
		// check if User with this Username exists
		/*
		 * User u = new User();
		 * try{
		 * 		u = XXX.getUserByUsername(username); // creates a User instance from DB Entries
		 * }catch (NoUserFoundException e){
		 * 		return false;
		 * }
		 */
		// check the password matches the USername
		/*
		 * u.checkPassword(passwort);
		 */
		return true;
	}
	
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		MainFrameVController mainController = new MainFrameVController();
		
	}

}
