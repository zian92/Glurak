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

	public static Uploader getInstance() {
		if (instance == null) {
			instance = new Uploader();
		}
		return instance;
	}

	private Uploader() {
		// TODO check if dirs exist
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

	public File uploadSinglePicture(Component comp) {
		return this.selectFiles(comp, Query.SUPPORTED_PICTURE_TYPES, JFileChooser.FILES_ONLY)[0];
	}

	public File[] selectMusic(Component comp) {
		return this.selectFiles(comp, Query.SUPPORTED_MUSIC_TYPES, JFileChooser.FILES_AND_DIRECTORIES);
	}

	private File[] selectFiles(Component comp, String[] fileExtensions, int selectionMode) {
		JFileChooser chooser = new JFileChooser(System.getProperty("user.home"));
		chooser.setFileSelectionMode(selectionMode);
		chooser.setFileFilter(new FileNameExtensionFilter(null, fileExtensions));
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
	/*
	 * private File[] selectAllowedFiletypes(File[] files, String[] fileExtensions) { FileNameExtensionFilter fileFilter = new FileNameExtensionFilter(null, fileExtensions); ArrayList<File> acceptFiles = new ArrayList<File>(); for (int i = 0; i < files.length; i++) { if (fileFilter.accept(files[i])) { acceptFiles.add(files[i]); } } return (File[]) acceptFiles.toArray(); }
	 */
}
