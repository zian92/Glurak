package de.glurak;

import javax.swing.SwingUtilities;

import de.glurak.frontend.login.LoginVController;

public class GlurakStarter {

    public GlurakStarter() {
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                // Create GUI
                LoginVController logControll = new LoginVController(Query.APPLICATION_NAME);
            }
        });
    }
}
