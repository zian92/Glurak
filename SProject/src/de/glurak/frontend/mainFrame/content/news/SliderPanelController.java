package de.glurak.frontend.mainFrame.content.news;

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
import javax.swing.JComponent;
import javax.swing.JLabel;

import de.glurak.Query;
import de.glurak.frontend.mainFrame.ContentController;


/**
 * Creates The PromotionPanel with a list of items
 * which can be scrolled throughout the panels components.
 * Manages the slidingbehaviour of the PromotionPanels
 * 
 * @author MxB
 *
 */
public class SliderPanelController  implements ActionListener,ContentController{

	private List<JLabel> imageLabelList = new ArrayList<JLabel>();
	private PromotionPanel promPan;
	private Dimension slidePaneDim = new Dimension(265, 180);
	//private int picDim = slidePaneDim.width;

	/**
	 * Constructor
	 * creates the PromotionPanel, attaches this as Actionlistener
	 * and initialize the array of items, that will be shown by the promotionpanel.
	 * @post Panel with sliderpanels is created, initialized and ready 
	 * 		 to be filled with further elements.
	 */
	public SliderPanelController(){
		promPan = new PromotionPanel(new Dimension(800,545), slidePaneDim);

		promPan.bt_start.addActionListener(this);
		promPan.bt_add.addActionListener(this);
		
		initPromPanel();
		//loadArrayList();
		//fillSliderPanel();
	}
	
	public JComponent getView(){ return promPan; }
		
	/**
	 * Initialize the item array with the first, initial hardcorded elements.
	 * Adds the first items to the Promotionpanel components 
	 */
	private void initPromPanel(){
		
		initImageLabelList();
		// fill the sliders
		for (int pos = 0 ; pos < promPan.getSliderCount(); pos++){
			/* promPan.getSLiderAtPos(sliderPos).setPreferredSize(slidePaneDim);  Allready done in the constructor */
			// the initial panels have no abilities
			
			promPan.getSLiderAtPos(pos).addSliderComponent(imageLabelList.get(pos));
		}
	}
	/*
	public void fillSliderPanel(){
		
		for (int sliderPos = 0 ; sliderPos < promPan.getSliderCount(); sliderPos++){
						
			JLayeredPane layered_pan = new JLayeredPane();
			
			layered_pan.setPreferredSize(slidePaneDim);
			JButton bt_like = new JButton("L");
			JButton bt_hate = new JButton("H");
			bt_like.setBounds(180,240,50,50);
			bt_hate.setBounds(240,240,50,50);
			
			// Add -1 for adding the last 4 pictures later with the addContentTo function
			for (int i = 1; i < (imageLabelList.size()/promPan.getSliderCount() ); i++){
				promPan.getSLiderAtPos(sliderPos).addSliderComponent(imageLabelList.get( sliderPos + (i*promPan.getSliderCount())));
			}
		promPan.getSLiderAtPos(sliderPos).refresh();
		}
	}
	
	*/
	
	public void addContentTo(int sliderPos, String filename){
		
		//test if filename == ""
		try {
			
			BufferedImage BGImage = ImageIO.read(new File(Query.FOLDER_PICTURE_SLIDER + filename));
			Image img =  BGImage.getScaledInstance(slidePaneDim.width, slidePaneDim.height, Image.SCALE_SMOOTH);
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
	
	/*
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
	*/
	/**
	 * Loads images and attacht them to picturelabels.
	 * Stores that picturelabels in the item-array.
	 * @post A Number of illustrated picturelabels is stored in the item-array.
	 * 		 The number depends on the amount of sliders on the PromotionPanel.
	 */
	private void initImageLabelList(){
		String filename = new String("pic");
		
		try {
			for (int sliderNr = 0 ; sliderNr < promPan.getSliderCount(); sliderNr++){
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
	/*	
	private void loadArrayList(){
		String filename = new String("pic");
		
		try {
			for (int j = 1; j < 4; j++){	
				for (int i = 1; i< 5; i++){
					BufferedImage BGImage = ImageIO.read(new File(Query.FOLDER_PICTURE_SLIDER + filename  + i + j + ".jpg"));
					Image img =  BGImage.getScaledInstance(picDim,picDim, Image.SCALE_SMOOTH);
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
	
	*/
	public void actionPerformed(ActionEvent e) {
		// TODO distinguish the SOurce of the Event and handle it
		if (e.getSource() == promPan.bt_start){
			// Start behaviour
					java.util.TimerTask action = new java.util.TimerTask() {
						@Override
						public void run() {
							if ((Math.random()*100) < 50){
								for (int j = 0; j < promPan.getSliderCount(); j++){
									if (promPan.getSLiderAtPos(j).getItemCount() > 0){
										promPan.getSLiderAtPos(j).next();
									}
								}
							}else{
								for (int j = 0; j < promPan.getSliderCount(); j++){
									if (promPan.getSLiderAtPos(j).getItemCount() > 0){
										promPan.getSLiderAtPos(j).previous();
									}
								}
							}
						}
			
					};
				java.util.Timer ankurbler = new java.util.Timer();
				ankurbler.schedule(action,  1000, 5000);	
		}else{
			 addContentTo(5,"pic17.jpg");
			 addContentTo(13,"pic18.jpg");
			 addContentTo(11,"pic32.jpg");
		}
				
		
	}

}
