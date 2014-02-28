package de.glurak.feature;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import de.glurak.frontend.login.PromotionPanel;


/**
 * Creates The PromotionPanel
 * Manages the slidingbehaviour of the PromotionPanels
 * 
 * @author MxB
 *
 */
public class SliderPanelController implements ActionListener{

	private List<JLabel> imageLabelList = new ArrayList<JLabel>();
	private PromotionPanel promPan;
	private Dimension slidePaneDim = new Dimension(240, 240);
	private int picDim = slidePaneDim.width;
	
	public SliderPanelController(){
		promPan = new PromotionPanel();
		promPan.bt_start.addActionListener(this);
		//promPan.bt_add.addActionListener(this);
		
		loadArrayList();
		fillSliderPanel();
	}
	
	public JComponent getView(){
		return promPan;
	}
	
	public void fillSliderPanel(){
		
		for (int sliderPos = 0 ; sliderPos < promPan.sliderCount; sliderPos++){
			promPan.getSLiderAtPos(sliderPos).setPreferredSize(slidePaneDim);
			
			JLayeredPane layered_pan = new JLayeredPane();
			
			layered_pan.setPreferredSize(slidePaneDim);
			JButton bt_like = new JButton("L");
			JButton bt_hate = new JButton("H");
			bt_like.setBounds(180,240,50,50);
			bt_hate.setBounds(240,240,50,50);
			
			// Add -1 for adding the last 4 pictures later with the addContentTo function
			for (int i = 0; i < (imageLabelList.size()/promPan.sliderCount ); i++){
				promPan.getSLiderAtPos(sliderPos).addSliderComponent(imageLabelList.get( sliderPos + (i*promPan.sliderCount)));
			}
		promPan.getSLiderAtPos(sliderPos).refresh();
		}
	}
	
	
	public void addContentTo(int sliderPos, String filename){
		
		//test if filename == ""
		try {
			
			BufferedImage BGImage = ImageIO.read(new File( filename));
			Image img =  BGImage.getScaledInstance(240, 240, Image.SCALE_SMOOTH);
			JLabel picLabel = new JLabel(new ImageIcon(img));
			picLabel.setPreferredSize(slidePaneDim);
			imageLabelList.add(picLabel);				
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		promPan.getSLiderAtPos(sliderPos).addSliderComponent(imageLabelList.get(imageLabelList.size()-1));
		promPan.getSLiderAtPos(sliderPos).refresh();

	}
	
	private JLayeredPane ComponentWithButtons(JLabel l_pic){
		JLayeredPane layered_pan = new JLayeredPane();
		layered_pan.setPreferredSize(slidePaneDim);
		JButton bt_like = new JButton("L");
		JButton bt_hate = new JButton("H");
		bt_like.setBounds(180,240,50,50);
		bt_hate.setBounds(240,240,50,50);
		
		l_pic.setBounds(0 , 0, slidePaneDim.width ,slidePaneDim.height);
		
		layered_pan.add(l_pic, JLayeredPane.DEFAULT_LAYER, 0);
		layered_pan.add(bt_like, JLayeredPane.PALETTE_LAYER, 0);
		layered_pan.add(bt_hate, JLayeredPane.PALETTE_LAYER, 0);
		
		return layered_pan;
	}
	
	
	private void loadArrayList(){
		String filename = new String("pic");
		
		try {
			for (int j = 0; j < 4; j++){	
				for (int i = 1; i< 5; i++){
					BufferedImage BGImage = ImageIO.read(new File(filename + i + j + ".jpg"));
					Image img =  BGImage.getScaledInstance(240, 240, Image.SCALE_SMOOTH);
					JLabel picLabel = new JLabel(new ImageIcon(img));
					picLabel.setPreferredSize(slidePaneDim);
					imageLabelList.add(picLabel);
					//System.out.println(filename + i + j + ".jpg");
				}
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void actionPerformed(ActionEvent e) {
		// TODO distinguish the SOurce of the Event and handle it
				if (e.getSource() == promPan.bt_start){
					// Start behaviour
					
					java.util.TimerTask action = new java.util.TimerTask() {
						
						@Override
						public void run() {
					
							if ((Math.random()*100) < 30){
								promPan.getSLiderAtPos(0).next();
								promPan.getSLiderAtPos(1).next();
								promPan.getSLiderAtPos(2).next();
								promPan.getSLiderAtPos(3).next();
							//	promPan.getSLiderAtPos(4).next();
							//	promPan.getSLiderAtPos(5).next();
						
							}else{
								promPan.getSLiderAtPos(0).previous();
								promPan.getSLiderAtPos(1).previous();
								promPan.getSLiderAtPos(2).previous();
								promPan.getSLiderAtPos(3).previous();
							//	promPan.getSLiderAtPos(4).previous();
							//	promPan.getSLiderAtPos(5).previous();
							}
							
						}
					};
					
					java.util.Timer ankurbler = new java.util.Timer();
					ankurbler.schedule(action,  1000, 5000);
					
					/*
					 * TESTING
					 
					promview.getSLiderAtPos(0).next();
					promview.getSLiderAtPos(1).next();
					promview.getSLiderAtPos(2).next();
					promview.getSLiderAtPos(3).next();
					*/
					
				}else{
					/*
					 * TESING
					
					promview.getSLiderAtPos(0).previous();
					promview.getSLiderAtPos(1).previous();
					promview.getSLiderAtPos(2).previous();
					promview.getSLiderAtPos(3).previous();
					 */
				
					 addContentTo(0,"mj.jpg");
					 addContentTo(1,"abba.jpg");
					 addContentTo(2,"o1.jpg");
					 addContentTo(3,"o2.jpg");
					 
					
				}
				
		
	}

}
