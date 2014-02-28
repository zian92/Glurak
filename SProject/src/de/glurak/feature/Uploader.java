package de.glurak.feature;

import java.awt.Component;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import de.glurak.Query;

/**
 * Uploader stellt Funktionen zum hochladen von Bild- und Musikdateien bereit. Die erlaubten Datentypen sind genauer in de.glurak.Qery spezifiziert.
 * 
 * @author Jonas
 */
public class Uploader {
	private static Uploader instance = null;

	/**
	 * Erstellt ein Object vom Typ Uploader. Dabei wird ueberprueft ob die benoetigte ordnerstruktur existiert und ggf. erstellt.
	 */
	private Uploader() {
		// lege nicht vorhandene, aber benoetigte Ordner an
		for (String s : Query.FOLDERS) {
			File dir = new File(s);
			if (!dir.exists()) {
				dir.mkdirs();
			}
		}
	}

	/**
	 * Singleton funktion zum erhalten des Objektes
	 * 
	 * @return
	 */
	public static Uploader getInstance() {
		if (instance == null) {
			instance = new Uploader();
		}
		return instance;
	}

	/**
	 * Speichert die uebergebenen Musikdateien in dem dafuer vorgesehenen Ordner.
	 * 
	 * @param files
	 * @return
	 * @throws IOException
	 */
	public boolean saveMusic(File[] files) throws IOException {
		for (File f : files) {
			Files.copy(f.toPath(), new File(Query.FOLDER_MUSIC + f.getName()).toPath(), StandardCopyOption.REPLACE_EXISTING);
		}
		return true;
	}

	/**
	 * Speichert die uebergebene Bilddatei im Profilordner.
	 * 
	 * @param pic
	 * @return
	 * @throws IOException
	 */
	public boolean saveProfilePicture(File pic) throws IOException {
		return this.savePic(pic, Query.FOLDER_PICTURE_PROFILE);
	}

	/**
	 * Speichert das uebergebene Bild im Albumordner.
	 * 
	 * @param pic
	 * @return
	 * @throws IOException
	 */
	public boolean saveAlbumCover(File pic) throws IOException {
		return this.savePic(pic, Query.FOLDER_PICTURE_COVER);
	}

	/**
	 * Speichert das uebergebene Bild im Sliderordner
	 * 
	 * @param pic
	 * @return
	 * @throws IOException
	 */
	public boolean saveSlider(File pic) throws IOException {
		return this.savePic(pic, Query.FOLDER_PICTURE_SLIDER);
	}

	/**
	 * Interne Funktion zum Bilder speichern.
	 * 
	 * @param pic
	 * @param picPath
	 * @return
	 * @throws IOException
	 */
	private boolean savePic(File pic, String picPath) throws IOException {
		Files.copy(pic.toPath(), new File(picPath + pic.getName()).toPath(), StandardCopyOption.REPLACE_EXISTING);
		return true;
	}

	/**
	 * Oeffnet einen Dialog zum auswaehlen eines Bildes. Falls mehrere Bilder ausgewaehlt werden, wird das erste benutzt.
	 * 
	 * @param comp
	 * @return
	 */
	public File selectSinglePicture(Component comp) {
		return this.selectFiles(comp, Query.SUPPORTED_PICTURE_TYPES, JFileChooser.FILES_ONLY)[0];
	}

	/**
	 * Oeffnet einen Dialog um Musikdateien hochzuladen.
	 * 
	 * @param comp
	 * @return
	 */
	public File[] selectMusic(Component comp) {
		return this.selectFiles(comp, Query.SUPPORTED_MUSIC_TYPES, JFileChooser.FILES_AND_DIRECTORIES);
	}

	/**
	 * Interne Funktion zum auswaehlen von Dateien.
	 * 
	 * @param comp
	 * @param fileExtensions
	 * @param selectionMode
	 * @return
	 */
	private File[] selectFiles(Component comp, String[] fileExtensions, int selectionMode) {
		JFileChooser chooser = new JFileChooser(System.getProperty("user.home"));
		chooser.setMultiSelectionEnabled(true);
		chooser.setFileSelectionMode(selectionMode);
		chooser.setFileFilter(new FileNameExtensionFilter(this.makeFileExtensionString(fileExtensions), fileExtensions));
		chooser.setFileFilter(new FileNameExtensionFilter(null, fileExtensions));
		File[] files = null;
		int result = chooser.showSaveDialog(comp);
		if (result == JFileChooser.APPROVE_OPTION) {
			files = chooser.getSelectedFiles();
		} else
			if (result == JFileChooser.CANCEL_OPTION) {
				return new File[0];
			}
		return files;
	}

	/**
	 * Erzeugt einen String, der alle dateiendungen aufzaehlt.
	 * 
	 * @param fileExtensions
	 * @return
	 */
	private String makeFileExtensionString(String[] fileExtensions) {
		String s = "." + fileExtensions[0];
		for (int i = 1; i < fileExtensions.length; i++) {
			s += ", ." + fileExtensions[i];
		}
		return s;
	}
}