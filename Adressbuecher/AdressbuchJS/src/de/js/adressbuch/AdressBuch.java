package de.js.adressbuch;

import java.util.ArrayList;

/**
 * repraesentiert ein Adressbuch.
 * 
 * @author Jonas
 * 
 */
public class AdressBuch {

	private ArrayList<AdressEintrag> ab;

	/**
	 * erstellt ein leeres Adressbuch.
	 */
	public AdressBuch() {
		ab = new ArrayList<AdressEintrag>();
	}

	/**
	 * fuegt dem Adressbuch einen Eintrag hinzu.
	 * 
	 * @param e
	 */
	public void addEintrag(AdressEintrag e) {
		ab.add(e);
	}

	/**
	 * loescht einen Eintrag aus dem Adressbuch anhand des Objektes.
	 * 
	 * @param e
	 *            Objekt des Eintrags.
	 * @return <i>true</i> wenn der Eintrag sich in der Liste befunden hat.
	 */
	public boolean deleteEintrag(AdressEintrag e) {
		return ab.remove(e);
	}

	/**
	 * loescht einen Eintrag aus dem Adressbuch anhand der Stelle des Eintrags.
	 * 
	 * @param index
	 *            stelle des Eintrags
	 * @return <i>true</i> wenn der Eintrag sich in der Liste befunden hat.
	 */
	public boolean deleteEintrag(int index) {
		return ab.remove(index) != null;
	}

	/**
	 * ersetzt den Eintrag an der angegebenen stelle.
	 * 
	 * @param index
	 * @return
	 */
	public boolean updateEintrag(int index, AdressEintrag ae) {
		if (index < 0 && ae == null) return false;
		this.deleteEintrag(index);
		ab.add(index, ae);
		return true;
	}

	// /**
	// * @param args
	// */
	// public static void main(String[] args) {
	// AdressEintrag a = new AdressEintrag("lkj", "lkj", Anrede.getString(Anrede.DR));
	// System.out.println(a.getAnrede());
	// }

	/**
	 * 
	 * @return
	 */
	public ArrayList<AdressEintrag> getAb() {
		return ab;
	}
}
