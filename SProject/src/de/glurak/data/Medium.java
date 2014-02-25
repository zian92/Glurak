package de.glurak.data;

import de.glurak.data.User;

public class Medium {
	
	private String titel;
	private String fileName;
	private User owner;
	//Diskutabel
	//private boolean blocked;
	private String id;
	
	/**
	 * Diese Klasse repr"asentiert ein Medium
	 * @author MxBox
	 */
	public Medium(String Id , String Titel, String File, User Owner){
		this.id = Id;
		setTitel(Titel);
		setFileName(File);
		
	}

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

	public String getId() {
		return id;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}	
}
