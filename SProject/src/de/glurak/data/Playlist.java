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
    private Reachable owner_of_playlist;


	private String name;
	

	/**
	 * Konstruktor 
	 * Index wird mit 0 initiated
	 * @param name
	 * @param playlist  to copy
	 */
	public Playlist (String name, Playlist playlist) {
        this();
		this.setName(name);
		this.index = 0;
		if (playlist != null) {
			List<Medium> nList=new ArrayList<Medium>();
			for (Medium m: playlist.getMediumList()){
				nList.add(m);
			}
			this.setMediumList(nList);
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
	 * @param name
	 */
	public Playlist (String name) {
		this( name, null);
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
        return owner_of_playlist;
    }

    public void setOwner(Reachable owner) {
        if (owner instanceof User)
            NotEnoughRightException.throwIfNot((User)owner, Rights.MANAGE_PLAYLIST);
        if (this.owner_of_playlist==owner) return;
        Reachable tmp = this.owner_of_playlist;
        this.owner_of_playlist = owner;
        if (tmp!=null){
            tmp.removePlaylist(this);
        }
        if (owner !=null)
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
