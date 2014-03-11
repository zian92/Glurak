package de.glurak.feature.sound;
import java.io.InputStream;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;


/**Pausable Player 
 * @author MMÜhlenjost
 *
 */
public class PausablePlayer {

    private final static int NOTSTARTED = 0;
    private final static int PLAYING = 1;
    private final static int PAUSED = 2;
    private final static int FINISHED = 3;
    private final static int FINISHED_BY_END = 4;
    private int actualFrame;
    

    // Advanced Player aus der Javazoom Library
    private final AdvancedPlayer player;
        
    private PropertyChangeSupport changes = new PropertyChangeSupport( this );
    
    // Lock-Objekt zur Komumnikation mit dem Thrad 
    private final Object playerLock = new Object();

    // status Variable , zeigt was der PLayer gerade tut
    private int playerStatus = NOTSTARTED;

    /**
     * @param inputStream inputstream des abzuspielenden Mediums
     * @throws JavaLayerException
     */
    public PausablePlayer(final InputStream inputStream) throws JavaLayerException {
        this.player = new AdvancedPlayer(inputStream);
      
    }
   
    /**
     * Startet (resume , wenn Pause)
     */
    public void play(final int time) throws JavaLayerException {
        synchronized (playerLock) {
            switch (playerStatus) {
                case NOTSTARTED:
                    final Runnable r = new Runnable() {
                        public void run() {
                            playInternal(time);
                        }
                    };
                    final Thread t = new Thread(r);
                    t.setDaemon(true);
                    t.setPriority(Thread.MAX_PRIORITY);
                    setPlayerStatus(PLAYING);
                    t.start();
                    break;
                case PAUSED:
                    resume();
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * Pausiert.Gibt true zurück wenn playerstatus pausiert.
     */
    public boolean pause() {
        synchronized (playerLock) {
            if (playerStatus == PLAYING) {
                setPlayerStatus(PAUSED);
            }
            return playerStatus == PAUSED;
        }
    }

    /**
     * Resume. Gibt true zurück, wenn playerstatus = playing
     */
    public boolean resume() {
        synchronized (playerLock) {
            if (playerStatus == PAUSED) {
            	setPlayerStatus(PLAYING);
                playerLock.notifyAll();
            }
            return playerStatus == PLAYING;
        }
    }

    /**
     * Stoppt. Wenn nichts gespielt wird,passiert nichts
     */
    public void stop() {
        synchronized (playerLock) {
        	setPlayerStatus(FINISHED);
            playerLock.notifyAll();
        }
    }
    
    /**Player status wird verändert
     * und PropertyChange ausgelöst
     * @param playerStatus der neue Status des PLayers
     */
    public void setPlayerStatus(int playerStatus){
    	 int oldPlayerStatus = this.playerStatus;
    	 this.playerStatus = playerStatus;
    	 changes.firePropertyChange( "playerStatus", oldPlayerStatus, playerStatus );
  	
    }
    
    /**Frame wird verändert
     * @param frame der aktuell abgespielte Frame
     */
    public void setActualFrame(int frame){
    	int oldFrame= this.actualFrame;
    	this.actualFrame = frame;
    	changes.firePropertyChange("actualFrame", oldFrame, actualFrame);
    	
    }
    
    /**Setzt den Player zum Startframe
     * @param time framezahl, an der gestartet wird
     */
    public void setTime(int time){
    	try {
			player.play(time,time);
			setActualFrame(time);
		} catch (JavaLayerException e1) {
			
			e1.printStackTrace();
		}
    	
    }
    
    /**Spielt nächsten frame
     * oder pausiert, abhängig vom playerstatus
     * @param time
     */
    private void playInternal(int time) {
    	setTime(time);
    	 while (playerStatus != FINISHED) {
            try {
                if (!player.play(1)) {
                	setPlayerStatus(FINISHED_BY_END);
                    break;
                }
                setActualFrame(actualFrame+1);
            } catch (final JavaLayerException e) {
                break;
            }
            // check if paused or terminated
            synchronized (playerLock) {
                while (playerStatus == PAUSED) {
                    try {
                        playerLock.wait();
                    } catch (final InterruptedException e) {
                        // terminate player
                        break;
                    }
                }
            }
        }
        close();
    }

    /**
     * Player wird geschlossen
     */
    public void close() {
        synchronized (playerLock) {
        	setPlayerStatus(FINISHED);
        }
        try {
            player.close();
        } catch (final Exception e) {
        
        }
    }
    
    public int getPlayerStatus(){
		return this.playerStatus;
       	 
     }
    public int getActualFrame(){
    	return this.actualFrame;
    }
    
    /**
     * PropertyChangeListener hinzufügen
     * @param l
     */
    public void addPropertyChangeListener( PropertyChangeListener l )
    {
      changes.addPropertyChangeListener( l );
    }

    /**PropertyChangeListener löschen
     * @param l
     */
    public void removePropertyChangeListener( PropertyChangeListener l )
    {
      changes.removePropertyChangeListener( l );
    }
    
    
}