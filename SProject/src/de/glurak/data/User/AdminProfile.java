package de.glurak.data.User;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Profil eines Admins
 * @author Entscheider
 */
@Entity
public class AdminProfile extends UserProfile implements Serializable {

    @Override
    public String[] myRights() {
        return Rights.ADMIN_RIGHTS;
    }

    @Override
    public String roleName() {
        return "Admin";
    }
}
