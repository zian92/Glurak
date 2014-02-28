package de.glurak.frontend.mainFrame.playQueue;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import de.glurak.data.Medium;
import de.glurak.data.Playlist;
import de.glurak.feature.sound.PlayerController;

public class PlayQueueViewController {
	
	private PlayerController player;
	private Playlist playlist;
	private Medium currentMedium;
	

	private PlayQueueView view;
	
	
	public PlayQueueViewController (Playlist playlist) {
		view = new PlayQueueView(playlist, playlist.getCurrent());
		player = new PlayerController();
		this.setCurrentMedium(playlist.getCurrent());
		this.setPlaylist(playlist);
		System.out.println("WOHIN?");
		
		ActionListener a = new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				System.out.println("Swag?");
				
				Object src = e.getSource();
				
				if (src == view.getPlayButton()) {
					if (player.isPaused()) {
						System.out.println("resume");
						player.resume();
					}  else if (player.isPlaying()) {
						System.out.println("pause");
						player.pause();
					} else {
						System.out.println("Play");
						player.play(getCurrentMedium().getFileName());					
					}
				} else if (src == view.getNextButton()) {
					System.out.println("next");
					
					
					
					setCurrentMedium(getPlaylist().getNext());
					if (player.isPaused() || player.isPlaying())
						player.stop();
					player.play(getCurrentMedium().getFileName());
					
					view.initQueueView(getPlaylist(), getCurrentMedium());
					
				} else if (src == view.getPreviousButton()) {
					System.out.println("prev");
					setCurrentMedium(getPlaylist().getPrevious());
					if (player.isPaused() || player.isPlaying())
						player.stop();
					player.play(getCurrentMedium().getFileName());
				}
				//else if
			}
		};
		
		view.getPlayButton().addActionListener(a);
		view.getNextButton().addActionListener(a);
		view.getPreviousButton().addActionListener(a);
		
	}
	
	public PlayQueueViewController () {
		this(null);
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
	}



}
