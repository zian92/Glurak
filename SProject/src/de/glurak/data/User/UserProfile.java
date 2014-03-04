package de.glurak.data.User;

import java.io.Serializable;
import javax.persistence.*;
/**
 * Oberklasse aller Profile eines Benutzers
 * @author Entscheider
 */
@Entity
public abstract class UserProfile extends Profile implements Serializable {
    public abstract String[] myRights();

    @OneToOne(mappedBy = "profile")
    private User myUser;

    /**
     * Prüft ob der Benutzer das Recht besitzt
     * @param right das zu überprüfende Recht
     * @return true, falls Recht besitzt, sonst false
     */
    public boolean hasRight(String right){
        for (String r: myRights()){
            if (r.equals(right)) return true;
        }
        return false;
    }
    protected String email;
    protected String firstname;
    protected String lastname;
    protected String emailadr;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmailadr() {
        return emailadr;
    }

    public void setEmailadr(String emailadr) {
        this.emailadr = emailadr;
    }


    @Override
    public User belongTo() {
         return myUser;
    }

    public void setUser(User u){
        if (u==myUser) return;
        myUser=u;
        u.setProfile(this);
    }
}
