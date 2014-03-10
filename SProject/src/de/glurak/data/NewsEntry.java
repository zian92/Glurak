package de.glurak.data;

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
    private String picturePathName;
    private String message;
    private Timestamp created;
    //@ManyToOne
    //private Hateable source;
    @ManyToOne
    private User owner;


    public NewsEntry(){
        picturePathName= new String();
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
/*
    public Hateable getSource() {
        return source;
    }

    public void setSource(Hateable source) {
        this.source = source;
    }
*/
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPicturePathName() {
        return picturePathName;
    }

    public void setPicturePathName(String picturePathName) {
        this.picturePathName = picturePathName;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }
}
