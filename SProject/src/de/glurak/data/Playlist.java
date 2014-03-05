package de.glurak.data;

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
public class Playlist implements Serializable,Hateable{

    @ManyToMany
    @JoinTable(
            name="PLAYLIST_SONGS",
            joinColumns={@JoinColumn(name="PLAYLIST_ID", referencedColumnName="ID")},
            inverseJoinColumns={@JoinColumn(name="MEDIUM_ID", referencedColumnName="ID")})
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
    private User owner;


	private String name;
    @Id
    @GeneratedValue
	private long id;
	

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
	

    //TODO: !!! DIE getCurrent funktionen und ähnliche gehören hier nicht hin !!!!!!
	
	/**
	 * Gibt aktuelles Medium zurück
	 * @return Medium der MediumList and der Stelle index
	 */
	public Medium getCurrent() {
		return this.getMediumList().get(this.index);
	}
	
	/** Setzt aktuelles Medium
	 * @param index des neuen aktuellen Mediums
	 */
	public void setCurrent(int index){
		this.index = index%this.getMediumList().size();
		System.out.println("Current :" +index+"");
		
	}
	/**
	 * Setzt index++
	 * Gibt nächstes Medium zurück
	 * @return medium an der Stelle index+1
	 */
	public Medium getNext() {
		this.index= (index+1)%this.getMediumList().size();
		return this.getMediumList().get(index);
	}
	
	/**
	 * Gibt vorheriges Medium zurück
	 * @return medium an der Stelle index-1
	 */
	public Medium getPrevious() {
		this.index--;
		if(index<0){index=this.getMediumList().size()-1;}
		return this.getMediumList().get(index);
	}

    //TODO: !! JA ALLE DA OBEN !! SIE SOLLTEN IN EINE PLAYQUEUE-KLASSE DIE DIE PLAYLIST IMPORTIERT !!!!!!!!!!!!!!!
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
	
	public long getID() {
		return id;
	}

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        NotEnoughRightException.throwIfNot(owner, Rights.MANAGE_PLAYLIST);
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
}
