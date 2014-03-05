package de.glurak.frontend.mainFrame.playQueue;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import de.glurak.data.Medium;
import de.glurak.data.Playlist;
import de.glurak.feature.sound.PlayerController;
import de.vdheide.mp3.MP3Properties;
import de.vdheide.mp3.NoMP3FrameException;

/**
 * Controller für die PlayQueueView
 * Verarbeitet alle Änderungen/Eingaben die den Mediaplayerbetreffen
 * 
 * @author MMÜhlenjost,Zengo
 *
 */
public class PlayQueueViewController {
	
	private PlayerController player;
	private Playlist playlist;
	private Medium currentMedium;
	private PlayQueueView view;
	private long duration;
    private final static int FINISHED_BY_END = 4;
	
	
	/**Konstrukter mit Initialisierung der Listener
	 * @param playlist abzuspielende Playlist
	 */
	public PlayQueueViewController (Playlist playlist) {
		view = new PlayQueueView(playlist, playlist.getCurrent());
		player = new PlayerController();
		this.setCurrentMedium(playlist.getCurrent());
		this.setPlaylist(playlist);
		
		ActionListener a = new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
			if(!view.getPositionBar().getValueIsAdjusting()){
				Object src = e.getSource();
				if (src == view.getPlayButton()) {
					if (player.isPaused()) {
						player.resume();
					}  else if (player.isPlaying()) {
								player.pause();
					} else {
						
						playNew(view.getPositionBar().getValue());
					}
				} else if (src == view.getNextButton()) {
					setCurrentMedium(getPlaylist().getNext());
					if (player.isPaused() || player.isPlaying())
						player.stop();
					playNew(0);
					view.getQueuePanel().resetButton();
					
				} else if (src == view.getPreviousButton()) {
					setCurrentMedium(getPlaylist().getPrevious());
					if (player.isPaused() || player.isPlaying())
						player.stop();
					playNew(0);
					view.getQueuePanel().resetButton();
				}
				else { 
					for(int i = 0;i<getPlaylist().getMediumList().size();i++){
						if(src== view.getQueuePanel().getMediumButtonArray()[i]){
							
							getPlaylist().setCurrent(i);
							setCurrentMedium(getPlaylist().getCurrent());
								
								if (player.isPaused()||player.isPlaying()){
									player.stop();}
								
								playNew(0);
								view.getQueuePanel().resetButton();
								
								
						}
					
					}
				}
			}
			}
		};
		
		ChangeListener c = new ChangeListener(){

			public void stateChanged(ChangeEvent e) {
				if(view.getPositionBar().getValueIsAdjusting()){
					if(player.isPlaying()){
						player.stop();
					}
				}
				
				if(!view.isPositionChange()&&!view.getPositionBar().getValueIsAdjusting()){
					System.out.println("leeetzteee");
					if(player.isPlaying()||player.isPaused()){
						player.stop();
						playNew(view.getPositionBar().getValue());
				}
				
			}
			}
		};
		
		view.getPositionBar().addChangeListener(c);
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
	
	/**
	 * Fügt Listener für Veränderungen beim PausablePlayer hinzu
	 */
	private void addPlayerListener() {
		player.getPlayer().addPropertyChangeListener(
					
		new PropertyChangeListener() {
			
			public void propertyChange(PropertyChangeEvent evt) {
				if(!view.getPositionBar().getValueIsAdjusting()){
				if(evt.getPropertyName()=="actualFrame"){
					view.changePositionBar((Integer) evt.getNewValue());
					
				}else
				if(player.getPlayer().getPlayerStatus()==FINISHED_BY_END){
					
					setCurrentMedium(getPlaylist().getNext());
						player.stop();
						playNew(0);
					view.getQueuePanel().resetButton();
				}
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
	
	}

	/**
	 * Startet Wiedergabe und führt nötige Erneuerungen durch
	 * @param time Frame bei dem er starten soll
	 */
	public void playNew(int time){
		
		player.play(getCurrentMedium().getFileName(),time);
		File file = new File(getCurrentMedium().getFileName());
		MP3Properties properties = null;
		try {
			properties = new MP3Properties(file);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (NoMP3FrameException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		duration= properties.getLength();
		//System.out.println(properties.getSamplerate());  
		view.setPositionBar(new JSlider(0,(int)(duration*1000/26),time));
		view.getPositionBar().validate();
		addPlayerListener();
		
		
	}

}
