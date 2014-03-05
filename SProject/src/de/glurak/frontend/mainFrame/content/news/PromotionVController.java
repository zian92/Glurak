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
import de.glurak.data.Album;
import de.glurak.data.Medium;
import de.glurak.data.User.ListenerProfile;
import de.glurak.data.User.User;
import de.glurak.frontend.mainFrame.ContentController;


/**
 * Erzeugt das PromotionPanel, welches mit Slidern
 * versehen wird. Kümmert sich um das Slidingverhalten der einzelnen Slider.
 * @author MxB
 *
 */
public class PromotionVController  implements ContentController{

	private List<JLabel> imageLabelList = new ArrayList<JLabel>();
	
	private List<NewsEntry>newsList = new ArrayList<NewsEntry>();
	
	private PromotionView promPan;
	private Dimension slidePaneDim = new Dimension(200, 180);
	private Dimension promPanelDim = new Dimension(810, 560);
	
	//private int picDim = slidePaneDim.width;

	/**
	 * Konstructor
	 * Erzeugt das PromoPanel und initialisiert deren Elemente.
	 * @post Panel mit Slidern ist erzeugt und initialisiert
	 */
	public PromotionVController(){
		promPan = new PromotionView(promPanelDim, slidePaneDim);

		//promPan.bt_start.addActionListener(this);
		//promPan.bt_add.addActionListener(this);
		
		initNewsEntries();
		//initPromPanel();
		//loadArrayList();
		//fillSliderPanel();
	}
	
	public JComponent getView(){ return promPan; }
		
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

	
	/**
	 * Initialisiert die PanelSliderElemente mit Inhalten aus
	 * der newsList des eingeloggten Benutzers.  
	 */
	private void initNewsEntries(){
		
		// Get Items from newsList of current User
		Album a1 = new Album();
		Album a2 = new Album();
		Medium m1 = new Medium(13, "Song 2", null, new User());
		
		a1.setName("This is It");
		a2.setName("Album dummy");
		NewsEntry n1 = new NewsEntry(a1);
		NewsEntry n2 = new NewsEntry(a2);
		
		User u1 = new User();
		u1.setUsername("TestUser m");
		ListenerProfile pu1 = new ListenerProfile();
		pu1.setFirstname(u1.getUsername());
		pu1.setFemale(false);
		u1.setProfile(pu1);
		
		User u2 = new User();
		u2.setUsername("TestUser f");
		ListenerProfile pu2 = new ListenerProfile();
		pu2.setFirstname(u2.getUsername());
		pu2.setFemale(true);
		u2.setProfile(pu2);
		
		newsList.add(n1);
		newsList.add(n2);
		newsList.add(new NewsEntry(a1));
		newsList.add(new NewsEntry(a1));
		newsList.add(new NewsEntry(u1));
		newsList.add(new NewsEntry(m1));
		newsList.add(new NewsEntry(a2));
		newsList.add(new NewsEntry(m1));
		newsList.add(new NewsEntry(a2));
		newsList.add(new NewsEntry(u2));
		newsList.add(new NewsEntry(a1));
		newsList.add(new NewsEntry(u1));
		newsList.add(new NewsEntry(m1));
		newsList.add(new NewsEntry(a1));
		newsList.add(new NewsEntry(a2));
		newsList.add(new NewsEntry(a1));
		newsList.add(new NewsEntry(m1));
		
		newsList.add(new NewsEntry(u2));
		newsList.add(new NewsEntry(u2));

		// Fill every SLider with Content from NewsList
		int sMax = promPan.getSliderCount();
		int q = newsList.size()/sMax;
		if (sMax*q<newsList.size()){
			q=q+1;
		}
		for (int pos = 0; pos < sMax; pos++){
				for (int j = 0; j < q; j++){
					if (newsList.size() <= (pos+(j*sMax))) break;
					promPan.getSLiderAtPos(pos).addSliderComponent(newsList.get(pos+(j*sMax)).getLayeredPane());
			}
		} 
		startTimer();
	}

	
	/**
	 * TestTimerTask for testing the sliding behaviour
	 * Will be reworked in the final version
	 */
	private void startTimer(){
		java.util.TimerTask action = new java.util.TimerTask() {
			@Override
			public void run() {
				int j = 0 + (int)(Math.random()*promPan.getSliderCount()); 
				int i = 0 + (int)(Math.random()*promPan.getSliderCount()); 
				if (promPan.getSLiderAtPos(i).getItemCount() > 0){
					promPan.getSLiderAtPos(i).next();
				}
				if (promPan.getSLiderAtPos(j).getItemCount() > 0){
					promPan.getSLiderAtPos(j).next();
				}
			}
		};
		java.util.Timer ankurbler = new java.util.Timer();
		ankurbler.schedule(action, 1000, 2000);
	}

}
