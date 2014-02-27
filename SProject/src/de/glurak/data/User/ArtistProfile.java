package de.glurak.data.User;
import javax.persistence.*;
import java.io.Serializable;
/**
 * Das Profil eines Artisten
 * @author Entscheider
 */
@Entity
public class ArtistProfile extends ListenerProfile implements Serializable{
    //@Id
    //@GeneratedValue
    @Transient
    private long id;

    @ManyToOne
    private LabelProfile myLabel;

    public LabelProfile getMyLabel() {
        return myLabel;
    }

    public void setMyLabel(LabelProfile myLabel) {
        this.myLabel = myLabel;
    }

    public long getId(){return id;}

    @Override
    public String roleName() {
        return "Artist";
    }

    @Override
    public String[] myRights() {
        return Rights.ARTIST_RIGHTS;
    }
}
