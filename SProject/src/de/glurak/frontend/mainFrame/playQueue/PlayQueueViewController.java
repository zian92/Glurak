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
import java.math.BigDecimal;
import java.util.Observable;

import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import de.glurak.data.Medium;
import de.glurak.data.Playlist;
import de.glurak.data.Playqueue;
import de.glurak.feature.sound.PlayerController;
import de.glurak.frontend.mainFrame.ContentController;
import de.glurak.frontend.mainFrame.MainFrameVController;
import de.glurak.frontend.mainFrame.content.playlist.PlaylistEditVController;
import de.vdheide.mp3.MP3Properties;
import de.vdheide.mp3.NoMP3FrameException;

/**
 * Controller für die PlayQueueView
 * Verarbeitet alle Änderungen/Eingaben die den Mediaplayerbetreffen
 * 
 * @author MMÜhlenjost,Zengo
 *
 */
public class PlayQueueViewController extends Observable{
	
	private PlayerController 	player;
	private Playqueue 			playqueue;
	private PlayQueueView 		view;
	private float				duration;
	private float 				bitrate=0;
    private final static int 	FINISHED_BY_END = 4;
    private ActionListener		a;
    private MouseListener		m;
    private boolean				propertiesBoolean;
	private ContentController 	contentController;
	private MainFrameVController mainController;
    private static PlayQueueViewController instance= null;
	
	
    /** Singleton
     * @return Instanz des Player
     */
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
		if(getPlayqueue()!=null&&player!=null){		
			if(!view.getPositionBar().getValueIsAdjusting()){
				Object src = e.getSource();
				
				if (src == view.getPlayButton()) {
				
					if (player.isPaused()){
						player.resume();
						view.playButton.setText("Pause");}
					else if (player.isPlaying()) {
								player.pause();
								view.playButton.setText("Play");}
					else {
						playNew(0);
					}
					
				} else if (src == view.getNextButton()) {
					getPlayqueue().getNext();
					if(player.isPlaying()||player.isPaused()){
						player.stop();}
					playNew(0);
					view.getQueuePanel().resetButton();
					
				} else if (src == view.getPreviousButton()) {
					getPlayqueue().getPrevious();
					if(player.isPlaying()||player.isPaused()){
						player.stop();}
					playNew(0);
					view.getQueuePanel().resetButton();
		
				}
				else if (src == view.getClearButton()) {
					setPlayqueue(null);
					view.getPositionBar().setValue(0);
					if(player.isPlaying()||player.isPaused()){
						player.stop();}
					refresh();
		
				}
				else if (src== view.getSaveButton()) {
					setContentController(new PlaylistEditVController(getPlayqueue().getPlaylist(), mainController.getContentController()));
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
				
				if(propertiesBoolean&&!view.isPositionChange()&&!view.getPositionBar().getValueIsAdjusting()){
					if(player.isPlaying()||player.isPaused()){
						{player.stop();}
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
		view.getSaveButton().addActionListener(a);
		
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
					if(propertiesBoolean){
					view.changePositionBar((Integer) evt.getNewValue());}
					
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
	
	/** Reagiert auf neue Playqueue
	 *  und aktualisiert View+ Listener
	 * @param PlayQueue
	 */
	public void refresh(Playqueue playqueue){
			setPlayqueue(playqueue);
			refresh();
	}
	
	/**Fügt einzelnes Medium der aktuellenPlayqueue hinzu
	 * Bei leerer Playqueue wird neue erzeugt
	 * Sonst Überprüft , ob Playqueue Medium bereits enthält
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
	 * Reagiert intern auf Änderungen der Playlist
	 * View wird aktualisiert
	 * MousListener neu angehängt
	 */
	private void refresh() {
		view.initQueueView(getPlayqueue());
		if(getPlayqueue()!=null){
			
			for(int i = 0;i<getPlayqueue().getPlaylist().getMediumList().size();i++){
				view.getQueuePanel().getMediumPanelArray()[i].addMouseListener(m);
			}
		}	
	}


	/**
	 * Startet Wiedergabe und führt nötige Erneuerungen durch
	 * @param time Frame bei dem er starten soll
	 */
	public void playNew(int time){
		stop();
		if(getPlayqueue()!=null){
			
			if(getPlayqueue().getCurrent().isLocked()) {// gesperrte Medium aus der Playlist löschen
				if(getPlayqueue().size()==1){			// Liste wird leer
					setPlayqueue(null);
					view.getPositionBar().setValue(0);
					player.pause();
					player.stop();
				}
				else{	//aktuelles Medium wird aus der Playqueue gelöscht
					if (player.isPaused()||player.isPlaying()){
						player.stop();}	
					getPlayqueue().removeMedium(getPlayqueue().getCurrent());
					playNew(0);
				}
				refresh();
			}
				
		
			else{	// Medium wird abgespielt
				view.playButton.setText("Pause");
				player.play(getPlayqueue().getCurrent().getFileName(),time);
				// Auslesen der Headerdaten
				File file = new File(getPlayqueue().getCurrent().getFileName());
				MP3Properties properties = null;
				propertiesBoolean=true;
				try {
					properties 	= new MP3Properties(file);
					duration	= properties.getLength();
					BigDecimal bigOne = new BigDecimal(128);
					BigDecimal bigBitrate = new BigDecimal(properties.getBitrate());
					bitrate		=(bigOne.divide(bigBitrate)).floatValue();
			
				} catch (IOException e1) {
					e1.printStackTrace();
				} catch (NoMP3FrameException e1) {
					e1.printStackTrace();
				}
				 catch(ArithmeticException e2){
					propertiesBoolean=false;
					System.out.println("Eigenschaften nicht lesbar");
				 }
				if(bitrate==0){propertiesBoolean=false;}
				if(propertiesBoolean) {
					
					float maximum;
					if (bitrate >= 1) {
						maximum=(duration*1000/26)/(bitrate);
					} else {
						maximum=(duration*1000/26);
						
					}
					//Anpassen des Sliders an das aktuelle Medium
					view.setPositionBar(new JSlider(0,(int)(maximum),time));
				}
				else{
					view.setPositionBar(new JSlider(0,0,0));
					
				}
				view.getPositionBar().validate();
				addPlayerListener();
					
		
			}
		}
	}
	
	/**
	 * Stoppt den PausablePlayer, falls er existiert
	 */
	public void stop(){
		if(player==null){
			
		}else{
				if(player.isPaused()||player.isPlaying()){
					player.stop();}
		}
			
	}
	/** Content des Mainframes wird aktualisiert
	 * @param contentController
	 */
	public void setContentController (ContentController contentController){
		
		this.contentController = contentController;
		setChanged();
		notifyObservers(contentController);
	}
	

	public ContentController getContentController(){
		
		return contentController;
	}
	
	public void setMainController(MainFrameVController mainController){
		this.mainController =mainController;
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
	

}
