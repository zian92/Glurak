package de.glurak.feature.sound;
import java.io.InputStream;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.AudioDevice;
import javazoom.jl.player.advanced.AdvancedPlayer;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;


public class PausablePlayer {

    private final static int NOTSTARTED = 0;
    private final static int PLAYING = 1;
    private final static int PAUSED = 2;
    private final static int FINISHED = 3;
    private final static int FINISHED_BY_END = 4;
    private int actualFrame;
    

    // the player actually doing all the work
    private final AdvancedPlayer player;
        
    private PropertyChangeSupport changes = new PropertyChangeSupport( this );
    
    // locking object used to communicate with player thread
    private final Object playerLock = new Object();

    // status variable what player thread is doing/supposed to do
    private int playerStatus = NOTSTARTED;

    public PausablePlayer(final InputStream inputStream) throws JavaLayerException {
        this.player = new AdvancedPlayer(inputStream);
      
    }

    public PausablePlayer(final InputStream inputStream, final AudioDevice audioDevice) throws JavaLayerException {
        this.player = new AdvancedPlayer(inputStream, audioDevice);
       
    }
   
   
    /**
     * Starts playback (resumes if paused)
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
     * Pauses playback. Returns true if new state is PAUSED.
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
     * Resumes playback. Returns true if the new state is PLAYING.
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
     * Stops playback. If not playing, does nothing
     */
    public void stop() {
        synchronized (playerLock) {
        	setPlayerStatus(FINISHED);
            playerLock.notifyAll();
        }
    }
    
    public void setPlayerStatus(int playerStatus){
    	 int oldPlayerStatus = this.playerStatus;
    	 this.playerStatus = playerStatus;
    	 changes.firePropertyChange( "playerStatus", oldPlayerStatus, playerStatus );
  	
    }
    public void setActualFrame(int frame){
    	int oldFrame= this.actualFrame;
    	this.actualFrame = frame;
    	changes.firePropertyChange("actualFrame", oldFrame, actualFrame);
    	
    }
    /**Setzt den Player zum Stratframe
     * @param time framezahl
     */
    public void setTime(int time){
    	try {
			player.play(time,time);
			setActualFrame(time);
		} catch (JavaLayerException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	
    }
    
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
        setPlayerStatus(FINISHED_BY_END);
        close();
    }

    /**
     * Closes the player, regardless of current state.
     */
    public void close() {
        synchronized (playerLock) {
        	setPlayerStatus(FINISHED);
        }
        try {
            player.close();
        } catch (final Exception e) {
            // ignore, we are terminating anyway
        }
    }
    
    public int getPlayerStatus(){
		return this.playerStatus;
       	 
     }
    public int getActualFrame(){
    	return this.actualFrame;
    }
    public void addPropertyChangeListener( PropertyChangeListener l )
    {
      changes.addPropertyChangeListener( l );
    }

    public void removePropertyChangeListener( PropertyChangeListener l )
    {
      changes.removePropertyChangeListener( l );
    }
    
    
}