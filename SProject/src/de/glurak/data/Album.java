package de.glurak.data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 
 * @author dadomadi
 *
 */

@Entity
public class Album extends Playlist implements Serializable {
	
	// kann zusätzlich Metainformationen enthalten
	// Erscheinungsjahr
	
	private String yearOfPublication;
	// Album-Cover
	private String filename;
	
	// Für Hibernate leerer Konstruktor
	public Album() { }
	
	public String getYearOfPublication() {
		return yearOfPublication;
	}
	
	public void setYearOfPublication(String y) {
		yearOfPublication = y;
	}
	
	public String getFilename() {
		return filename;
	}
	
	public void setFilename(String f) {
		filename = f;
	}

}
