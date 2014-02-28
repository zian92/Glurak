package de.glurak.data.User;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import de.glurak.data.Hateable;

import java.io.Serializable;
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


    abstract public Profile getProfile();

    public long getId(){return id;}
    
    /**
	 * Gibt einen Negativpunkt im Hate-Count.
	 * @param hater der User, der negativ bewertet hat.
	 */
	public void hate(User hater);
	
	/**
	 * Gibt einen Positivpunkt im Like-Count.
	 * @param Liker der User, der positiv bewertet hat.
	 */
	public void like(User Liker);
	
	/**
	 * Inkrementiert den Hate-Count.
	 * @return Hate-Count
	 */
	public int hateCount();
	
	/**
	 * Inkrementiert den Like-Count.
	 * @return Like-Count
	 */
	public int likeCount();
	
	/**
	 * Gibt die Liste aller Hater zurück
	 * @return Liste alle Hater
	 */
	public List<User> getHater();
	
	/**
	 * Gibt die Liste aller Liker zurück
	 * @return Liste aller Liker
	 */
	public List<User> getLiker();

    
}
