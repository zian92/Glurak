package de.glurak.feature.sound;
import java.io.FileInputStream;

/**
 * PlayerController für den PausablePlayer
 * @author Zengo
 *
 */
public class PlayerController {
	
	private PausablePlayer player;
	
	/**
	 * Startet Dateiwiedergabe
	 * @param location Der Pfad zur Datei
	 */
	public void play(String location) {
		
		try {
            FileInputStream input = new FileInputStream(location); 
            player = new PausablePlayer(input);
            
            // start playing
            player.play();
        } catch (final Exception e) {
            throw new RuntimeException(e);
        }
		
	}
	
	/**
	 * Pausiert aktuelle Wiedergabe
	 */
	public void pause() {
		player.pause();
	}
	
	/**
	 * Setzt aktuelle Wiedergabe fort
	 */
	public void resume() {
		player.resume();
	}

}
