package de.glurak.data;

import java.util.LinkedList;

/**
 * @author Zengo
 *
 */
public class Playlist {

	private LinkedList<Medium> mediumList;
	private String name;
	private String ID;
	

	/**
	 * Konstruktor 
	 * @param id 
	 * @param name
	 * @param playlist  to copy
	 */
	public Playlist (String id,String name, Playlist playlist) {
		this.setName(name);
		if (playlist != null) {
			this.setMediumList(playlist.getMediumList());
		}
	}
	
	/**
	 * Konstruktor
	 * @param id
	 * @param name
	 */
	public Playlist (String id,String name) {
		this(id, name, null);
	}

	public LinkedList<Medium> getMediumList() {
		return mediumList;
	}

	public void setMediumList(LinkedList<Medium> mediumList) {
		this.mediumList = mediumList;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getID() {
		return ID;
	}

}
