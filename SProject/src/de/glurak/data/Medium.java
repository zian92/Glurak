package de.glurak.data;

import de.glurak.data.User.User;
import java.io.Serializable;
import javax.persistence.*;

@Entity
public class Medium implements Serializable {

	private String titel;
	private String fileName;
	@ManyToOne
	private User owner;
	//Diskutabel
	//private boolean blocked;
    @Id
    @GeneratedValue
    @Column(name="ID")
	private long id;
	
	/**
	 * Diese Klasse repr"asentiert ein Medium
	 * @author MxBox
	 */
	public Medium(long Id , String Titel, String File, User Owner){
		this.id = Id;
		setTitel(Titel);
		setFileName(File);
		
	}
	
	// Hibernate benoetigt leeren Konstruktor
	public Medium() { }

	public String getTitel() {
		return titel;
	}

	public void setTitel(String titel) {
		this.titel = titel;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public long getId() {
		return id;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}	
}
