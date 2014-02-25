package de.glurak.data;

/**
 * Repräsentiert ein Genre. Der Genrename wird in einer Variable title vom Typ 
 * String gespeichert.
 * 
 * @author Darko Dermadi
 *
 */
public class Genre {
	
	private String title;
	private Genre parentGenre;
	private String id;
	
	/**
	 * Konstruktor mit Parameter
	 * @param title der Titel, den das Genre erhalten soll.
	 */
	public Genre(String id, String title, Genre parentGenre) {
		this.id = id;
		this.title = title;
		this.parentGenre = parentGenre;
	}
	
	/**
	 * Getter
	 */
	public String getTitle() {
		return title;
	}
	
	public Genre getParentGenre() {
		return parentGenre;
	}
	
	public String getId() {
		return id;
	}
	
	/**
	 * Setter
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	
	public void setParentGenre(Genre genre) {
		this.parentGenre = genre;
	}

	public void setId(String id) {
		this.id = id;
	}
	
}
