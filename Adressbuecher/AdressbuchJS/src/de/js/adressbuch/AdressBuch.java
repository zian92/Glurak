package de.js.adressbuch;

public class AdressBuch {

	public AdressBuch() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		AdressEintrag a = new AdressEintrag("lkj", "lkj", Anrede.getString(Anrede.DR));
		System.out.println(a.getAnrede());
	}

}
