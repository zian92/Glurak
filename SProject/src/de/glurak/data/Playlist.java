package de.glurak.data;

import de.glurak.data.User.ListenerProfile;
import de.glurak.data.User.User;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import java.util.Set;
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
            joinColumns={@JoinColumn(name="PLAYLIST_ID", referencedColumnName="id")},
            inverseJoinColumns={@JoinColumn(name="MEDIUM_ID", referencedColumnName="id")})
	private List<Medium> mediumList;

    @ManyToOne
    private User owner;


	private String name;
    @Id
    @GeneratedValue
	private long id;
	

	/**
	 * Konstruktor 
	 * @param id 
	 * @param name
	 * @param playlist  to copy
	 */
	public Playlist (long id,String name, Playlist playlist) {
        this.id=id;
		this.setName(name);
		if (playlist != null) {
			this.setMediumList(playlist.getMediumList());
		}
	}

    /**
     * Leerer Konstrktor
     */
    public Playlist(){mediumList=new ArrayList<Medium>();}

	/**
	 * Konstruktor
	 * @param id
	 * @param name
	 */
	public Playlist (long id,String name) {
		this(id, name, null);
	}

	public List<Medium> getMediumList() {
		return mediumList;
	}

	public void setMediumList(List<Medium> mediumList) {
		this.mediumList = mediumList;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public long getID() {
		return id;
	}

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public void addMedium(Medium m){
        mediumList.add(m);
    }

}
