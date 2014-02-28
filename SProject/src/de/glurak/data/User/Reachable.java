package de.glurak.data.User;

import javax.persistence.*;

import de.glurak.data.Hateable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Oberklasse für alles im Programm,
 * was Narichten empfangen kann.
 * @author Entscheider
 */
@Entity
abstract public class Reachable  implements Serializable, Hateable{
   
	@Id
    @GeneratedValue
    private long id;

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

    public long getId(){return id;}

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
