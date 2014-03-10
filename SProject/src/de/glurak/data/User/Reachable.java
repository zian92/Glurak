package de.glurak.data.User;

import javax.persistence.*;

import de.glurak.data.EntryObject;
import de.glurak.data.Hateable;

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


    public Reachable(){
        hater=new ArrayList<User>();
        liker=new ArrayList<User>();
    }

    abstract public Profile getProfile();

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
