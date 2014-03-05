package de.glurak.data;

import de.glurak.data.User.ArtistProfile;
import de.glurak.data.User.Profile;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.sql.Timestamp;
/**
 * Klasse um Ankündigungen zu machen
 * @author Entscheider
 */
@Entity
public class Announcement implements Serializable{
    @Id
    @GeneratedValue
    private long id;
    private Timestamp time;
    private String title;
    private String content;
    @ManyToOne
    private Profile belongsTo;

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Profile getBelongsTo() {
        return belongsTo;
    }

    /**
     * Setzt das Profil zu dem diese Ankündigung gehört.
     * @param belongsTo ein Profil, null falls kein Profil zugeordnet
     */
    public void setBelongsTo(Profile belongsTo) {
        if (this.belongsTo==belongsTo)  return;
        Profile oldProfile = this.belongsTo;
        this.belongsTo = belongsTo;
        if (oldProfile!=null)
            oldProfile.removeAnnouncement(this);
        belongsTo.addAnnouncement(this);
    }

    public long getId(){
        return this.id;
    }
}
