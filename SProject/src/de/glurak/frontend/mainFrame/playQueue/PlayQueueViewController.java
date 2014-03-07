package de.glurak.frontend.mainFrame.playQueue;

import java.awt.Component;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import de.glurak.Query;
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
	
	private PlayerController 	player;
	private Playlist 			playlist;
	private PlayQueueView 		view;
	private long 				duration;
    private final static int 	FINISHED_BY_END = 4;
    private ActionListener		a;
    private MouseListener		m;
	
	
    
    
    
    
	/**Konstrukter mit Initialisierung der Listener
	 * @param playlist abzuspielende Playlist
	 */
	public PlayQueueViewController (Playlist playlist) {
		view = new PlayQueueView(playlist);
		player = new PlayerController();
		this.setPlaylist(playlist);
		
		a = new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
		if(getPlaylist()!=null){		
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
					getPlaylist().getNext();
					if (player.isPaused() || player.isPlaying())
						player.stop();
					playNew(0);
					view.getQueuePanel().resetButton();
					
				} else if (src == view.getPreviousButton()) {
					getPlaylist().getPrevious();
					if (player.isPaused() || player.isPlaying())
						player.stop();
					playNew(0);
					view.getQueuePanel().resetButton();
		
				}
			
			}
			}
			}
		};
		
		ChangeListener c = /**
		 * @author MMÜhlenjost
		 *Reagiert auf Veränderungen der Scrollbar
		 *
		 */
		new ChangeListener(){

			public void stateChanged(ChangeEvent e) {
				if(view.getPositionBar().getValueIsAdjusting()){
					if(player.isPlaying()){
						player.stop();
					}
				}
				
				if(!view.isPositionChange()&&!view.getPositionBar().getValueIsAdjusting()){
					if(player.isPlaying()||player.isPaused()){
						player.stop();
						playNew(view.getPositionBar().getValue());
				}
				
			}
			}
		};
		
		m= new MouseAdapter(){
			public void mouseReleased(MouseEvent e){
    				for(int i = 0;i<getPlaylist().getMediumList().size();i++){
    					if(e.getSource()==view.getQueuePanel().getMediumPanelArray()[i]){
    						if (e.getClickCount() >= 2) { 	
    							getPlaylist().setCurrent(i);
    							view.getQueuePanel().resetButton();
    							if (player.isPaused()||player.isPlaying()){
    								player.stop();}
							
    							playNew(0);
    							view.getQueuePanel().resetButton();
    						}/*else{
    							
    							
    							view.getQueuePanel().showInformations(i);
    							
    						}*/
    			
    					}
    				}
    		
    			}
			
		};
		
		view.getQueuePanel().addMouseListener(m);
		view.getPositionBar().addChangeListener(c);
		view.getPlayButton().addActionListener(a);
		view.getNextButton().addActionListener(a);
		view.getPreviousButton().addActionListener(a);
		if(getPlaylist()!=null){	
			for(int i = 0;i<getPlaylist().getMediumList().size();i++){
				//view.getPlaylistButton()[i].addActionListener(a);
				view.getQueuePanel().getMediumPanelArray()[i].addMouseListener(m);
			}
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
					
					getPlaylist().getNext();
						player.stop();
						playNew(0);
					view.getQueuePanel().resetButton();
				}
			}
			}		
            });
	}
	
	/**reagiert auf Veränderungen der Playlist
	 * und aktualisiert View+ Listener
	 * @param playlist
	 */
	public void refresh(Playlist playlist){
		setPlaylist(playlist);
		view.initQueueView(playlist);
		for(int i = 0;i<getPlaylist().getMediumList().size();i++){
			view.getQueuePanel().getMediumPanelArray()[i].addMouseListener(m);
		}
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


	/**
	 * Startet Wiedergabe und führt nötige Erneuerungen durch
	 * @param time Frame bei dem er starten soll
	 */
	public void playNew(int time){
		
		player.play(getPlaylist().getCurrent().getFileName(),time);
		File file = new File(getPlaylist().getCurrent().getFileName());
		MP3Properties properties = null;
		try {
			properties = new MP3Properties(file);
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (NoMP3FrameException e1) {
			e1.printStackTrace();
		}
		duration= properties.getLength();		
		view.setPositionBar(new JSlider(0,(int)((duration*1000/26)/(128/properties.getBitrate())),time));
		view.getPositionBar().validate();
		addPlayerListener();
		
		
	}

}
