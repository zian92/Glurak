package de.glurak.data;

import de.glurak.Query;
import de.glurak.data.User.Rights;
import de.glurak.data.User.User;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Die Klasse Album erweitert die Klasse Playlist um die Möglichkeit,
 * ein Erscheinungsjahr und ein Album-Cover anzugeben
 * @author dadomadi
 *
 */

@Entity
public class Album extends Playlist implements Serializable {
	
	// kann zusätzlich Metainformationen enthalten
	// Erscheinungsjahr
	
	private String yearOfPublication;
	// Album-Cover
	private String filename = Query.FOLDER_PICTURE_ICONS + "playlistic.jpg";
	
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

    @Override
    public void setOwner(User owner) {
        NotEnoughRightException.throwIfNot(owner, Rights.MANAGE_ALBUM);
        super.setOwner(owner);
    }
}
