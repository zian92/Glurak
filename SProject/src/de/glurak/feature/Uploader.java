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
 * @author Jonas
 * 
 */
public class Uploader {
	private static Uploader instance = null;

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
	 * Fuehrt alle noetigen operationen aus. - Legt vorhandene Ordner an.
	 */
	private Uploader() {
		// lege nicht vorhandene, aber benoetigte Ordner an
		for (String s : Query.FOLDERS) {
			File dir = new File(s);
			if (!dir.exists()) dir.mkdirs();
		}
	}

	public boolean saveMusic(File[] files) {
		for (File f : files) {
			try {

				Files.copy(f.toPath(), new File(Query.FOLDER_MUSIC + f.getName()).toPath(), StandardCopyOption.REPLACE_EXISTING);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return true;
	}

	public boolean saveProfilePicture(File pic) {
		return this.savePic(pic, Query.FOLDER_PICTURE_PROFILE);
	}

	public boolean saveAlbumCover(File pic) {
		return this.savePic(pic, Query.FOLDER_PICTURE_COVER);
	}

	public boolean saveSlider(File pic) {
		return this.savePic(pic, Query.FOLDER_PICTURE_SLIDER);
	}

	private boolean savePic(File pic, String picPath) {
		try {
			Files.copy(pic.toPath(), new File(picPath + pic.getName()).toPath(), StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}

	public File selectSinglePicture(Component comp) {
		return this.selectFiles(comp, Query.SUPPORTED_PICTURE_TYPES, JFileChooser.FILES_ONLY)[0];
	}

	public File[] selectMusic(Component comp) {
		return this.selectFiles(comp, Query.SUPPORTED_MUSIC_TYPES, JFileChooser.FILES_AND_DIRECTORIES);
	}

	private File[] selectFiles(Component comp, String[] fileExtensions, int selectionMode) {
		JFileChooser chooser = new JFileChooser(System.getProperty("user.home"));
		chooser.setFileSelectionMode(selectionMode);
		chooser.setMultiSelectionEnabled(true);
		chooser.setFileFilter(new FileNameExtensionFilter(this.makeFileExtensionString(fileExtensions), fileExtensions));
		File[] files = null;
		int result = chooser.showSaveDialog(comp);
		if (result == JFileChooser.APPROVE_OPTION) {
			files = chooser.getSelectedFiles();
		} else
			if (result == JFileChooser.CANCEL_OPTION) {
				return null;
			}
		return files;
	}

	private String makeFileExtensionString(String[] fileExtensions) {
		String s = "." + fileExtensions[0];
		for (int i = 1; i < fileExtensions.length; i++) {
			s = s + ", ." + fileExtensions[i];
		}
		return s;
	}
	/*
	 * private File[] selectAllowedFiletypes(File[] files, String[] fileExtensions) { FileNameExtensionFilter fileFilter = new FileNameExtensionFilter(null, fileExtensions); ArrayList<File> acceptFiles = new ArrayList<File>(); for (int i = 0; i < files.length; i++) { if (fileFilter.accept(files[i])) { acceptFiles.add(files[i]); } } return (File[]) acceptFiles.toArray(); }
	 */
}
