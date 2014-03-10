package de.glurak.frontend.mainFrame.content.profile;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Observable;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import de.glurak.data.Playlist;
import de.glurak.data.User.User;
import de.glurak.feature.Uploader;
import de.glurak.frontend.SessionThing;
import de.glurak.frontend.mainFrame.ContentController;
import de.glurak.frontend.mainFrame.NextContent;

/**
 * Diese Klasse stellt dem ProfileEditView die Funktionalität zur Verfügung.
 * @author Christopher Distelkämper		
 * Date: 28.02.2014
 */
public class ProfileEditVController extends Observable implements ActionListener, ContentController, NextContent {
	
	private ProfileView profileEditView;
	private ContentController nextContent;
	private User user;
	private boolean picChanged;
	
	public boolean isPicChanged() {
		return picChanged;
	}

	public ProfileEditVController(){
		
		this.user = SessionThing.getInstance().getSessionUser();
		List<Playlist> top5Playlists = new ProfileVController(this.user).getTopFiveHatedPlaylists();
		
		profileEditView = new ProfileView(user, top5Playlists, true);
		
		// Setzen der ActionListener
		profileEditView.b_edit.addActionListener(this);
		profileEditView.b_upload.addActionListener(this);
		
	}

	@SuppressWarnings("deprecation")
	public void actionPerformed(ActionEvent e){
		Object obj = e.getSource();
		String password = profileEditView.t_password.getText();
		String passwordConfirm = profileEditView.t_passwordConfirm.getText();
		
		if (obj == profileEditView.b_edit) {
			try {
				if (!password.isEmpty() && password.equals(passwordConfirm)) {
						user.setPassword(password);
				} else if (password.isEmpty() && passwordConfirm.isEmpty()){
					
				} else {
					JOptionPane.showMessageDialog(profileEditView,"Passwörter nicht korrekt");
                    return;
				}
			} catch (NoSuchAlgorithmException e1) {
				e1.printStackTrace();
			}
			
			user.getProfile().setFirstname(profileEditView.t_firstname.getText());
			user.getProfile().setLastname(profileEditView.t_lastname.getText());
			user.getProfile().setEmail(profileEditView.t_email.getText());
			user.getProfile().setCountry(profileEditView.t_homecountry.getText());
			nextContent = new ProfileVController(null);
			setChanged();
			notifyObservers();
		} else if (obj == profileEditView.b_upload){
			Uploader u = Uploader.getInstance();
			File file = u.selectSinglePicture(this.profileEditView);
			try {
                u.saveProfilePicture(file);
            } catch (IOException e1) {
                JOptionPane.showMessageDialog(this.profileEditView, "Bitte versuch es mit einer anderen Datei.", "Fehler", JOptionPane.ERROR_MESSAGE);
            }
			this.picChanged = true;
			this.profileEditView.repaint();
			this.profileEditView.revalidate();
			setChanged();
			notifyObservers();
		}
	}

	public JComponent getView() {
		return profileEditView;
	}

	public ContentController getNextContent() {
		return nextContent;
	}

}
