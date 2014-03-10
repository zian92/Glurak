package de.glurak.data.User;
import javax.persistence.*;
import java.io.Serializable;
/**
 * Das Profil eines Artisten
 * @author Entscheider
 */
@Entity
public class ArtistProfile extends ListenerProfile implements Serializable, Comparable<ArtistProfile> {

    @ManyToOne
    private LabelProfile myLabel;

    public LabelProfile getMyLabel() {
        return myLabel;
    }

    /**
     * Setzt das Label des Artisten
     * @param myLabel das Label, oder null falls kein Label
     */
    public void setMyLabel(LabelProfile myLabel) {
        if (myLabel==this.myLabel) return;
        LabelProfile oldLabel = this.myLabel;
        this.myLabel = myLabel;
        if (oldLabel!=null){
            oldLabel.removeArtist(this);
        }
        if (myLabel!=null)
             myLabel.addArtist(this);
    }

    @Override
    public String roleName() {
        return "Artist";
    }

    @Override
    public String[] myRights() {
        return Rights.ARTIST_RIGHTS;
    }

	public int compareTo(ArtistProfile a) {
		Integer myHates = this.belongTo().hateCount();
		Integer hates = a.belongTo().hateCount();
		
		return myHates.compareTo(hates);
	}
}
