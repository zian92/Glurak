package de.glurak.data.User;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Das Profil eines Mediums
 * @author Entscheider
 */
@Entity
public class LabelProfile extends Profile implements Serializable{

    @OneToMany(mappedBy = "myLabel")
    private List<ArtistProfile> myartists;
    //Noch mehr Attribute ?

    @OneToOne(mappedBy = "profile")
    private Label myLabel;

    public String getName() {
        return name;
    }


    public void addArtist(ArtistProfile pro){
        myartists.add(pro);
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;

    @Override
    public Reachable belongTo() {
        return myLabel;
    }

    public void setLabel(Label l){
        myLabel=l;
    }

    @Override
    public String roleName() {
        return "TheLabel";
    }



    public List<ArtistProfile> getMyartists() {
        return myartists;
    }

    public void setMyartists(List<ArtistProfile> myartists) {
        this.myartists = myartists;
    }
}
