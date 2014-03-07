/**
 * 
 */
package de.glurak.feature;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import de.glurak.Query;

/**
 * @author Jonas
 * 
 */
public class FontLoader {
    protected GraphicsEnvironment genv;

    /**
     * 
     */
    public FontLoader() {
        genv = GraphicsEnvironment.getLocalGraphicsEnvironment();
    }

    public Font loadFontFromFile(String splashscreenFont) {
        Font font = null;
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, new FileInputStream(Query.SPLASHSCREEN_FONT));// .createFont(Font.TRUETYPE_FONT, getClass().getResource(Query.SPLASHSCREEN_FONT).openStream());
        } catch (FontFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        genv.registerFont(font);
        return font;
    }
}
