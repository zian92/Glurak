package de.glurak.data.User;

import javax.persistence.*;
import java.io.Serializable;
/**
 * Das Profil eines Mediums
 * @author Entscheider
 */
@Entity
public class LabelProfil extends Profile implements Serializable{
    @Id
    @GeneratedValue
    private long id;

    //Noch mehr Attribute ?

    public long getId(){return id;}
    @Override
    public String roleName() {
        return "TheLabel";
    }
}
