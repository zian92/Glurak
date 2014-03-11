package de.glurak.data.User;

import de.glurak.data.EntryObject;
import de.glurak.data.Hateable;
import de.glurak.data.Playlist;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Oberklasse für alles im Programm,
 * was Narichten empfangen kann oder sonst im System funktional integriert ist.
 * @author Entscheider
 */
@Entity
abstract public class Reachable extends EntryObject implements Serializable, Hateable{

    @ManyToMany
    @JoinTable(
            name="REACHABLE_HATER",
            joinColumns={@JoinColumn(name="REACHABLE_ID")},
            inverseJoinColumns={@JoinColumn(name="USER_ID")})
    private List<User> hater;
    @ManyToMany
    @JoinTable(
            name="REACHABLE_LIKER",
            joinColumns={@JoinColumn(name="REACHABLE_ID")},
            inverseJoinColumns={@JoinColumn(name="USER_ID")})
    private List<User> liker;


    @OneToMany(mappedBy = "owner_of_playlist")
    protected List<Playlist> myPlaylists;


    public Reachable(){
        hater=new ArrayList<User>();
        liker=new ArrayList<User>();
        myPlaylists=new ArrayList<Playlist>();
    }

    abstract public Profile getProfile();

    public List<Playlist> getMyPlaylists() {
        return myPlaylists;
    }

    public void removePlaylist(Playlist p){
    	myPlaylists.remove(p);
    }
    
    public void setMyPlaylists(List<Playlist> myPlaylists) {
        this.myPlaylists = myPlaylists;
    }

    public void addPlaylist(Playlist pl){
        if (myPlaylists.contains(pl)) return;
        myPlaylists.add(pl);
        pl.setOwner(this);
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
