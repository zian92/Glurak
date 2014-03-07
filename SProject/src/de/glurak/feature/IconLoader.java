package de.glurak.feature;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import de.glurak.Query;

public class IconLoader  {
	private ImageIcon icon;

	public IconLoader(int width, int height, String fullFilename){
		BufferedImage BGImage = null;
		try {
			BGImage = ImageIO.read(new File(fullFilename));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Image img =  BGImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		icon = new ImageIcon(img);
	}

	public ImageIcon getIcon() {
		return icon;
	}
	
}
