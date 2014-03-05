package de.glurak;

import java.awt.Component;

import javax.swing.SwingUtilities;

import de.glurak.frontend.login.LoginVController;

public class GlurakStarter {

    public GlurakStarter() {
        // TODO Auto-generated constructor stub
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                // Create GUI
                LoginVController logControll = new LoginVController(Query.APPLICATION_NAME);
                // LoginView frame_Login = new LoginView(Query.APPLICATION_NAME);
                // JFrame frame = new JFrame();
                // logControll.getView().setSize(1024, 700);
            }
        });

    }

    public void test(Component comp) {
    }
}
