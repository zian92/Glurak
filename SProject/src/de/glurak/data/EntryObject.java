package de.glurak.data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * @author Entscheider
 */
@Entity
abstract public class EntryObject implements Serializable, Hateable{
    @Id
    @GeneratedValue
    protected long id;

    public long getId(){
        return id;
    }
}
