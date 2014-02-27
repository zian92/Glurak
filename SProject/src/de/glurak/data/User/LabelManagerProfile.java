package de.glurak.data.User;


import javax.persistence.*;
import java.io.Serializable;
/**
 * Das Profil eines LabelManagers
 * @author Entscheider
 */
@Entity
public class LabelManagerProfile extends ListenerProfile implements Serializable {
    //@Id
    //@GeneratedValue
    @Transient
    private long id;

    @ManyToOne
    private LabelProfile myLabel;

    public long getId(){return id;}

    public LabelProfile getMyLabel() {
        return myLabel;
    }

    public void setMyLabel(LabelProfile myLabel) {
        this.myLabel = myLabel;
    }

    @Override
    public String[] myRights() {
        return Rights.LABELMANAGER_RIGHTS;
    }

    @Override
    public String roleName() {
        return "LabelManager";
    }
}
