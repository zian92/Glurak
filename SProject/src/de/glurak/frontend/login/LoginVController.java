package de.glurak.frontend.login;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.security.NoSuchAlgorithmException;

import javax.swing.*;

import de.glurak.data.User.User;
import de.glurak.frontend.SessionThing;
import de.glurak.frontend.mainFrame.MainFrameVController;
import de.glurak.frontend.registration.RegistrationVController;

public class LoginVController implements ActionListener, WindowListener {

    private LoginView 				startLoginScreen;
    private LoginSLiderController 	con_slider;
    private java.util.Timer 		timer_slider; 

    public LoginVController(String viewTitel) {
        // build the view
        this.startLoginScreen = new LoginView(viewTitel, this);
        startLoginScreen.setSize(1024, 700);
        con_slider = new LoginSLiderController();
        startLoginScreen.getSliderPanel().add(con_slider.getView(),
                BorderLayout.CENTER);
        startLoginScreen.addWindowListener(this);


        // startLoginScreen.add(con_slider.getView());
        // startLoginScreen.setSliderPanel(con_slider.getView());

    }

    public JFrame getView() {
        return startLoginScreen;
    }

    /**
     * Prüft nach ob der Benutzer in der Datenbank registriert ist und das
     * Password korrekt ist
     *
     * @param username der Benutzername
     * @param password das Password
     * @return true falls alle Angaben korrekt, false sonst
     * @author Entscheider
     */
    public boolean authenticate(String username, String password) {

        SessionThing session = SessionThing.getInstance();
        User u = session.getDatabase().getUserByUsername(username);

        if (u == null) return false;

        try {
            if (!u.checkPassword(password)) return false;
        } catch (NoSuchAlgorithmException e) {
            session.handleException(e);
            return false;
        }

        session.setSessionUser(u);

        return true;
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("login") || e.getActionCommand().equals("OK")) {
        	timer_slider.cancel();
        	if (!authenticate(startLoginScreen.getUsername(), startLoginScreen.getPassword())) {
                JOptionPane.showMessageDialog(startLoginScreen, "Login failed. Check Username and Password.");
                return;
            }
            
            startLoginScreen.dispose();
            MainFrameVController mainController = new MainFrameVController();
        } else if (e.getActionCommand().equals("registrate")) {
            RegistrationVController regvcon = new RegistrationVController();
            regvcon.getView().setBounds(600, 150, 400, 400);
            startLoginScreen.getPanContent().add(regvcon.getView(), JLayeredPane.PALETTE_LAYER + 1, 0);
        }
    }


    public void windowClosed(WindowEvent e) {
        // TODO Auto-generated method stub

    }

    public void windowClosing(WindowEvent e) {
        // TODO Auto-generated method stub

    }

    public void windowOpened(WindowEvent e) {
    	timer_slider = new java.util.Timer();
        java.util.TimerTask action = new java.util.TimerTask() {
            @Override
            public void run() {
                if (con_slider.slideAble()) {
                    con_slider.slide();
                }
            }
        };
        
        timer_slider.schedule(action, 1000, 4500);
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
