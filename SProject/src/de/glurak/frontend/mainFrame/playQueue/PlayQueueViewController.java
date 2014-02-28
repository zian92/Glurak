package de.glurak.frontend.mainFrame.playQueue;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import de.glurak.data.Medium;
import de.glurak.data.Playlist;
import de.glurak.feature.sound.PlayerController;

public class PlayQueueViewController {
	
	private PlayerController player;
	private Playlist playlist;
	private Medium currentMedium;
	private PlayQueueView view;
	
	private final static int NOTSTARTED = 0;
    private final static int PLAYING = 1;
    private final static int PAUSED = 2;
    private final static int FINISHED = 3;
    private final static int FINISHED_BY_END = 4;
	
	
	public PlayQueueViewController (Playlist playlist) {
		view = new PlayQueueView(playlist, playlist.getCurrent());
		player = new PlayerController();
		this.setCurrentMedium(playlist.getCurrent());
		this.setPlaylist(playlist);
		
		ActionListener a = new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				Object src = e.getSource();
				if (src == view.getPlayButton()) {
					if (player.isPaused()) {
						player.resume();
					}  else if (player.isPlaying()) {
								player.pause();
					} else {
						
						player.play(getCurrentMedium().getFileName());
						addPlayerListener();
					}
				} else if (src == view.getNextButton()) {
					setCurrentMedium(getPlaylist().getNext());
					if (player.isPaused() || player.isPlaying())
						player.stop();
					player.play(getCurrentMedium().getFileName());
					view.getQueuePanel().resetButton();
					
				} else if (src == view.getPreviousButton()) {
					setCurrentMedium(getPlaylist().getPrevious());
					if (player.isPaused() || player.isPlaying())
						player.stop();
					player.play(getCurrentMedium().getFileName());
					addPlayerListener();
					view.getQueuePanel().resetButton();
				}
				else { 
					for(int i = 0;i<getPlaylist().getMediumList().size();i++){
						if(src== view.getQueuePanel().getMediumButtonArray()[i]){
							
							getPlaylist().setCurrent(i);
							setCurrentMedium(getPlaylist().getCurrent());
								
								if (player.isPaused()||player.isPlaying()){
									player.stop();}

								player.play(getCurrentMedium().getFileName());
								addPlayerListener();
								view.getQueuePanel().resetButton();
								
								
						}
					
					}
				}
			}
				
			
		};
		
		view.getPlayButton().addActionListener(a);
		view.getNextButton().addActionListener(a);
		view.getPreviousButton().addActionListener(a);
		
		for(int i = 0;i<getPlaylist().getMediumList().size();i++){
			view.getPlaylistButton()[i].addActionListener(a);
		}
	}
	
	public PlayQueueViewController () {
		this(null);
	}
	
	private void addPlayerListener() {
		player.getPlayer().addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				
				if(player.getPlayer().getPlayerStatus()==FINISHED_BY_END){
					
					setCurrentMedium(getPlaylist().getNext());
						player.stop();
					player.play(getCurrentMedium().getFileName());
					view.getQueuePanel().resetButton();
				}
			}
            });
	}
	
	public PlayerController getPlayer() {
		return player;
	}


	public void setPlayer(PlayerController player) {
		this.player = player;
	}


	public Playlist getPlaylist() {
		return playlist;
	}


	public void setPlaylist(Playlist playlist) {
		this.playlist = playlist;
	}


	public PlayQueueView getView() {
		return view;
	}


	public void setView(PlayQueueView view) {
		this.view = view;
	}

	public Medium getCurrentMedium() {
		return currentMedium;
	}

	public void setCurrentMedium(Medium currentMedium) {
		this.currentMedium = currentMedium;
		System.out.println(currentMedium);
	}



}
