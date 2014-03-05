package de.glurak.frontend.login;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;

import de.glurak.frontend.mainFrame.MainFrameVController;
import de.glurak.frontend.registration.RegistrationVController;

public class LoginVController implements ActionListener, WindowListener {

	private LoginView startLoginScreen;
	private LoginSLiderController con_slider;

	public LoginVController(String viewTitel) {
		// build the view
		this.startLoginScreen = new LoginView(viewTitel);
		startLoginScreen.setSize(1024, 700);
		con_slider = new LoginSLiderController();
		startLoginScreen.getSliderPanel().add(con_slider.getView(),
				BorderLayout.CENTER);
		startLoginScreen.getBt_login().addActionListener(this);
		startLoginScreen.addWindowListener(this);
		startLoginScreen.getBt_register().addActionListener(this);
		// startLoginScreen.add(con_slider.getView());
		// startLoginScreen.setSliderPanel(con_slider.getView());

	}

	public JFrame getView() {
		return startLoginScreen;
	}

	public static boolean authenticate(String username, String passwort) {

		// check if User with this Username exists
		/*
		 * User u = new User(); try{ u = XXX.getUserByUsername(username); //
		 * creates a User instance from DB Entries }catch (NoUserFoundException
		 * e){ return false; }
		 */
		// check the password matches the USername
		/*
		 * u.checkPassword(passwort);
		 */
		return true;
	}

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == startLoginScreen.getBt_login()) {
			startLoginScreen.dispose();
			MainFrameVController mainController = new MainFrameVController();
		} else if (e.getSource() == startLoginScreen.getBt_register()) {
			RegistrationVController regvcon = new RegistrationVController();
			regvcon.getView().setBounds(600,150,400,320);
			startLoginScreen.getPanContent().add(regvcon.getView(), JLayeredPane.PALETTE_LAYER+1, 0);
		}
	}

	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	public void windowOpened(WindowEvent e) {
		java.util.TimerTask action = new java.util.TimerTask() {
			@Override
			public void run() {
				System.out.println(" LoginVController calling slide()");
				if (con_slider.slideAble()) {
					con_slider.slide();
				}
			}
		};
		java.util.Timer ankurbler = new java.util.Timer();
		ankurbler.schedule(action, 1000, 5000);
	}

	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}

}
