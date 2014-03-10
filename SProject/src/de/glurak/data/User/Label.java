package de.glurak.data.User;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Repräsentiert das Label. Somit können Narichten und ähnliches
 * an das Label geschrieben werden
 * @author Entscheider
 */
@Entity
public class Label extends Reachable implements Serializable {
    @OneToOne
    private LabelProfile profile;

    @OneToMany(mappedBy = "myLabel")
    private List<LabelManagerProfile> manager;

    public Label(){
        manager= new ArrayList<LabelManagerProfile>();
    }

    public LabelProfile getProfile() {
        return profile;
    }

    public void setProfile(LabelProfile profile) {
        if (this.profile==profile) return;
        this.profile = profile;
        profile.setLabel(this);
    }

    public List<LabelManagerProfile> getManager() {
        return manager;
    }

    /**
     * Fügt den LabelManagerProfile pr in das Label hinzu
     * @param pr das Profil des hinzuzufügenden Labelmanagers
     */
    public void addLabelManager(LabelManagerProfile pr){
        if (manager.contains(pr)) return;
        pr.setMyLabel(this);
        this.manager.add(pr);
    }

    /**
     * Löscht den Labelmanager pr aus dem Label
     * @param pr das Profil des zu löschenden Labelmanagers
     */
    public void removeLabelManager(LabelManagerProfile pr){
        if (!manager.contains(pr))  return;
        this.manager.remove(pr);
        if (pr.getMyLabel()==this)
            pr.setMyLabel(null);
    }

    public void setManager(List<LabelManagerProfile> manager) {
        this.manager = manager;
    }

    @Override
    public String entryPicture() {
        if (profile!=null)
            return profile.getPictureFileNameOrDefaultPictureName();
        return null;
    }
}
