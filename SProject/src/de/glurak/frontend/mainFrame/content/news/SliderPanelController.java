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
<<<<<<< HEAD:SProject/src/de/glurak/frontend/mainFrame/content/news/SliderPanelController.java
=======
import javax.swing.JLayeredPane;
>>>>>>> 9ea001f658c59487256b0b4638a10c9953dbce93:SProject/src/de/glurak/feature/SliderPanelController.java

import de.glurak.Query;
import de.glurak.frontend.mainFrame.ContentController;

/**
 * Creates The PromotionPanel with a list of items which can be scrolled throughout the panels components. Manages the slidingbehaviour of the PromotionPanels
 * 
 * @author MxB
 * 
 */
<<<<<<< HEAD:SProject/src/de/glurak/frontend/mainFrame/content/news/SliderPanelController.java
public class SliderPanelController  implements ActionListener,ContentController{
=======
public class SliderPanelController implements ActionListener {
>>>>>>> 9ea001f658c59487256b0b4638a10c9953dbce93:SProject/src/de/glurak/feature/SliderPanelController.java

	private List<JLabel> imageLabelList = new ArrayList<JLabel>();
	private PromotionPanel promPan;
	private Dimension slidePaneDim = new Dimension(265, 180);
	//private int picDim = slidePaneDim.width;

	/**
<<<<<<< HEAD:SProject/src/de/glurak/frontend/mainFrame/content/news/SliderPanelController.java
	 * Constructor
	 * creates the PromotionPanel, attaches this as Actionlistener
	 * and initialize the array of items, that will be shown by the promotionpanel.
	 * @post Panel with sliderpanels is created, initialized and ready 
	 * 		 to be filled with further elements.
	 */
	public SliderPanelController(){
		promPan = new PromotionPanel(new Dimension(800,545), slidePaneDim);

=======
	 * Constructor creates the PromotionPanel, attaches this as Actionlistener and initialize the array of items, that will be shown by the promotionpanel.
	 * 
	 * @post Panel with sliderpanels is created and ready to be filled with further elements.
	 */
	public SliderPanelController() {
		promPan = new PromotionPanel(4);
>>>>>>> 9ea001f658c59487256b0b4638a10c9953dbce93:SProject/src/de/glurak/feature/SliderPanelController.java
		promPan.bt_start.addActionListener(this);
		promPan.bt_add.addActionListener(this);

		initPromPanel();
		// loadArrayList();
		// fillSliderPanel();
	}

	public JComponent getView() {
		return promPan;
	}

	/**
	 * Initialize the item array with the first, initial hardcorded elements. Adds the first items to the Promotionpanel components
	 */
	private void initPromPanel() {

		initImageLabelList();
		// fill the sliders
<<<<<<< HEAD:SProject/src/de/glurak/frontend/mainFrame/content/news/SliderPanelController.java
		for (int pos = 0 ; pos < promPan.getSliderCount(); pos++){
			/* promPan.getSLiderAtPos(sliderPos).setPreferredSize(slidePaneDim);  Allready done in the constructor */
			// the initial panels have no abilities
			
			promPan.getSLiderAtPos(pos).addSliderComponent(imageLabelList.get(pos));
		}
	}
	/*
	public void fillSliderPanel(){
		
		for (int sliderPos = 0 ; sliderPos < promPan.getSliderCount(); sliderPos++){
						
=======
		for (int sliderPos = 0; sliderPos < promPan.getSliderCount(); sliderPos++) {
			promPan.getSLiderAtPos(sliderPos).setPreferredSize(slidePaneDim);
			// the initial panels have no abilities

			promPan.getSLiderAtPos(sliderPos).addSliderComponent(imageLabelList.get(sliderPos));
		}
	}

	public void fillSliderPanel() {

		for (int sliderPos = 0; sliderPos < promPan.getSliderCount(); sliderPos++) {
			promPan.getSLiderAtPos(sliderPos).setPreferredSize(slidePaneDim);

>>>>>>> 9ea001f658c59487256b0b4638a10c9953dbce93:SProject/src/de/glurak/feature/SliderPanelController.java
			JLayeredPane layered_pan = new JLayeredPane();

			layered_pan.setPreferredSize(slidePaneDim);
			JButton bt_like = new JButton("L");
			JButton bt_hate = new JButton("H");
			bt_like.setBounds(180, 240, 50, 50);
			bt_hate.setBounds(240, 240, 50, 50);

			// Add -1 for adding the last 4 pictures later with the addContentTo function
			for (int i = 1; i < (imageLabelList.size() / promPan.getSliderCount()); i++) {
				promPan.getSLiderAtPos(sliderPos).addSliderComponent(imageLabelList.get(sliderPos + (i * promPan.getSliderCount())));
			}
			promPan.getSLiderAtPos(sliderPos).refresh();
		}
	}
<<<<<<< HEAD:SProject/src/de/glurak/frontend/mainFrame/content/news/SliderPanelController.java
	
	*/
	
	public void addContentTo(int sliderPos, String filename){
		
		//test if filename == ""
		try {
			
			BufferedImage BGImage = ImageIO.read(new File(Query.FOLDER_PICTURE_SLIDER + filename));
			Image img =  BGImage.getScaledInstance(slidePaneDim.width, slidePaneDim.height, Image.SCALE_SMOOTH);
=======

	public void addContentTo(int sliderPos, String filename) {

		// test if filename == ""
		try {

			BufferedImage BGImage = ImageIO.read(new File(filename));
			Image img = BGImage.getScaledInstance(picDim, picDim, Image.SCALE_SMOOTH);
>>>>>>> 9ea001f658c59487256b0b4638a10c9953dbce93:SProject/src/de/glurak/feature/SliderPanelController.java
			JLabel picLabel = new JLabel(new ImageIcon(img));
			picLabel.setPreferredSize(slidePaneDim);
			imageLabelList.add(picLabel);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		promPan.getSLiderAtPos(sliderPos).addSliderComponent(imageLabelList.get(imageLabelList.size() - 1));
		promPan.getSLiderAtPos(sliderPos).refresh();

	}
<<<<<<< HEAD:SProject/src/de/glurak/frontend/mainFrame/content/news/SliderPanelController.java
	
	/*
	private JLayeredPane ComponentWithButtons(JLabel l_pic){
=======

	private JLayeredPane ComponentWithButtons(JLabel l_pic) {
>>>>>>> 9ea001f658c59487256b0b4638a10c9953dbce93:SProject/src/de/glurak/feature/SliderPanelController.java
		JLayeredPane layered_pan = new JLayeredPane();
		layered_pan.setPreferredSize(slidePaneDim);
		JButton bt_like = new JButton("L");
		JButton bt_hate = new JButton("H");
		bt_like.setBounds(180, 240, 50, 50);
		bt_hate.setBounds(240, 240, 50, 50);

		l_pic.setBounds(0, 0, slidePaneDim.width, slidePaneDim.height);

		layered_pan.add(l_pic, JLayeredPane.DEFAULT_LAYER, 0);
		layered_pan.add(bt_like, JLayeredPane.PALETTE_LAYER, 0);
		layered_pan.add(bt_hate, JLayeredPane.PALETTE_LAYER, 0);

		return layered_pan;
	}
	*/
	/**
	 * Loads images and attacht them to picturelabels. Stores that picturelabels in the item-array.
	 * 
	 * @post A Number of illustrated picturelabels is stored in the item-array. The number depends on the amount of sliders on the PromotionPanel.
	 */
	private void initImageLabelList() {
		String filename = new String("pic");

		try {
			for (int sliderNr = 0; sliderNr < promPan.getSliderCount(); sliderNr++) {
				BufferedImage labelImage = ImageIO.read(new File(Query.FOLDER_PICTURE_SLIDER + filename + sliderNr + ".jpg"));
<<<<<<< HEAD:SProject/src/de/glurak/frontend/mainFrame/content/news/SliderPanelController.java
				Image img =  labelImage.getScaledInstance(slidePaneDim.width,slidePaneDim.height, Image.SCALE_SMOOTH);
=======
				Image img = labelImage.getScaledInstance(picDim, picDim, Image.SCALE_SMOOTH);
>>>>>>> 9ea001f658c59487256b0b4638a10c9953dbce93:SProject/src/de/glurak/feature/SliderPanelController.java
				JLabel picLabel = new JLabel(new ImageIcon(img));
				picLabel.setPreferredSize(slidePaneDim);
				imageLabelList.add(picLabel);
				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
<<<<<<< HEAD:SProject/src/de/glurak/frontend/mainFrame/content/news/SliderPanelController.java
	/*	
	private void loadArrayList(){
=======

	private void loadArrayList() {
>>>>>>> 9ea001f658c59487256b0b4638a10c9953dbce93:SProject/src/de/glurak/feature/SliderPanelController.java
		String filename = new String("pic");

		try {
			for (int j = 1; j < 4; j++) {
				for (int i = 1; i < 5; i++) {
					BufferedImage BGImage = ImageIO.read(new File(Query.FOLDER_PICTURE_SLIDER + filename + i + j + ".jpg"));
					Image img = BGImage.getScaledInstance(picDim, picDim, Image.SCALE_SMOOTH);
					JLabel picLabel = new JLabel(new ImageIcon(img));
					picLabel.setPreferredSize(slidePaneDim);
					imageLabelList.add(picLabel);
					// System.out.println(filename + i + j + ".jpg");
				}
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
<<<<<<< HEAD:SProject/src/de/glurak/frontend/mainFrame/content/news/SliderPanelController.java
	
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
				
		
=======

	public void actionPerformed(ActionEvent e) {
		// TODO distinguish the SOurce of the Event and handle it
		if (e.getSource() == promPan.bt_start) {
			// Start behaviour

			java.util.TimerTask action = new java.util.TimerTask() {

				@Override
				public void run() {

					if ((Math.random() * 100) < 50) {
						for (int j = 0; j < promPan.getSliderCount(); j++) {
							if (promPan.getSLiderAtPos(j).getItemCount() > 0) {
								promPan.getSLiderAtPos(j).next();
							}
						}
					} else {
						for (int j = 0; j < promPan.getSliderCount(); j++) {
							if (promPan.getSLiderAtPos(j).getItemCount() > 0) {
								promPan.getSLiderAtPos(j).previous();
							}
						}
					}

				}
			};

			java.util.Timer ankurbler = new java.util.Timer();
			ankurbler.schedule(action, 1000, 5000);
		} else {
			addContentTo(0, Query.FOLDER_PICTURE_SLIDER + "mj.jpg");
		}

>>>>>>> 9ea001f658c59487256b0b4638a10c9953dbce93:SProject/src/de/glurak/feature/SliderPanelController.java
	}

}
