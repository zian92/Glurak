/**
 * 
 */
package de.js.adressbuch;

/**
 * @author Jonas
 * 
 */
public enum Anrede {
	OHNE(0), HERR(1), FRAU(2), DR(4), PROF(3);

	private int a;

	Anrede(int anredeNr) {
		a = anredeNr;
	}

	/**
	 * Gibt Anrede Text zurueck.<b> Inklusive Spacer</b> 
	 * @param dr2
	 * @return Anredetext
	 */
	public static String getString(Anrede dr2) {
		String str = "";
		switch (dr2) {
			case OHNE:
				str = " ";
			case HERR:
				str = " Herr ";
				break;
			case FRAU:
				str = " Frau ";
				break;
			case DR:
				str = " Dr. ";
				break;
			case PROF:
				str = " Prof. ";
				break;
		}
		return str;
	}

	public int getAnredeNr() {
		return a;
	}
}