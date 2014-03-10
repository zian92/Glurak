package de.glurak.data;

import java.io.Serializable;
import java.util.List;

import de.glurak.data.User.User;

import javax.persistence.Entity;

/**
 * Das Interface stellt Methoden zum Bewerten anderer User (ausser Admin) sowie Medien bzw
 * Playlists zur Verfügung.
 * @author dadomadi
 *
 */

//@Entity
public interface Hateable extends Serializable {
	
	/**
	 * Gibt einen Negativpunkt im Hate-Count.
	 * @param hater der User, der negativ bewertet hat.
	 */
	public void hate(User hater);
	
	/**
	 * Gibt einen Positivpunkt im Like-Count.
	 * @param liker der User, der positiv bewertet hat.
	 */
	public void like(User liker);
	
	/**
	 * Gibt die Anzahl der Hates zurück
	 * @return Hate-Count
	 */
	public int hateCount();
	
	/**
	 * Gibt die Anzahl der Likes zurück
	 * @return Like-Count
	 */
	public int likeCount();
	
	/**
	 * Gibt die Liste aller Hater zurück
	 * @return Liste alle Hater
	 */
	public List<User> getHater();
	
	/**
	 * Gibt die Liste aller liker zurück
	 * @return Liste aller liker
	 */
	public List<User> getLiker();

}
