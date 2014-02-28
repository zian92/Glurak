package de.glurak.data.User;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.io.Serializable;

/**
 * Oberklasse für alles im Programm,
 * was Narichten empfangen kann.
 * @author Entscheider
 */
@Entity
abstract public class Reachable  implements Serializable{
    @Id
    @GeneratedValue
    private long id;


    abstract public Profile getProfile();

    public long getId(){return id;}
}
