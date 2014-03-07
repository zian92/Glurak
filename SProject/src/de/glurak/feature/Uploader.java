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
import de.glurak.frontend.SessionThing;

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
        this.createFolders(Query.FOLDERS);
    }

    private void createFolders(String[] folders) {
        for (String s : folders) {
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
    public void saveMusic(Medium[] medien, String albumName) {
        File[] files = this.getFileArrayFromMedium(medien);
        for (int i = 0; i < files.length; i++) {
            String artistPath = Query.FOLDER_MUSIC + medien[i].getOwner().getUsername() + "/" + albumName + "/";
            medien[i].setFileName(this.saveFile(new File(medien[i].getFileName()), artistPath).getPath());
        }
    }

    /**
     * speichert eine datei
     * 
     * @param file
     * @param artistPath
     * @return
     */
    private File saveFile(File file, String artistPath) {
        File path = null;
        this.createFolders(new String[] { artistPath, });
        boolean b = true;
        int i = 0;
        while (b) {
            path = new File(artistPath + i + file.getName());
            if (!path.exists()) {
                try {
                    Files.copy(file.toPath(), path.toPath(), StandardCopyOption.REPLACE_EXISTING);
                    b = false;
                } catch (IOException e) {
                    b = true;
                }
            }
            i++;
        }
        return path;
    }

    /**
     * Speichert die uebergebene Bilddatei im Profilordner.
     * 
     * @param pic
     * @return
     * @throws IOException
     */
    public void saveProfilePicture(File pic) throws IOException {
        String path = Query.FOLDER_PICTURE_PROFILE + SessionThing.getInstance().getSessionUser().getUsername();
        this.createFolders(new String[] { path, });
        File newPath = new File(path + "/profile" + (pic.getName().substring(pic.getName().lastIndexOf("."))));
        this.savePic(pic, newPath.getPath());
        SessionThing.getInstance().getSessionUser().getProfile().setPictureFileName(newPath.getPath());
    }

    /**
     * Speichert das uebergebene Bild im Albumordner.
     * 
     * @param pic
     * @return
     * @throws IOException
     */
    public void saveAlbumCover(File picture) throws IOException {
        this.savePic(picture, Query.FOLDER_PICTURE_COVER);
    }

    /**
     * Speichert das uebergebene Bild im Sliderordner
     * 
     * @param pic
     * @return
     * @throws IOException
     */
    public void saveSlider(File picture) throws IOException {
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
    private void savePic(File pic, String picPath) throws IOException {
        System.out.println("pic: " + pic.getPath());
        System.out.println(pic.exists());
        System.out.println("f: " + picPath);

        Files.copy(Paths.get(pic.getPath()), Paths.get(picPath), StandardCopyOption.REPLACE_EXISTING);
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
        int result = chooser.showOpenDialog(comp);
        if (result == JFileChooser.APPROVE_OPTION) {
            files = chooser.getSelectedFiles();
            if (files.length == 0) {
                files = new File[] { chooser.getSelectedFile(), };
            }
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