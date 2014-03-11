package de.glurak.data.User;

import de.glurak.data.Announcement;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
/**
 *  Oberklasse von ein Profil, das angesehen werden kann. Z.B. Label Profil oder User Profil
 * @author Entscheider
 */
@Entity
public abstract class Profile implements Serializable {

    @Id
    @GeneratedValue
    protected long id;
    protected String address		 = "";
    protected String pictureFileName = "";

    @OneToMany(mappedBy = "belongsTo")
    protected List<Announcement> announcements;

    public Profile(){
        announcements =new ArrayList<Announcement>();
    }

    /**
     * Ein Name der später angezeigt wird.
     * @return ein anzeigename
     */
    public abstract String viewName();

    /**
     * Gibt den zurück, zu dem das Profil gehört
     * @return den Besitzer des Profils
     */
    abstract public Reachable belongTo();

    /**
     * Setzt den zurück, zu dem man gehört.
     * @param r der neue Reachable, null fall kein
     */
    abstract public void setBelongsTo(Reachable r);

    /**
     * Gibt den Rollennamen des Profils zurück
     * Z.B. beim Artist "Artist" und bei Listener "Listener2
     * @return den Rollennamen
     */
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
    
    abstract public String getPictureFileNameOrDefaultPictureName();

    public void setPictureFileName(String pictureFileName) {
        this.pictureFileName = pictureFileName;
    }

    public long getId(){return id;}

    /**
     * Gibt alle Ankündigungen zurück
     * @return die Liste aller Ankündigungen
     */
    public List<Announcement> getAnnouncements() {
        return announcements;
    }

    /**
     * Setzte alle Anündigungen
     * @param announcements die Liste der Ankündigungen
     */
    public void setAnnouncements(List<Announcement> announcements) {
        this.announcements = announcements;
    }

    /**
     * Fügt eine Ankündigung hinzu
     * @param a die Ankündigung
     */
    public void addAnnouncement(Announcement a){
        if (a==null) return;
        if (this.announcements.contains(a)) return;
        this.announcements.add(a);
        a.setBelongsTo(this);
    }

    /**
     * Löscht eine Ankündigung a
     * @param a die zu löschende Ankündigung
     */
    public void removeAnnouncement(Announcement a){
        if (a==null) return;
        if (!this.announcements.contains(a)) return;
        this.announcements.remove(a);
        if (a.getBelongsTo()==this)
            a.setBelongsTo(null);
    }
}
