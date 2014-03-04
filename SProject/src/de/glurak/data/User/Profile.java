package de.glurak.data.User;
import de.glurak.data.Announcement;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
/**
 *  Oberklasse von ein Profil, das angesehen werden kann. Z.B. Label Profil oder User Profil
 * @author Entscheider
 */
@Entity
public abstract class Profile implements Serializable {

    @Id
    @GeneratedValue
    protected long id;
    protected String address;
    protected String pictureFileName;

    @OneToMany(mappedBy = "belongsTo")
    protected List<Announcement> announcements;

    public Profile(){
        announcements =new ArrayList<Announcement>();
    }

    abstract public Reachable belongTo();

    public abstract String roleName();

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPictureFileName() {
        return pictureFileName;
    }

    public void setPictureFileName(String pictureFileName) {
        this.pictureFileName = pictureFileName;
    }

    public long getId(){return id;}

    public List<Announcement> getAnnouncements() {
        return announcements;
    }

    public void setAnnouncements(List<Announcement> announcements) {
        this.announcements = announcements;
    }

    public void addAnnouncement(Announcement a){
        if (this.announcements.contains(a)) return;
        this.announcements.add(a);
        a.setBelongsTo(this);
    }
}
