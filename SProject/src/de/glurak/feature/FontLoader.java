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
        Font font = Font.createFont(Font.TRUETYPE_FONT, new FileInputStream(Query.SPLASHSCREEN_FONT));
        genv.registerFont(font);
        return font;
    }
}
