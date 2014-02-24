package de.js.adressbuch;

import java.io.Serializable;

public class AdressEintrag implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String vorname = "";
	private String anrede = "";
	private String nachname = "";
	private String strasse = "";
	private String plz = "";
	private String ort = "";
	private String telefon = "";
	private String handy = "";
	private String email = "";
	private String land = "";

	public AdressEintrag(String vor, String nach, String anre) {
		this.anrede = anre;
		this.vorname = vor;
		this.nachname = nach;
	}

	public AdressEintrag(String vorname, String nachname, String anrede, String strasse, String telefon, String handy, String plz, String ort, String email, String land) {
		this(vorname, nachname, anrede);
		this.strasse = strasse;
		this.telefon = telefon;
		this.handy = handy;
		this.plz = plz;
		this.ort = ort;
		this.email = email;
		this.land = land;
	}

	public AdressEintrag() {
	}

	/**
	 * Gibt alle Einträge als String-Array wieder.
	 * 
	 * @return
	 */
	public String[] toStringArray() {
		String[] s = new String[10];
		s[0] = vorname;
		s[1] = nachname;
		s[2] = anrede;
		s[3] = strasse;
		s[4] = telefon;
		s[5] = handy;
		s[6] = plz;
		s[7] = ort;
		s[8] = email;
		s[9] = land;
		return s;
	}

	public String getNachname() {
		return nachname;
	}

	public void setNachname(String nachname) {
		this.nachname = nachname;
	}

	public String getVorname() {
		return vorname;
	}

	public void setVorname(String vorname) {
		this.vorname = vorname;
	}

	public String getStrasse() {
		return strasse;
	}

	public void setStrasse(String strasse) {
		this.strasse = strasse;
	}

	public String getTelefon() {
		return telefon;
	}

	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}

	public String getHandy() {
		return handy;
	}

	public void setHandy(String handy) {
		this.handy = handy;
	}

	public String getPlz() {
		return plz;
	}

	public void setPlz(String plz) {
		this.plz = plz;
	}

	public String getOrt() {
		return ort;
	}

	public void setOrt(String ort) {
		this.ort = ort;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLand() {
		return land;
	}

	public void setLand(String land) {
		this.land = land;
	}

	public String getAnrede() {
		return anrede;
	}

	public void setAnrede(String anrede) {
		this.anrede = anrede;
	}
}
