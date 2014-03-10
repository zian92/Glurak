package de.glurak.data.User;

import de.glurak.Query;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.io.Serializable;
import java.util.ArrayList;
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
    private String labelImageFilename;

    public String getName() {
        return name;
    }



    public LabelProfile(){
        super();
        myartists=new ArrayList<ArtistProfile>();
    }
    /**
     * Fügt einen Artist den Label hinzu
     * @param pro
     */
    public void addArtist(ArtistProfile pro){
        if (pro==null) return;
        if (myartists.contains(pro)) return;
        myartists.add(pro);
        pro.setMyLabel(this);
    }

    /**
     * Löscht den Artisten mit den Profil pro aus den Profil
     * @param pro das Profil des zu löschenden
     */
    public void removeArtist(ArtistProfile pro){
        if (pro == null) return;
        if (!myartists.contains(pro)) return;
        myartists.remove(pro);
        if (pro.getMyLabel()==this)
            pro.setMyLabel(null);
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
        if (l==myLabel) return;
        myLabel=l;
        if (l!=null)
            l.setProfile(this);
    }

    public Label getMyLabel() {
        return myLabel;
    }

    @Override
    public String roleName() {
        return "TheLabel";
    }

    @Override
    public String getPictureFileNameOrDefaultPictureName(){
    	if (pictureFileName.isEmpty()){
    		return (Query.FOLDER_PICTURE_ICONS + "userf.jpg");
  		
    	}
    	return pictureFileName;
    }

    public List<ArtistProfile> getMyartists() {
        return myartists;
    }

    public void setMyartists(List<ArtistProfile> myartists) {
        this.myartists = myartists;
    }


	public String getLabelImageFilename() {
		return labelImageFilename;
	}


	public void setLabelImageFilename(String labelImageFilename) {
		this.labelImageFilename = labelImageFilename;
	}
}
