package de.glurak.feature;

import java.awt.Component;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import de.glurak.Query;
import de.glurak.data.Medium;

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
    public void saveMusic(Medium[] medien) throws IOException {
        File[] files = this.getFileArrayFromMedium(medien);
        for (int i = 0; i < files.length; i++) {
            File path = new File(Query.FOLDER_MUSIC + files[i].getName());
            Files.copy(files[i].toPath(), path.toPath(), StandardCopyOption.REPLACE_EXISTING);
            medien[i].setFileName(path.toPath().toString());
        }
    }

    /**
     * Speichert die uebergebene Bilddatei im Profilordner.
     * 
     * @param pic
     * @return
     * @throws IOException
     */
    public void saveProfilePicture(Medium pic) throws IOException {
        this.savePic(pic, Query.FOLDER_PICTURE_PROFILE);
    }

    /**
     * Speichert das uebergebene Bild im Albumordner.
     * 
     * @param pic
     * @return
     * @throws IOException
     */
    public void saveAlbumCover(Medium picture) throws IOException {
        this.savePic(picture, Query.FOLDER_PICTURE_COVER);
    }

    /**
     * Speichert das uebergebene Bild im Sliderordner
     * 
     * @param pic
     * @return
     * @throws IOException
     */
    public void saveSlider(Medium picture) throws IOException {
        this.savePic(picture, Query.FOLDER_PICTURE_SLIDER);
    }

    /**
     * Interne Funktion zum Bilder speichern.
     * 
     * @param pic
     * @param picPath
     * @return
     * @throws IOException
     */
    private void savePic(Medium pic, String picPath) throws IOException {
        Files.copy(this.getFileFromMedium(pic).toPath(), Paths.get(picPath), StandardCopyOption.REPLACE_EXISTING);
        pic.setFileName(picPath);
    }

    /**
     * Oeffnet einen Dialog zum auswaehlen eines Bildes. Falls mehrere Bilder ausgewaehlt werden, wird das erste benutzt.
     * 
     * @param comp
     *            Den Besitzer des Fensters
     * @return
     */
    public File selectSinglePicture(Component comp) {
        return this.selectFiles(comp, Query.SUPPORTED_PICTURE_TYPES, JFileChooser.FILES_ONLY, false)[0];
    }

    /**
     * Oeffnet einen Dialog um Musikdateien hochzuladen.
     * 
     * @param comp
     *            Den Besitzer des Fensters
     * @return
     */
    public File[] selectMusic(Component comp) {
        return this.selectFiles(comp, Query.SUPPORTED_MUSIC_TYPES, JFileChooser.FILES_AND_DIRECTORIES, true);
    }

    /**
     * Oeffnet einen Dialog um einzelne Musikdateien hochzuladen.
     * 
     * @param comp
     *            Den Besitzer des Fensters
     * @return
     */
    public File selectSingleMusic(Component comp) {
        return this.selectFiles(comp, Query.SUPPORTED_MUSIC_TYPES, JFileChooser.FILES_ONLY, false)[0];
    }

    /**
     * Interne Funktion zum auswaehlen von Dateien.
     * 
     * @param comp
     * @param fileExtensions
     * @param selectionMode
     * @return
     */
    private File[] selectFiles(Component comp, String[] fileExtensions, int selectionMode, boolean multiSelection) {
        JFileChooser chooser = new JFileChooser(System.getProperty("user.home"));
        chooser.setMultiSelectionEnabled(multiSelection);
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

    /**
     * Gibt ein FileArray zurueck.
     * 
     * @param med
     * @return
     */
    private File[] getFileArrayFromMedium(Medium[] med) {
        File[] temp = new File[med.length];
        for (int i = 0; i < med.length; i++) {
            temp[i] = new File(med[i].getFileName());
        }
        return temp;
    }

    /**
     * gibt eine neue File von Medium zurueck
     * 
     * @param med
     * @return
     */
    private File getFileFromMedium(Medium med) {
        return new File(med.getFileName());
    }

}