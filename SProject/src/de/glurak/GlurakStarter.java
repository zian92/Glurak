package de.glurak;

import java.awt.Component;
import java.io.File;
import java.io.IOException;

import javax.swing.SwingUtilities;

import de.glurak.feature.Uploader;
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
				//LoginView frame_Login = new LoginView(Query.APPLICATION_NAME);
				//JFrame frame = new JFrame();
				//frame = 
				//logControll.getView().setSize(1024, 700);

				//GlurakStarter glumanda = new GlurakStarter();
				//glumanda.test(frame_Login.getContentPane());
			}
		});

	}

	public void test(Component comp) {
		Uploader u = Uploader.getInstance();
		try {
			File f = u.selectSinglePicture(comp);
			u.saveProfilePicture(f);
			u.saveAlbumCover(f);
			u.saveSlider(f);
			File[] f2 = u.selectMusic(comp);
			u.saveMusic(f2);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
