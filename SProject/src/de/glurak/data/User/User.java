package de.glurak.data.User;
import de.glurak.data.NotEnoughRightException;
import de.glurak.data.Playlist;

import java.io.Serializable;
import javax.persistence.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

/**
 * Diese Klasse implementiert den User.
 * @author Christopher Distelk�mper
 * Date: 25.01.2014
 */
@Entity
public class User extends Reachable implements Serializable{

    private String username;
    private String passwordHash;

    private boolean isLocked;

    @OneToOne
    private UserProfile profile;

    @ManyToMany
    @JoinTable(
            name="FOLLOWING_RELATION",
            joinColumns={@JoinColumn(name="FOLLOWER_ID")},
            inverseJoinColumns={@JoinColumn(name="FOLLOWS_ID")})
    private List<User> following;

    public User(){
        following=new ArrayList<User>();
        isLocked=false;
    }



	public void setUsername(String username){
		this.username = username;
	}

    public String getUsername(){return username;}

    public void setPassword(String password) throws NoSuchAlgorithmException {
        this.passwordHash=hashString(password);
    }

    private String hashString(String password) throws NoSuchAlgorithmException {
        String res;
        MessageDigest md = MessageDigest.getInstance("SHA");
        md.update(password.getBytes());
        res = new String(md.digest());
        return res ;
    }

    public boolean checkPassword(String p) throws NoSuchAlgorithmException {
        return this.passwordHash.equals(hashString(p));
    }

    /**
     * Gibt zurück ob der Benutzer gesperrt ist
     * @return true falls gesperrt, false wenn nicht
     */
    public boolean isLocked() {
        return isLocked;
    }

    public void setLocked(boolean isLocked) {
        this.isLocked = isLocked;
    }

    public String getPasswordHash(){return passwordHash;}

    /**
     * Gibt das Profil zurück, welches den Benutzer zugewiesen ist
     * @return  das Benutzerprofil, null falls es keines hat
     */
    public UserProfile getProfile() {
        return profile;
    }

    /**
     * Setzt das Benutzerprofil
     * @param profile das Profil, null, falls es keines haben soll
     */
    public void setProfile(UserProfile profile) {
        if (this.profile==profile) return;
        if (this.profile!=null){
            UserProfile tmp=this.profile;
            this.profile=null;
            tmp.setUser(null);
        }
        this.profile = profile;
        if (profile!=null)
            profile.setUser(this);
    }

    /**
     * Gibt die Liste aller Benutzer an, die dieser hier favorisiert
     * @return die Liste aller favorisierter Benutzer
     */
    public List<User> getFollowing() {
        return following;
    }

    /**
     * Setzt die Liste aller Benutzer an, die dieser hier favorisiert
     * @param following die Liste favorisierte Benutzer
     */
    public void setFollowing(List<User> following) {
        this.following = following;
    }

    public void follow(User who){
        NotEnoughRightException.throwIfNot(this,Rights.FOLLOW_USER);
        this.following.add(who);
    }

    @Override
    public String entryPicture() {
        if (profile!=null)
            return profile.getPictureFileNameOrDefaultPictureName();
        return null;
    }
}
