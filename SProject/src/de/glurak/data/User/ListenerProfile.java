package de.glurak.data.User;

import de.glurak.data.Playlist;
import java.util.List;
import java.io.Serializable;
import javax.persistence.*;

/**
 * Das Profil eines Listener
 * @author Entscheider
 */
@Entity
public class ListenerProfile extends UserProfile implements Serializable{
    //@Id
    //@GeneratedValue
    @Transient
    private long id;

    protected String birthdate;
    protected String homecountry;
    protected String gender;

    public List<Playlist> getMyPlaylists() {
        return myPlaylists;
    }

    public void setMyPlaylists(List<Playlist> myPlaylists) {
        this.myPlaylists = myPlaylists;
    }

    @OneToMany(mappedBy = "owner")
    protected List<Playlist> myPlaylists;
    @Override
    public String[] myRights() {
        return Rights.LISTENER_RIGHTS;
    }

    @Override
    public String roleName() {
        return "Listener";
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getHomecountry() {
        return homecountry;
    }

    public void setHomecountry(String homecountry) {
        this.homecountry = homecountry;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public long getId(){
        return id;
    }
}
