package de.glurak.data.User;
import java.io.Serializable;
import javax.persistence.*;
/**
 *  Oberklasse von ein Profil, das angesehen werden kann. Z.B. Label Profil oder User Profil
 * @author Entscheider
 */
@Entity
public abstract class Profile implements Serializable {

    @Id
    @GeneratedValue
    private long id;
    protected String address;
    protected String pictureFileName;

    public abstract String roleName();

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPictureFileName() {
        return pictureFileName;
    }

    public void setPictureFileName(String pictureFileName) {
        this.pictureFileName = pictureFileName;
    }

    public long getId(){return id;}

}
