package de.glurak.feature.sound;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;

import org.tritonus.share.sampled.file.TAudioFileFormat;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;

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
	public void play(String location) {
		
		try {
            FileInputStream input = new FileInputStream(location); 
            File file = new File(location);
            System.out.println(file.getAbsolutePath());
            
//            AudioFileFormat fileFormat = AudioSystem.getAudioFileFormat(file);
//            System.out.println(getDuration(file));
            
//            System.out.println(fileFormat.getFrameLength());
            
            player = new PausablePlayer(input);
            
            // start playing
            player.play();
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

}
