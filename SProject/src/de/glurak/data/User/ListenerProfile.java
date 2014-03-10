package de.glurak.data.User;

import javax.persistence.Entity;
import java.io.Serializable;

/**
 * Das Profil eines Listener
 * @author Entscheider
 */
@Entity
public class ListenerProfile extends UserProfile implements Serializable{

    protected String birthdate;
    protected String homecountry;
    protected String gender;

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
}
