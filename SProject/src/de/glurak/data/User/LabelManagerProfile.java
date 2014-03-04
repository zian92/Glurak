package de.glurak.data.User;


import javax.persistence.*;
import java.io.Serializable;
/**
 * Das Profil eines LabelManagers
 * @author Entscheider
 */
@Entity
public class LabelManagerProfile extends ListenerProfile implements Serializable {
    @ManyToOne(fetch=FetchType.LAZY)
    private Label myLabel;

    public Label getMyLabel() {
        return myLabel;
    }

    public void setMyLabel(Label myLabel) {
        if (this.myLabel== myLabel) return;
        this.myLabel = myLabel;
        //if (!myLabel.getManager().contains(this))
        myLabel.addLabelManager(this);
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
