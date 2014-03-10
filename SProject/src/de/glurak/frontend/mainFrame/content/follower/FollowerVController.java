package de.glurak.frontend.mainFrame.content.follower;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import java.util.Observable;

import javax.swing.JComponent;

import de.glurak.data.Playlist;
import de.glurak.data.User.User;
import de.glurak.frontend.SessionThing;
import de.glurak.frontend.mainFrame.ContentController;
import de.glurak.frontend.mainFrame.NextContent;

public class FollowerVController  extends Observable implements MouseListener, ActionListener, ContentController, NextContent {

	private FollowerView view;
	private ContentController nextContent;
	 
	public FollowerVController(){
		this.view = new FollowerView(this, this);
		view.setVisible(true);
	    initfillView();
	}
	
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
		// TODO Auto-generated method stub
		
	}

	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
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
