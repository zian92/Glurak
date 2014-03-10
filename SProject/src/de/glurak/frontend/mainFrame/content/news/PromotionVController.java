package de.glurak.frontend.mainFrame.content.news;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import org.hibernate.property.Getter;

import de.glurak.Query;
import de.glurak.data.Album;
import de.glurak.data.Medium;
import de.glurak.data.User.ListenerProfile;
import de.glurak.data.User.User;
import de.glurak.feature.IconLoader;
import de.glurak.frontend.SessionThing;
import de.glurak.frontend.mainFrame.ContentController;
import de.glurak.frontend.mainFrame.NextContent;
import de.glurak.frontend.mainFrame.content.playlist.PlaylistEditVController;
import de.glurak.frontend.mainFrame.content.profile.ProfileVController;


/**
 * Erzeugt das PromotionPanel, welches mit Slidern
 * versehen wird. Kümmert sich um das Slidingverhalten der einzelnen Slider.
 * @author MxB
 *
 */
public class PromotionVController extends Observable implements ContentController{

	private List<JLabel> imageLabelList = new ArrayList<JLabel>();
	
	private List<JComponent>newsList = new ArrayList<JComponent>();
	private ContentController nextContent;
	private PromotionView promPan;
	private Dimension slidePaneDim = new Dimension(200, 180);
	private Dimension promPanelDim = new Dimension(810, 560);
	
	//private int picDim = slidePaneDim.width;

	/**
	 * Konstructor
	 * Erzeugt das PromoPanel und initialisiert deren Elemente.
	 * @post Panel mit Slidern ist erzeugt und initialisiert
	 */
	public PromotionVController() {
		promPan = new PromotionView(promPanelDim, slidePaneDim);
		initNewsEntries();
	}
	
	public JComponent getView(){ return promPan; }
	/*	
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
	*/
	
	/**
	 * Initialisiert die PanelSliderElemente mit Inhalten aus
	 * der newsList des eingeloggten Benutzers.  
	 */
	private void initNewsEntries(){
		
		//TODO: Get Items from newsList of current User
		
		// Creating dummy-Objects for testing 
		Album a1 = new Album();
		Album a2 = new Album();
		Medium m1 = new Medium(13, "Song 2", null, null);
		
		a1.setName("This is It");
		a2.setName("Album dummy");
		
		
		
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
		
		//User testU = SessionThing.getInstance().getDatabase().getUserByUsername("LeTest");
		
		//newsList.add(buildEntryView(200, 180, new NewsEntry(testU)));
		/*
		newsList.add(buildEntryView(200, 180, new NewsEntry(a2)));
		newsList.add(buildEntryView(200, 180, new NewsEntry(u1)));
		newsList.add(buildEntryView(200, 180, new NewsEntry(m1)));
		newsList.add(buildEntryView(200, 180, new NewsEntry(a2)));
		newsList.add(buildEntryView(200, 180, new NewsEntry(m1)));
		newsList.add(buildEntryView(200, 180, new NewsEntry(a2)));
		newsList.add(buildEntryView(200, 180, new NewsEntry(u2)));
		newsList.add(buildEntryView(200, 180, new NewsEntry(a1)));
		newsList.add(buildEntryView(200, 180, new NewsEntry(u1)));
		newsList.add(buildEntryView(200, 180, new NewsEntry(m1)));
		newsList.add(buildEntryView(200, 180, new NewsEntry(a2)));
		newsList.add(buildEntryView(200, 180, new NewsEntry(m1)));
		newsList.add(buildEntryView(200, 180, new NewsEntry(a2)));
		newsList.add(buildEntryView(200, 180, new NewsEntry(u2)));
		newsList.add(buildEntryView(200, 180, new NewsEntry(u1)));
		newsList.add(buildEntryView(200, 180, new NewsEntry(m1)));
		newsList.add(buildEntryView(200, 180, new NewsEntry(a2)));
		newsList.add(buildEntryView(200, 180, new NewsEntry(m1)));
		newsList.add(buildEntryView(200, 180, new NewsEntry(a2)));
		newsList.add(buildEntryView(200, 180, new NewsEntry(u2)));
		newsList.add(buildEntryView(200, 180, new NewsEntry(u1)));
		*/
		// Fill every SLider with Content from NewsList
		int sMax = promPan.getSliderCount();
		int q = newsList.size()/sMax;
		if (sMax*q<newsList.size()){
			q=q+1;
		}
		for (int pos = 0; pos < sMax; pos++){
				for (int j = 0; j < q; j++){
					if (newsList.size() <= (pos+(j*sMax))) break;
					promPan.getSLiderAtPos(pos).addSliderComponent(newsList.get(pos+(j*sMax)));
			}
		} 
		startTimer();
	}
	
	/**
	 * Erzeugt einen Sichtbaren NewsEntry-Eintrag samt Interfacekommponenten
	 * 
	 * @param width Breite des Panels
	 * @param height Hoehe des Panels
	 * @param n	NewsEntry-Eintrag
	 * @return
	 */
	public JComponent buildEntryView(int width, int height, NewsEntry n){
		JLayeredPane pan_content = new JLayeredPane();
		JLabel lab_pic = new JLabel(new IconLoader(width, height, n.getPictureName()).getIcon());
		JLabel lab_text = new JLabel(n.getMessage());
		    
	    JButton bt_like = new JButton();
	    JButton bt_hate = new JButton();
		
	    NewsAction listener = new NewsAction(n);
	    
	    bt_like.addActionListener(listener);
	    bt_like.setActionCommand("likeNews");
	    bt_hate.addActionListener(listener);
	    bt_hate.setActionCommand("hateNews");
	    
	    lab_pic.addMouseListener(listener);
	    
		lab_text.setBounds(8, 5, width, 30);
		lab_text.setForeground(Color.WHITE);
		lab_text.setFont(new Font("Verdana", Font.BOLD, 22));
			          
        bt_like.setBounds(width - 2 * 35, height - 35, 30, 30);
        bt_hate.setBounds(width - 35, height - 35, 30, 30);
        
        lab_pic.setBounds(0, 0, width, height);
        pan_content.setBounds(0, 0, width, height);
	    
        pan_content.add(lab_pic, JLayeredPane.DEFAULT_LAYER, 0);
        // TODO: add User Picture
        pan_content.add(lab_text, JLayeredPane.PALETTE_LAYER, 0);
        pan_content.add(bt_like, JLayeredPane.PALETTE_LAYER, 0);
        pan_content.add(bt_hate, JLayeredPane.PALETTE_LAYER, 0);
        pan_content.setVisible(true);
	    
		return pan_content;
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
	
//==============================================================================================================
//									ACTION HANDLING
//==============================================================================================================

	private class NewsAction implements ActionListener, MouseListener{
		
		private NewsEntry news;
		public NewsAction(NewsEntry n){
			this.news = n;
		}
		
		public void actionPerformed(ActionEvent e) {
			if (e.getActionCommand().equals("likeNews")){
				news.getSource().like(SessionThing.getInstance().getSessionUser());
			}else if (e.getActionCommand().equals("hateNews")){
				System.out.println("Hate " + news.getPictureName() );
				news.getSource().hate(SessionThing.getInstance().getSessionUser());
			}
		}

		public void mouseClicked(MouseEvent e) {
			if (news.getSource() instanceof Medium){
				System.out.println("PVC - 252 - Ich bin ein Medium");
			}else if(news.getSource() instanceof Album){
				System.out.println("PVC - 252 - Ich bin ein Album");
				
			}else if(news.getSource() instanceof User){
			
				nextContent =  new ProfileVController( (User) news.getSource() );
				setChanged();
				notifyObservers(nextContent);
				
				System.out.println("PVC - 252 - Ich bin ein User");
			}
		}

		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}

public ContentController getNextContent() {
	// TODO Auto-generated method stub
	return null;
}
	
}
