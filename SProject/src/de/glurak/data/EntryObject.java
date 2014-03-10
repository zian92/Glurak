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

    /**
     * Gibt das Bild zurück, dass beim NewsEntry angezgeit wird.
     * @return der Pfad des Bildes , null falls kein Bild
     */
    abstract public String entryPicture();

    public long getId(){
        return id;
    }
}
