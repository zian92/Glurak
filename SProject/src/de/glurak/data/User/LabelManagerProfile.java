package de.glurak.data.User;


import javax.persistence.*;
import java.io.Serializable;
/**
 * Das Profil eines LabelManagers
 * @author Entscheider
 */
@Entity
public class LabelManagerProfile extends ListenerProfile implements Serializable {
    @ManyToOne
    private Label myLabel;

    public Label getMyLabel() {
        return myLabel;
    }

    public void setMyLabel(Label myLabel) {
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
