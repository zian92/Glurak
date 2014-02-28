package de.glurak.data;

import java.util.List;

import de.glurak.data.User.User;

/**
 * Das Interface stellt Methoden zum Bewerten anderer User (ausser Admin) sowie Medien bzw
 * Playlists zur Verfügung.
 * @author dadomadi
 *
 */

public interface Hateable {
	
	/**
	 * Gibt einen Negativpunkt im Hate-Count.
	 * @param hater der User, der negativ bewertet hat.
	 */
	public void hate(User hater);
	
	/**
	 * Gibt einen Positivpunkt im Like-Count.
	 * @param Liker der User, der positiv bewertet hat.
	 */
	public void like(User liker);
	
	/**
	 * Inkrementiert den Hate-Count.
	 * @return Hate-Count
	 */
	public int hateCount();
	
	/**
	 * Inkrementiert den Like-Count.
	 * @return Like-Count
	 */
	public int likeCount();
	
	/**
	 * Gibt die Liste aller Hater zurück
	 * @return Liste alle Hater
	 */
	public List<User> getHater();
	
	/**
	 * Gibt die Liste aller Liker zurück
	 * @return Liste aller Liker
	 */
	public List<User> getLiker();

}
