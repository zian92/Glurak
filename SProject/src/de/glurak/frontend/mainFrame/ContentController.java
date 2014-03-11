package de.glurak.frontend.mainFrame;

import javax.swing.JComponent;

/**
 * Kontroller fuer die Contents
 *
 */
public interface ContentController {

	/**
	 * @return Gibt die View zurueck
	 */
	public JComponent getView();
	
	/**
	 * Laedt die View neu
	 */
	public void reload();
}
