package de.glurak.frontend.mainFrame.playQueue;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import de.glurak.data.Medium;
import de.glurak.data.Playlist;
import de.glurak.data.Playqueue;
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
	private Playqueue 			playqueue;
	private PlayQueueView 		view;
	private long 				duration;
    private final static int 	FINISHED_BY_END = 4;
    private ActionListener		a;
    private MouseListener		m;
    private static PlayQueueViewController instance= null;
	
	
    public static PlayQueueViewController getInstance(){
		if(instance==null){
			instance = new PlayQueueViewController();
		}   	
    	return instance;  	
    }
    
    
    
	/**Konstrukter mit Initialisierung der Listener
	 * @param playlist abzuspielende Playlist
	 */
	private PlayQueueViewController () {
		view = new PlayQueueView(null);
		player = new PlayerController();
		
		a = new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
		if(getPlayqueue()!=null){		
			if(!view.getPositionBar().getValueIsAdjusting()){
				Object src = e.getSource();
				if (src == view.getPlayButton()) {
					if (player.isPaused()) {

						playNew(view.getPositionBar().getValue());
					} else if (player.isPlaying()) {
								player.pause();
								view.playButton.setText("Play    ");
					} else {
						playNew(view.getPositionBar().getValue());
					}
				} else if (src == view.getNextButton()) {
					getPlayqueue().getNext();
					if (player.isPaused() || player.isPlaying())
						player.stop();
					playNew(0);
					view.getQueuePanel().resetButton();
					
				} else if (src == view.getPreviousButton()) {
					getPlayqueue().getPrevious();
					if (player.isPaused() || player.isPlaying())
						player.stop();
					playNew(0);
					view.getQueuePanel().resetButton();
		
				}
				else if (src == view.getClearButton()) {
					setPlayqueue(null);
					view.getPositionBar().setValue(0);
						player.stop();
					refresh();
		
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
				if(getPlayqueue()!=null){
					//LinksDoppelKlick
					if (e.getButton() == 1 &&e.getClickCount()==2) {
						for(int i = 0;i<getPlayqueue().size();i++){
							//Medium in der Playqueue angeklickt
							if(e.getSource()==view.getQueuePanel().getMediumPanelArray()[i]){ 
    								getPlayqueue().setCurrent(i);
    								view.getQueuePanel().resetButton();
    								if (player.isPaused() || player.isPlaying())
    									player.stop();
    								playNew(0);
    								view.getQueuePanel().resetButton();
    							}
    						}
					}
    				else{
    					//Rechtsklick
    					if (e.getButton()==3){
    							//Playqueue wird leer
    							if(getPlayqueue().size()==1){
    								setPlayqueue(null);
    								view.getPositionBar().setValue(0);
    								if(player.isPaused()||player.isPlaying()){
    									player.stop();}
    								refresh();
        							
        						}//Playqueue nicht leer
    							else{    							
    								for(int i = 0;i<getPlayqueue().size();i++){
    									if(e.getSource()==view.getQueuePanel().getMediumPanelArray()[i]){ 
    										if(!getPlayqueue().isCurrent(i)){
    											getPlayqueue().removeMedium(getPlayqueue().getPlaylist().getMediumList().get(i));
    										}
    							else{ // aktuelles Medium wird entfernt
    								if (player.isPaused()||player.isPlaying()){
    									player.stop();}	
    								getPlayqueue().removeMedium(getPlayqueue().getPlaylist().getMediumList().get(i));
    								playNew(0);
    								}
    							
    							}
    								}	
    								refresh();
    							}
    						
    				
    						}
    				}
    		
    			}
			}
		};
		
		view.getPositionBar().addChangeListener(c);
		view.getPlayButton().addActionListener(a);
		view.getNextButton().addActionListener(a);
		view.getPreviousButton().addActionListener(a);
		view.getClearButton().addActionListener(a);
		if(getPlayqueue()!=null){	
			view.getQueuePanel().addMouseListener(m);
			for(int i = 0;i<getPlayqueue().getPlaylist().getMediumList().size();i++){
				view.getQueuePanel().getMediumPanelArray()[i].addMouseListener(m);
			}
		}
	}
	/**
	 * Fügt Listener für Veränderungen beim PausablePlayer hinzu
	 */
	private void addPlayerListener() {
		player.getPlayer().addPropertyChangeListener(
					
		new PropertyChangeListener() {
			
			public void propertyChange(PropertyChangeEvent evt) {
				if(!view.getPositionBar().getValueIsAdjusting()&&getPlayqueue()!=null){
				if(evt.getPropertyName()=="actualFrame"){
					view.changePositionBar((Integer) evt.getNewValue());
					
				}else
					if(player.getPlayer()!=null){//

						if(player.getPlayer().getPlayerStatus()==FINISHED_BY_END){
							getPlayqueue().getNext();
							if(player.isPlaying()||player.isPaused()){
							player.stop();}
							playNew(0);
							view.getQueuePanel().resetButton();
						}
					}
				}
		}		
     });
	}
	
	/**reagiert auf neue Playlist
	 * und aktualisiert View+ Listener
	 * @param PlayQueue
	 */
	public void refresh(Playqueue playqueue){
			setPlayqueue(playqueue);
			refresh();
	}
	
	/**fügt einzelnes Medium der aktuellenPlayqueue hinzu
	 * 
	 * @param medium
	 */
	public void addMedium(Medium medium){
		if(getPlayqueue()==null){
			Playlist pl= new Playlist();
			pl.addMedium(medium);
			setPlayqueue(new Playqueue(pl));
		}
		else{
			if(!getPlayqueue().getPlaylist().getMediumList().contains(medium)){
			getPlayqueue().add(medium);	}
		}
		refresh();
	}
	
	/**
	 * reagiert intern au Änderungen der Playlist
	 */
	private void refresh() {
		view.initQueueView(getPlayqueue());
		if(getPlayqueue()!=null){
			
			for(int i = 0;i<getPlayqueue().getPlaylist().getMediumList().size();i++){
				view.getQueuePanel().getMediumPanelArray()[i].addMouseListener(m);
			}
		}	
	}



	public PlayerController getPlayer() {
		return player;
	}


	public void setPlayer(PlayerController player) {
		this.player = player;
	}


	public Playqueue getPlayqueue() {
		return playqueue;
	}


	public void setPlayqueue(Playqueue playqueue) {
		this.playqueue = playqueue;
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
		if(getPlayqueue()!=null){
			if (player.isPaused() || player.isPlaying()){
				player.stop();}
			view.playButton.setText("Pause");
		player.play(getPlayqueue().getCurrent().getFileName(),time);
		File file = new File(getPlayqueue().getCurrent().getFileName());
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
	public void stop(){
		if(player==null){
			
		}else{
			if(player.isPaused()||player.isPlaying()){
				player.stop();}
		}
			
	}

}
