package de.glurak.data.User;


import javax.persistence.*;
import java.io.Serializable;
/**
 * Das Profil eines LabelManagers
 * @author Entscheider
 */
@Entity
public class LabelManagerProfile extends ListenerProfile implements Serializable {
    @Id
    @GeneratedValue
    private long id;

    @ManyToOne
    private LabelProfil myLabel;

    public long getId(){return id;}

    public LabelProfil getMyLabel() {
        return myLabel;
    }

    public void setMyLabel(LabelProfil myLabel) {
        this.myLabel = myLabel;
    }
}
