package de.glurak.feature.sound;
import java.io.FileInputStream;

/**
 * PlayerController für den PausablePlayer
 * @author Zengo
 *
 */
public class PlayerController {
	
	private boolean isPaused;
	private boolean isPlaying;
	
	private PausablePlayer player;
	
	public PlayerController () {
		isPaused = false;
		isPlaying = false;
		
	}
	/**
	 * Startet Dateiwiedergabe
	 * @param location Der Pfad zur Datei
	 */
	/**
	 * @param location
	 * @param time
	 */
	public void play(String location,int time) {
		if(player!=null){
			player.close();
			
		}
		player=null;
		try {
            FileInputStream input = new FileInputStream(location);
            player = new PausablePlayer(input);
            
            player.play(time);
        } catch (final Exception e) {
            throw new RuntimeException(e);
        }
		
		isPlaying = true;
		isPaused = false;
	}

	
	/**
	 * Pausiert aktuelle Wiedergabe
	 */
	public void pause() {
		player.pause();

		isPlaying = false;
		isPaused = true;
	}
	
	/**
	 * Setzt aktuelle Wiedergabe fort
	 */
	public void resume() {
		player.resume();

		isPlaying = true;
		isPaused = false;
	}
	
	/**
	 * Stoppt aktuelle Wiedergabe
	 */
	public void stop() {
		player.stop();
		isPlaying = false;
		isPaused = true;
	}

	public boolean isPaused() {
		return isPaused;
	}

	public void setPaused(boolean isPaused) {
		this.isPaused = isPaused;
	}

	public boolean isPlaying() {
		return isPlaying;
	}

	public void setPlaying(boolean isPlaying) {
		this.isPlaying = isPlaying;
	}
	public PausablePlayer getPlayer() {
		
		return player;
	}
	

}

