package de.glurak.data;

import java.io.Serializable;
import javax.persistence.*;
/**
 * Repr�sentiert ein Genre. Der Genrename wird in einer Variable title vom Typ 
 * String gespeichert.
 * 
 * @author Darko Dermadi
 *
 */

@Entity
public class Genre implements Serializable {
	
	@Id
	@GeneratedValue
	private long id;
	@ManyToOne
	private Genre parentGenre;	
	private String title;
	
	
	/**
	 * Konstruktor mit Parameter
	 * @param title der Titel, den das Genre erhalten soll.
	 */
	public Genre(long id, String title, Genre parentGenre) {
		this.id = id;
		this.title = title;
		this.parentGenre = parentGenre;
	}
	
	// Hibernate benoetigt leeren Konstruktor
	public Genre() { }
	
	public String getTitle() {
		return title;
	}
	
	public Genre getParentGenre() {
		return parentGenre;
	}
	
	public long getId() {
		return id;
	}
	
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public void setParentGenre(Genre genre) {
		this.parentGenre = genre;
	}

	public void setId(long id) {
		this.id = id;
	}
	
}
