package de.glurak.data;

import java.util.LinkedList;
import java.io.Serializable;
import javax.persistence.*;
/**
 * @author Zengo
 *
 */
@Entity
public class Playlist implements Serializable{

    @ManyToMany
    @JoinTable(
            name="PLAYLIST_SONGS",
            joinColumns={@JoinColumn(name="PLAYLIST_ID", referencedColumnName="ID")},
            inverseJoinColumns={@JoinColumn(name="MEDIUM_ID", referencedColumnName="ID")})
	private LinkedList<Medium> mediumList;
	private String name;
    @Id
    @GeneratedValue
    @Column(name="ID")
	private long ID;
	

	/**
	 * Konstruktor 
	 * @param id 
	 * @param name
	 * @param playlist  to copy
	 */
	public Playlist (long id,String name, Playlist playlist) {
        this.ID=id;
		this.setName(name);
		if (playlist != null) {
			this.setMediumList(playlist.getMediumList());
		}
	}

    /**
     * Leerer Konstrktor
     */
    public Playlist(){}

	/**
	 * Konstruktor
	 * @param id
	 * @param name
	 */
	public Playlist (long id,String name) {
		this(id, name, null);
	}

	public LinkedList<Medium> getMediumList() {
		return mediumList;
	}

	public void setMediumList(LinkedList<Medium> mediumList) {
		this.mediumList = mediumList;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public long getID() {
		return ID;
	}

}
