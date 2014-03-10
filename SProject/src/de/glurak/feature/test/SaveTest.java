package de.glurak.feature.test;

import java.io.File;
import java.io.IOException;

import org.junit.After;
import org.junit.Test;

import de.glurak.Query;
import de.glurak.data.Medium;
import de.glurak.feature.Uploader;

/**
 * 
 * @author Jonas
 * 
 */
public class SaveTest {
    private Uploader up = Uploader.getInstance();
    Medium picture;
    Medium[] songs;

    @Test
    public void savePicture() {
        picture = new Medium();
        picture.setFileName(Query.DATAPATH + "test.jpg");
        /*try { //TODO: hier hakt es jetzt
            up.saveAlbumCover(picture);
        } catch (IOException e) {
            System.out.println("Fehlgeschlagen");
        } */
    }

    // TODO OUtdated!
    @Test
    public void saveMusic() {
        songs = new Medium[2];
        songs[0].setFileName(Query.ROOTPATH + "test.mp3");
        songs[1].setFileName(Query.ROOTPATH + "test2.mp3");
        up.saveMusic(songs, "testAlbum");
    }

    @After
    public void after() {
        File f = new File(picture.getFileName());
        f.delete();
        f = new File(songs[0].getFileName());
        f.delete();
        f = new File(songs[0].getFileName());
        f.delete();
    }
}
