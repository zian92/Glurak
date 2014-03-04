package de.glurak.data;

import de.glurak.data.User.Rights;
import de.glurak.data.User.User;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

@Entity
public class Medium implements Serializable,Hateable {

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
        NotEnoughRightException.throwIfNot(owner, Rights.MANAGE_OWN_MEDIEN);
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
