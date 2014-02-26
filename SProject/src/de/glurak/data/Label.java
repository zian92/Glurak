package de.glurak.data;

import java.beans.Beans;
import java.util.List;
import java.util.ArrayList;
import java.io.Serializable;
import javax.persistence.*;
/**
 * Diese Klasse repräsentiert ein Label
 * @author Entscheider
 */

@Entity
public class Label implements Serializable {
	
	@Id
	@GeneratedValue
    private String logoFileName;
    private String name;
    private String description;
    private String address;
    private String mail;
    private long id;

    @Override
    public boolean equals(Object other){
        if (other==this) return true;
        if (other instanceof Label ){
            return ((Label) other).id == (id);
        }
        return false;
    }

    private List<Artist> myartists;

    public Label(){
        myartists= new ArrayList<Artist>();
    }

    public Label(long id){
        this.id=id;
        myartists=new ArrayList<Artist>();
    }

    public String getLogoFileName() {
        return logoFileName;
    }

    public void setLogoFileName(String logoFileName) {
        this.logoFileName = logoFileName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public List<Artist> getMyartists() {
        return myartists;
    }

    public void setMyartists(List<Artist> myartists) {
        this.myartists = myartists;
    }

    public void addArtist(Artist art){
        myartists.add(art);
    }

    public void removeArtist(Artist art){
        myartists.remove(art);
    }


    public long getId(){
        return id;
    }
}
