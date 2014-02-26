package de.glurak.data.User;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Das Profil eines Mediums
 * @author Entscheider
 */
@Entity
public class LabelProfil extends Profile implements Serializable{
    @Id
    @GeneratedValue
    private long id;

    @OneToMany(mappedBy = "myLabel")
    private List<ArtistProfil> myartists;
    //Noch mehr Attribute ?

    public long getId(){return id;}
    @Override
    public String roleName() {
        return "TheLabel";
    }

    public List<ArtistProfil> getMyartists() {
        return myartists;
    }

    public void setMyartists(List<ArtistProfil> myartists) {
        this.myartists = myartists;
    }
}
