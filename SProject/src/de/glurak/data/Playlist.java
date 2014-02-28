package de.glurak.data;

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
	
	
	
	/**
	 * Gibt aktuelles Medium zurück
	 * @return Medium der MediumList and der Stelle index
	 */
	public Medium getCurrent() {
		return this.getMediumList().get(this.index);
	}
	
	/**
	 * Setzt index++
	 * Gibt nächstes Medium zurück
	 * @return medium an der Stelle index+1
	 */
	public Medium getNext() {
		this.index++;
		return this.getMediumList().get(index%this.getMediumList().size());
	}
	
	/**
	 * Gibt vorheriges Medium zurück
	 * @return medium an der Stelle index-1
	 */
	public Medium getPrevious() {
		this.index--;
		return this.getMediumList().get(index%this.getMediumList().size());
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


    @Override
    public void hate(User hater) {
        this.hater.add(hater);
    }

    @Override
    public void like(User liker) {
        this.liker.add(liker);
    }

    @Override
    public int hateCount() {
        return hater.size();
    }

    @Override
    public int likeCount() {
        return liker.size();
    }

    @Override
    public List<User> getHater() {
        return hater;
    }

    @Override
    public List<User> getLiker() {
        return liker;
    }
}
