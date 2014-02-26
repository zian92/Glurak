package de.glurak.data.User;

import de.glurak.data.Label;

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
    private Label myLabel;

    public long getId(){return id;}

    public Label getMyLabel() {
        return myLabel;
    }

    public void setMyLabel(Label myLabel) {
        this.myLabel = myLabel;
    }
}
