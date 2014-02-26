package de.glurak.data.User;
import java.io.Serializable;
import javax.persistence.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Diese Klasse implementiert den User.
 * @author: Christopher Distelk�mper
 * Date: 25.01.2014
 */
@Entity
public class User implements Serializable{
	
	// Attribute
    @Id
    @GeneratedValue
	private long id;

    private String username;
    private String passwordHash;

    private boolean isLocked;

    @OneToOne
    private UserProfile profile;

	public long getId(){
		return this.id;
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

    public boolean isLocked() {
        return isLocked;
    }

    public void setLocked(boolean isLocked) {
        this.isLocked = isLocked;
    }
}
