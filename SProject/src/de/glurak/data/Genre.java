package de.glurak.data;

/**
 * Repräsentiert die Angabe eines Genres. Der Genrename wird in einer Variable title vom Typ 
 * String gespeichert.
 * 
 * @author Darko Dermadi
 *
 */
public class Genre {
	
	private String title;
	
	/**
	 * Konstruktor mit Parameter
	 * @param title der Titel, den das Genre erhalten soll.
	 */
	public Genre(String title) {
		this.title = title;
	}
	
	/**
	 * Konstruktor ohne Parameter
	 */
	public Genre() {
		this.title = "neues Genre";
	}
	
	
	/**
	 * Getter
	 * 
	 * @return title der aktuelle Titel des Genres
	 */
	public String getTitle() {
		return title;
	}
	
	/**
	 * Setter
	 * 
	 * @param title der Titel, den das Genre erhalten soll
	 */
	public void setGenre(String title) {
		this.title = title;
	}
	
}
