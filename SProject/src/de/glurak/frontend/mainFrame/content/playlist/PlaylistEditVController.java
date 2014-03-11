package de.glurak.frontend.mainFrame.content.playlist;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusListener;
import java.awt.event.FocusEvent;
import java.util.Observable;

import javax.swing.JComponent;
import javax.swing.JOptionPane;

import de.glurak.data.Playlist;
import de.glurak.data.Playqueue;
import de.glurak.frontend.SessionThing;
import de.glurak.frontend.mainFrame.ContentController;
import de.glurak.frontend.mainFrame.NextContent;
import de.glurak.frontend.mainFrame.playQueue.PlayQueueView;
import de.glurak.frontend.mainFrame.playQueue.PlayQueueViewController;

public class PlaylistEditVController extends Observable implements ActionListener, ContentController, NextContent, FocusListener{

	private PlaylistEditView playeditview;
	private ContentController nextContent;
	private Playlist myPlaylist;
	/**
	 * Konstruktor
	 */
	public PlaylistEditVController(Playlist p, ContentController c){
		
		playeditview = new PlaylistEditView(this);
		playeditview.setPlaylist(p);
		playeditview.field_name.addFocusListener(this);
		//nextContent = c;
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
			nextContent = new PlaylistVController();
			setChanged();
			notifyObservers();
			
		}else if (e.getActionCommand().equals("save")){
			if (!playeditview.NameAppropriate()){
				JOptionPane.showMessageDialog(playeditview,"Ihr Playlistname ist unangemessen.","Fehlerhafte Eingabe",JOptionPane.ERROR_MESSAGE);
				playeditview.refreshName();
			}else{
	
					Playlist npl = new Playlist(playeditview.getPlaylistName() , playeditview.getPlaylist() );
					npl.setOwner(SessionThing.getInstance().getSessionUser());
					SessionThing.getInstance().getDatabase().addPlaylist(npl, null);
					//SessionThing.getInstance().getDatabase().save();
					/*
					if (nextContent instanceof PlaylistVController){
						((PlaylistVController) nextContent).refreshView();
					}*/
					nextContent = new PlaylistVController();
					setChanged();
					notifyObservers();
				
				
			}
		}else if(e.getActionCommand().equals("delete")){
		
			SessionThing.getInstance().getSessionUser().removePlaylist(playeditview.getPlaylist());
			SessionThing.getInstance().getDatabase().save();
		
			nextContent = new PlaylistVController();
			setChanged();
			notifyObservers();
		}else if(e.getActionCommand().equals("play")){
			if (playeditview.getPlaylist() != null)
			 PlayQueueViewController.getInstance().refresh(new Playqueue(playeditview.getPlaylist()));
		}
	}

	public void focusGained(FocusEvent e) {
		playeditview.field_name.selectAll();
	}

	public void focusLost(FocusEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void reload() {
		
		
	}



}
