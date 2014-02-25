package de.glurak;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import de.glurak.frontend.login.LoginView;

public class GlurakStarter {

	
	
	public GlurakStarter() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Dis is Gluwrak");
		
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				//Create GUI
				LoginView frame_Login = new LoginView();
				frame_Login.setSize(500,500);
			}
		});
		
	}

}
