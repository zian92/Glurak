package de.glurak.data;

import de.glurak.data.User.User;
import java.io.Serializable;
import java.util.List;
import javax.persistence.*;

@Entity
public class Medium implements Serializable {

	private String titel;
	private String fileName;

    @ManyToOne
    private Genre myGenre;
	@ManyToOne
	private User owner;
	//Diskutabel
	//private boolean blocked;
    @Id
    @GeneratedValue
    //@Column(name="ID")
	private long id;

    /*@ManyToMany(mappedBy = "mediumList")
    private List<Playlist> l; */

    boolean isLocked;
	
	/**
	 * Diese Klasse repr"asentiert ein Medium
	 * @author MxBox
	 */
	public Medium(long Id , String Titel, String File, User Owner){
		this.id = Id;
		setTitel(Titel);
		setFileName(File);
		
	}
	
	// Hibernate benoetigt leeren Konstruktor
	public Medium() { isLocked=false;}

	public String getTitel() {
		return titel;
	}

	public void setTitel(String titel) {
		this.titel = titel;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public long getId() {
		return id;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

    public boolean isLocked() {
        return isLocked;
    }

    public void setLocked(boolean isLocked) {
        this.isLocked = isLocked;
    }

    public Genre getMyGenre() {
        return myGenre;
    }

    public void setMyGenre(Genre myGenre) {
        this.myGenre = myGenre;
    }
}
