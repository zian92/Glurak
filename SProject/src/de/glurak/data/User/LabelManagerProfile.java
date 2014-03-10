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

    public LabelManagerProfile(){
        super();
        myLabel=null;
    }

    public Label getMyLabel() {
        return myLabel;
    }

    /**
     * Setzt das Label des Labelmanagers
     * @param myLabel das Label, null falls keins mehr
     */
    public void setMyLabel(Label myLabel) {
        if (this.myLabel== myLabel) return;
        Label oldLabel = this.myLabel;
        this.myLabel = myLabel;
        if (oldLabel != null)
            oldLabel.removeLabelManager(this);
        //if (!myLabel.getManager().contains(this))
        if (myLabel!= null)
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
