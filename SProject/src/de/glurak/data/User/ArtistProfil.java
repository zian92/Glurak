package de.glurak.data.User;
import javax.persistence.*;
import java.io.Serializable;
/**
 * Das Profil eines Artisten
 * @author Entscheider
 */
@Entity
public class ArtistProfil extends ListenerProfile implements Serializable{
    @Id
    @GeneratedValue
    private long id;

    @ManyToOne
    private LabelProfil myLabel;

    public LabelProfil getMyLabel() {
        return myLabel;
    }

    public void setMyLabel(LabelProfil myLabel) {
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
