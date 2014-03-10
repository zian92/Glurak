package de.glurak.data;

import de.glurak.Query;
import de.glurak.data.User.Label;
import de.glurak.data.User.User;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author Entscheider
 */
@Entity
public class NewsEntry implements Serializable {
    @Id
    @GeneratedValue
    private long id;
    private String message;
    private Timestamp created;
    @ManyToOne
    private EntryObject source;
    @ManyToOne
    private User owner;

    public NewsEntry(Medium source) {
        this("Your Song", source);

    }
    public NewsEntry(Album source) {
        this( source.getName(), source);
    }

    public NewsEntry(User source) {
        this(source.getProfile().getFirstname(), source);
    }

    public NewsEntry(Label source) {
        this(null,  source);
    }
    
    public NewsEntry(String message, EntryObject h) {
        this.message = message;
        this.source = h;
    }
    
    public NewsEntry(){
        message = new String();
    }


    public User getOwner() {
        return owner;
    }

    public long getId(){
        return id;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public EntryObject getSource() {
        return source;
    }

    public void setSource(EntryObject source) {
        this.source = source;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }
}
