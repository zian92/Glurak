package de.glurak.data;

import de.glurak.data.User.Rights;
import de.glurak.data.User.User;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

@Entity
public class Medium extends EntryObject implements Serializable,Hateable {

	private String titel;
	private String fileName;

    @ManyToMany
    @JoinTable(
            name="MEDIUM_HATER",
            joinColumns={@JoinColumn(name="REACHABLE_ID")},
            inverseJoinColumns={@JoinColumn(name="USER_ID")})
    private List<User> hater;
    @ManyToMany
    @JoinTable(
            name="MEDIUM_LIKER",
            joinColumns={@JoinColumn(name="REACHABLE_ID")},
            inverseJoinColumns={@JoinColumn(name="USER_ID")})
    private List<User> liker;


    @ManyToOne
    private Genre myGenre;
	@ManyToOne
	private User owner;
	//Diskutabel
	//private boolean blocked;

    boolean isLocked;
	
	/**
	 * Diese Klasse repr"asentiert ein Medium
	 * @author MxBox
	 */
	public Medium(String Titel, String File, User owner){
        this();
		setTitel(Titel);
		setFileName(File);
		if (owner!=null)
			setOwner(owner);
	}
	
	// Hibernate benoetigt leeren Konstruktor
	public Medium() {
        isLocked=false;
        hater=new ArrayList<User>();
        liker=new ArrayList<User>();
    }

	public String getTitel() {
		return titel;
	}

	public void setTitel(String titel) {
		this.titel = titel;
	}

    /**
     * Gibt den Dateinamen (den Mp3-Pfad) des Mediums zurück
     * @return den Dateinamen
     */
	public String getFileName() {
		return fileName;
	}

    /**
     * Setzt den Dateinamen des Mediums (Mp3-Pfad)
     * @param fileName der Dateiname
     */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}


	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
        NotEnoughRightException.throwIfNot(owner, Rights.MANAGE_OWN_MEDIEN);
		this.owner = owner;
	}

    /**
     * Gibt zurück ob das Medium gesperrt ist oder nicht
     * @return true falls gesperrt, false sonst
     */
    public boolean isLocked() {
        return isLocked;
    }

    /**
     * Setzt die sperre des Mediums
     * @param isLocked true falls gesperrt sein soll, false sonst
     */
    public void setLocked(boolean isLocked) {
        this.isLocked = isLocked;
    }

    public Genre getMyGenre() {
        return myGenre;
    }

    public void setMyGenre(Genre myGenre) {
        this.myGenre = myGenre;
    }

    
    public void hate(User hater) {
        this.hater.add(hater);
    }

    
    public void like(User liker) {
        this.liker.add(liker);
    }

    public int hateCount() {
        return hater.size();
    }

    
    public int likeCount() {
        return liker.size();
    }

    public List<User> getHater() {
        return hater;
    }

    public List<User> getLiker() {
        return liker;
    }

    @Override
    public String entryPicture() {
        return null;
    }
}
