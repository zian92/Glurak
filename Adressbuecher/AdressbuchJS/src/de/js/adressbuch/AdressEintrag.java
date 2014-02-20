package de.js.adressbuch;

public class AdressEintrag {

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
		setAnrede(anre);
		vorname = vor;
		nachname = nach;
	}

	public AdressEintrag(String vorname, String nachname, String anrede, String strasse, String telefon, String handy, String plz, String ort, String email, String land) {
		this.vorname = vorname;
		this.nachname = nachname;
		this.setAnrede(anrede);
		this.strasse = strasse;
		this.telefon = telefon;
		this.handy = handy;
		this.plz = plz;
		this.ort = ort;
		this.email = email;
		this.land = land;
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
