package de.glurak.frontend.mainFrame.content.follower;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import java.util.Observable;

import javax.swing.JButton;
import javax.swing.JComponent;

import de.glurak.data.Playlist;
import de.glurak.data.User.User;
import de.glurak.frontend.SessionThing;
import de.glurak.frontend.mainFrame.ContentController;
import de.glurak.frontend.mainFrame.NextContent;
import de.glurak.frontend.mainFrame.content.follower.FollowerView.FavoriteLabel;
import de.glurak.frontend.mainFrame.content.playlist.PlaylistEditVController;
import de.glurak.frontend.mainFrame.content.playlist.PlaylistView;
import de.glurak.frontend.mainFrame.content.profile.ProfileVController;

/**
 * Diese Klasse stellt der FollowerView die Funktionalität zur Verfügung.
 */
public class FollowerVController  extends Observable implements MouseListener, ActionListener, ContentController, NextContent {

	private FollowerView view;
	private ContentController nextContent;
	 
	/**
	 * Konstruktor initialisiert view  
	 */
	public FollowerVController(){
		this.view = new FollowerView(this, this);
		view.setVisible(true);
	    initfillView();
	}
	
	/**
	 * besorgt daten für die view
	 */
	public void initfillView(){
		List<User> list = SessionThing.getInstance().getSessionUser().getFollowing();
		view.fillView(list);
	}
	
	public ContentController getNextContent() {
		// TODO Auto-generated method stub
		return null;
	}

	public JComponent getView() {
		return view;
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("likeFavo")){
			JButton bt = (JButton) e.getSource();
			( (FavoriteLabel) bt.getParent()).getFavorite().like(SessionThing.getInstance().getSessionUser());
			
			// Die wohl mit Abstand schla..., eh, "unsauberste" Stelle im gesamten Programm
		}else if(e.getActionCommand().equals("hateFavo")){
			JButton bt = (JButton) e.getSource();
			( (FavoriteLabel) bt.getParent()).getFavorite().hate(SessionThing.getInstance().getSessionUser());
			// Die wohl mit Abstand schla..., eh, "unsauberste" Stelle im gesamten Programm
		}else{
			
		}
		
	}

	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		FollowerView.FavoriteLabel  lab = (FollowerView.FavoriteLabel) e.getSource();
	
			nextContent = new ProfileVController(lab.getFavorite());
			setChanged();
			notifyObservers(nextContent);
		
	}

	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void reload() {
		view.cleanView();
		List<User> list = SessionThing.getInstance().getSessionUser().getFollowing();
		view.fillView(list);
	}

}
