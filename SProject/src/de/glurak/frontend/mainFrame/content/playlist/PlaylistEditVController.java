package de.glurak.frontend.mainFrame.content.playlist;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Observable;

import javax.swing.JComponent;

import de.glurak.data.Playlist;
import de.glurak.frontend.mainFrame.ContentController;
import de.glurak.frontend.mainFrame.NextContent;

public class PlaylistEditVController extends Observable implements ActionListener, ContentController, NextContent{

	private PlaylistEditView playeditview;
	private ContentController nextContent;
	/**
	 * Konstruktor
	 */
	public PlaylistEditVController(Playlist p, ContentController c){
		playeditview = new PlaylistEditView(this);
		playeditview.setPlaylist(p);
		nextContent = c;
	}
	
	public JComponent getView(){
		return playeditview;
	}
	/*
	public JComponent getView(Playlist p){
		playeditview.setPlaylist(p);
		return playeditview;
	} */
	
	public ContentController getNextContent() {
		return nextContent;
	}
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("cancel")){
			//nextContent = c;
			setChanged();
			notifyObservers();
		}
	}



}
