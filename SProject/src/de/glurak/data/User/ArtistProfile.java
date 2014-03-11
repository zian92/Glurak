package de.glurak.data.User;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.io.Serializable;
/**
 * Das Profil eines Artisten
 * @author Entscheider
 */
@Entity
public class ArtistProfile extends ListenerProfile implements Serializable, Comparable<ArtistProfile> {

    @ManyToOne
    private LabelProfile myLabel_profile;

    /**
     * Kosntruktor
     */
    public ArtistProfile(){
        super();
        myLabel_profile =null;
    }

    /**
     * Artistprofile wird aus einen Listenerprofile angelegt.
     * @param otherprof Listenerprofile, der zum Artist werden soll
     */
    public ArtistProfile(ListenerProfile otherprof){
        this.setLastname(otherprof.getLastname());
        this.setBirthdate(otherprof.getBirthdate());
        this.setFirstname(otherprof.getFirstname());
        this.setAddress(otherprof.getAddress());
        this.setEmail(otherprof.getEmail());
        this.setCountry(otherprof.getCountry());
        this.setFemale(otherprof.isFemale());
        this.setHomecountry(otherprof.getHomecountry());
        this.setPictureFileName(otherprof.getPictureFileName());
    }

    /**
     * @return das Label in dem der Artist ist
     */
    public LabelProfile getMyLabel() {
        return myLabel_profile;
    }

    /**
     * Setzt das Label des Artisten
     * @param myLabel das Label, oder null falls kein Label
     */
    public void setMyLabel(LabelProfile myLabel) {
        if (myLabel==this.myLabel_profile) return;
        LabelProfile oldLabel = this.myLabel_profile;
        this.myLabel_profile = myLabel;
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
