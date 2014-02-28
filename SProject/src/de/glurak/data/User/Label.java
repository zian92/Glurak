package de.glurak.data.User;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.io.Serializable;
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

    public LabelProfile getProfile() {
        return profile;
    }

    public void setProfile(LabelProfile profile) {
        this.profile = profile;
    }

    public List<LabelManagerProfile> getManager() {
        return manager;
    }

    public void addLabelManager(LabelManagerProfile pr){
        this.manager.add(pr);
    }

    public void setManager(List<LabelManagerProfile> manager) {
        this.manager = manager;
    }
}
