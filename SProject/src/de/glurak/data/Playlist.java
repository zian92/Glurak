package de.glurak.data;

import de.glurak.data.User.Reachable;
import de.glurak.data.User.Rights;
import de.glurak.data.User.User;
import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import javax.persistence.*;
/**
 * @author Zengo
 *
 */
@Entity
public class Playlist extends EntryObject implements Serializable, Hateable, Comparable<Playlist>{

    @ManyToMany
    @JoinTable(
            name="PLAYLIST_SONGS",
            joinColumns={@JoinColumn(name="PLAYLIST_ID")},
            inverseJoinColumns={@JoinColumn(name="MEDIUM_ID")})
	private List<Medium> mediumList;
    @Transient
    private int index;

    @ManyToMany
    @JoinTable(
            name="PLAYLIST_HATER",
            joinColumns={@JoinColumn(name="REACHABLE_ID")},
            inverseJoinColumns={@JoinColumn(name="USER_ID")})
    private List<User> hater;
    @ManyToMany
    @JoinTable(
            name="PLAYLIST_LIKER",
            joinColumns={@JoinColumn(name="REACHABLE_ID")},
            inverseJoinColumns={@JoinColumn(name="USER_ID")})
    private List<User> liker;

    @ManyToOne
    private Reachable owner;


	private String name;
	

	/**
	 * Konstruktor 
	 * Index wird mit 0 initiated
	 * @param id 
	 * @param name
	 * @param playlist  to copy
	 */
	public Playlist (long id,String name, Playlist playlist) {
        this();
        this.id=id;
		this.setName(name);
		this.index = 0;
		if (playlist != null) {
			this.setMediumList(playlist.getMediumList());
		}
	}

    /**
     * Leerer Konstrktor
     */
    public Playlist(){
        mediumList=new ArrayList<Medium>();
        hater=new ArrayList<User>();
        liker=new ArrayList<User>();
    }

	/**
	 * Konstruktor
	 * @param id
	 * @param name
	 */
	public Playlist (long id,String name) {
		this(id, name, null);
	}
	
	

	public void add(int index, Medium medium) {
		this.getMediumList().add(index, medium);
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


    public Reachable getOwner() {
        return owner;
    }

    public void setOwner(Reachable owner) {
        if (owner instanceof User)
            NotEnoughRightException.throwIfNot((User)owner, Rights.MANAGE_PLAYLIST);
        if (this.owner==owner) return;
        this.owner = owner;
        owner.addPlaylist(this);
    }

    public void addMedium(Medium m){
        mediumList.add(m);
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

	public int compareTo(Playlist o) {
		Integer myHates = this.hateCount();
		Integer hates = o.hateCount();
		
		return myHates.compareTo(hates);
	}

    @Override
    public String entryPicture() {
        return null;
    }
}
