/**
 * 
 */
package de.glurak.feature;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import de.glurak.Query;

/**
 * @author Jonas
 * 
 */
public class FontLoader {
    protected GraphicsEnvironment genv;

    /**
     * erstellt eine FontLoader
     */
    public FontLoader() {
        genv = GraphicsEnvironment.getLocalGraphicsEnvironment();
    }

    /**
     * Erstellt eine Schrift.
     * 
     * @param splashscreenFont
     *            String zur Font datei.
     * @return neue Font
     * @throws IOException
     * @throws FontFormatException
     * @throws FileNotFoundException
     */
    public Font loadFontFromFile(String splashscreenFont) throws FileNotFoundException, FontFormatException, IOException {
        Font font = null;
        font = Font.createFont(Font.TRUETYPE_FONT, new FileInputStream(Query.SPLASHSCREEN_FONT));// .createFont(Font.TRUETYPE_FONT, getClass().getResource(Query.SPLASHSCREEN_FONT).openStream());
        genv.registerFont(font);
        return font;
    }
}
