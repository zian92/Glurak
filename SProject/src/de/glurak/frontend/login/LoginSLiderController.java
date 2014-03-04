package de.glurak.frontend.login;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;

import de.glurak.Query;

public class LoginSLiderController{
	
	private List<JLabel> imageLabelList = new ArrayList<JLabel>();
	private LoginPromoPanel promoPanel;
	private Dimension slidePaneDim = new Dimension(240, 240);
	private int loginSLiderSides = 3;
	public LoginSLiderController(){
		promoPanel = new LoginPromoPanel(new Dimension(480,500), slidePaneDim);
		initPromPanel();

	}
	
	public JComponent getView(){ return promoPanel; }
	
	private void initPromPanel(){
		int sMax = promoPanel.getSliderCount();
		initImageLabelList();
		// fill the sliders
		int q = (imageLabelList.size()/sMax);
		for (int pos = 0; pos < sMax; pos++){
				for (int j = 0; j < q; j++){
				promoPanel.getSLiderAtPos(pos).addSliderComponent(imageLabelList.get(pos+(j*sMax)));
			}
		} 
	}
	
	public boolean slideAble(){
		boolean result = false;
		for (int i= 0; i< promoPanel.getSliderCount(); i++){
			if (promoPanel.getSLiderAtPos(i).getItemCount() > 0){
				result = true;
			}
		}
		return result;
	}
	
	public void slide(){
		if( (Math.random() * 100) < 50) {
			for (int j = 0; j < promoPanel.getSliderCount(); j++){
				promoPanel.getSLiderAtPos(j).next();
			}
		}else{
			for (int j = 0; j < promoPanel.getSliderCount(); j++){
				promoPanel.getSLiderAtPos(j).previous();
			}
		}
	}
	
	private void initImageLabelList(){
		String filename = new String("pic");
		
		try {
			for (int sliderNr = 0 ; sliderNr < (promoPanel.getSliderCount()*loginSLiderSides); sliderNr++){
				BufferedImage labelImage = ImageIO.read(new File(Query.FOLDER_PICTURE_SLIDER + filename + sliderNr + ".jpg"));
				Image img =  labelImage.getScaledInstance(slidePaneDim.width,slidePaneDim.height, Image.SCALE_SMOOTH);
				JLabel picLabel = new JLabel(new ImageIcon(img));
				picLabel.setPreferredSize(slidePaneDim);
				imageLabelList.add(picLabel);
			}
		}catch (IOException e){
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
