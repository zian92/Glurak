package de.glurak.frontend.mainFrame.content.news;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

import de.glurak.Query;
import de.glurak.data.Album;
import de.glurak.data.Medium;
import de.glurak.data.NewsEntry;
import de.glurak.data.User.ListenerProfile;
import de.glurak.data.User.User;
import de.glurak.feature.IconLoader;
import de.glurak.frontend.SessionThing;
import de.glurak.frontend.mainFrame.ContentController;
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
	
	private boolean bool_timerstart;
	private java.util.Timer time_slider;
	private java.util.TimerTask timTask_slide;
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
	 * einer NewsList, die in der Datenbank gespeichert ist.
	 * TODO: Mehrere NewsListen (eigene, globale, etc)
	 */
	private void initNewsEntries(){
		// Init NewsList
		List<NewsEntry> newsEntrylist = SessionThing.getInstance().getDatabase().getAllEntries();
		for (int j = 0; j < newsEntrylist.size(); j++){
			newsList.add(buildEntryView(200, 180, newsEntrylist.get(j)));
		}
		// Distribute the Entries to the Sliders on Screen
		int sMax = promPan.getSliderCount();
		int q = newsList.size()/sMax;
		if (sMax*q<newsList.size()){
			q=q+1;
		}
		for (int pos = 0; pos < sMax; pos++){
				for (int j = 0; j < q; j++){
					if (newsList.size() <= (pos+(j*sMax))) break;
					promPan.getSLiderAtPos(pos).addSliderComponent((newsList.get(pos+(j*sMax))));
			}
		} 
		// Start the SLiding-task
		if (!bool_timerstart)
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
		JLabel lab_pic = new JLabel();
		
		if (n.getSource().entryPicture() == null){
			lab_pic.setIcon(new IconLoader(width, height, Query.FOLDER_PICTURE_ICONS + "musicfile.jpg").getIcon());
		}else {
			lab_pic.setIcon(new IconLoader(width, height, n.getSource().entryPicture()).getIcon());
		}
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
		
		 timTask_slide = new java.util.TimerTask() {
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
		time_slider = new java.util.Timer();
		time_slider.schedule(timTask_slide, 1000, 2000);
		bool_timerstart = true;
	}
	
	private void cleanSliders(){
		int sMax = promPan.getSliderCount();
		int q = newsList.size()/sMax;
		if (sMax*q<newsList.size()){
			q=q+1;
		}
		for (int pos = 0; pos < sMax; pos++){
				for (int j = 0; j < q; j++){
					if (newsList.size() <= (pos+(j*sMax))) break;
					promPan.getSLiderAtPos(pos).removeSliderCompinent((newsList.get(pos+(j*sMax))));
			}
		} 
		for (int j = 0; j< newsList.size(); j++){
			newsList.remove(j);
		}
		
	}
	
	public void reload() {
		// TODO Auto-generated method stub
		cleanSliders();
		initNewsEntries();
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
				news.getSource().hate(SessionThing.getInstance().getSessionUser());
			}
		}

		public void mouseClicked(MouseEvent e) {
			if (news.getSource() instanceof Medium){
				
			}else if(news.getSource() instanceof Album){
				
			}else if(news.getSource() instanceof User){		
				nextContent =  new ProfileVController( (User) news.getSource() );
				setChanged();
				notifyObservers(nextContent);
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


	
}
