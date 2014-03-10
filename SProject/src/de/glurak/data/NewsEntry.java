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
    private String picturePathName;
    private String message;
    private Timestamp created;
    @ManyToOne
    private EntryObject source;
    @ManyToOne
    private User owner;

    public NewsEntry(Medium source) {
        this(Query.FOLDER_PICTURE_ICONS + "musicfile.jpg", "Your Song", source);

    }
    public NewsEntry(Album source) {
        this(source.getFilename(), source.getName(), source);
    }

    public NewsEntry(User source) {
        this(source.getProfile().getPictureFileNameOrDefaultPictureName(), source.getProfile().getFirstname(), source);
    }

    public NewsEntry(Label source) {
        this(null, source.getProfile().getName(), source);    
    }
    
    public NewsEntry(String imgFilename, String message, EntryObject h) {
        this.picturePathName = imgFilename;
        this.message = message;
        this.source = h;
    }
    
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
