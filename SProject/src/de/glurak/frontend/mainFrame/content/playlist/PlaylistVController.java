package de.glurak.frontend.mainFrame.content.playlist;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JComponent;

import de.glurak.frontend.mainFrame.ContentController;

public class PlaylistVController implements ActionListener, ContentController{

	private PlaylistView view;
	
	/**
	 * Konstruktor
	 */
	public PlaylistVController(){
		view = new PlaylistView();
		
	}

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	public JComponent getView() {
		return view;
	}
	
}
