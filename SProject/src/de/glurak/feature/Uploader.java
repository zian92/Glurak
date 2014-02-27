package de.glurak.feature;

import java.awt.Component;
import java.io.File;
import java.io.FileNotFoundException;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import de.glurak.Query;

/**
 * @author Jonas
 * 
 */
public class Uploader {

	public Uploader() {

	}

	private boolean[] saveFiles(boolean[] isAccepted, File[] files) {
		if (this.checkBooleans(isAccepted) == false) return isAccepted;

		return isAccepted;
	}

	private boolean checkBooleans(boolean[] isAccepted) {
		for (boolean b : isAccepted)
			if (b == false) return false;
		return true;
	}

	public boolean[] uploadSinglePicture(Component comp) throws FileNotFoundException {
		return this.uploadFiles(comp, Query.SUPPORTED_PICTURE_TYPES);
	}

	private boolean[] uploadFiles(Component comp, String[] fileExtensions) throws FileNotFoundException {
		JFileChooser chooser = new JFileChooser(System.getProperty("user.home"));
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		boolean[] isAccepted = null;
		int result = chooser.showSaveDialog(comp);
		if (result == JFileChooser.APPROVE_OPTION) {
			File files = chooser.getSelectedFile();
			isAccepted = this.checkFileTypes(new File[] { files, }, fileExtensions);
		} else
			if (result == JFileChooser.CANCEL_OPTION) {
				throw new FileNotFoundException();
			}
		return isAccepted;
	}

	private boolean[] checkFileTypes(File[] files, String[] fileExtensions) {
		FileNameExtensionFilter fileFilter = new FileNameExtensionFilter(null, fileExtensions);
		boolean[] isAccepted = new boolean[files.length];
		for (int i = 0; i < files.length; i++) {
			if (fileFilter.accept(files[i])) {
				isAccepted[i] = true;
			} else {
				isAccepted[i] = false;
			}
		}
		return isAccepted;
	}
}
