package de.glurak.frontend.mainFrame.playQueue;

import java.awt.BorderLayout;
import java.awt.ScrollPane;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.ScrollPaneLayout;

import de.glurak.data.Medium;
import de.glurak.data.Playlist;

/**
 * Gui für den MediaPlayer, mit ControllPanel(Play/Stop,Next/Prev) && QueueView
 * @author MMÜhlenjost
 *
 */
public class PlayQueueView extends JPanel{
	private JButton 	playButton;
	private JButton 	nextButton;
	private JButton 	previousButton;
	private EJSlider 	positionBar;
	private JPanel		controllPanel;
	private JPanel		t;
	private QueuePanel	queuePanel;
	private JScrollPane scrollbar;
	private boolean 	positionChange;
	
	public PlayQueueView (){
		this(null,null);
		
		
	}
	/**
	 * @param playlist die abzuspielende Playlist
	 * @param current  das gerade abgespielte Medium
	 */
	public PlayQueueView (Playlist playlist,Medium current){
		super();
		positionChange = false;
		initComponents(playlist,current);
	
	}
	/**
	 * initialisiert View f�r die angegebene Playlist
	 */
	public void initComponents(Playlist playlist,Medium current){
		
		this.setLayout(new BorderLayout());
		
		setPlayButton(new JButton("Play"));
		setNextButton(new JButton("=>"));
		setPreviousButton(new JButton("<="));
		positionBar = new EJSlider(0,0,0);
		positionBar.setOrientation(JSlider.HORIZONTAL);
		positionBar.setExtent(0);
		setControllPanel(new JPanel());
		getControllPanel().setLayout(new BorderLayout());
		
		getControllPanel().add(getPlayButton(),BorderLayout.CENTER);
		getControllPanel().add(getNextButton(),BorderLayout.EAST);
		getControllPanel().add(getPreviousButton(),BorderLayout.WEST);
		
		if(playlist!=null){
			initQueueView(playlist,current);}
		
		
		
		this.add(getControllPanel(),BorderLayout.WEST);
		this.add(getPositionBar(),BorderLayout.NORTH);
		
	}
	
	/**
	 * Initialisiert QueueView(Scrollbar+Panels)
	 * @param playlist die abzuspielende Playlist
	 * @param current  das gerade abgespielte Medium
	 */
	public void initQueueView(Playlist playlist,Medium current){
		
		setQueuePanel(new QueuePanel(playlist,current));
		this.add(getQueuePanel());
		scrollbar = new JScrollPane(getQueuePanel().getFirstPanel(),JScrollPane.VERTICAL_SCROLLBAR_NEVER,   
	        	JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollbar.setLayout(new ScrollPaneLayout());
		getQueuePanel().add(scrollbar);
		this.validate();
	}
	
	public JButton getPlayButton() {
		return playButton;
	}

	public void setPlayButton(JButton playButton) {
		this.playButton = playButton;
	}

	public JButton getNextButton() {
		return nextButton;
	}
	public JButton[] getPlaylistButton(){
		return getQueuePanel().getMediumButtonArray();
	}
	public void setNextButton(JButton nextButton) {
		this.nextButton = nextButton;
	}

	public void changePositionBar(int newValue){
		positionChange = true;
		this.positionBar.setValue(newValue);
		positionChange = false;
	}

	public JSlider getPositionBar() {
		return positionBar;
	}

	public void setPositionBar(JSlider positionBar) {
		positionChange = true;
		this.positionBar.setMaximum(positionBar.getMaximum());
		this.positionBar.setValue(positionBar.getValue());
		positionChange = false;
	}

	public JPanel getControllPanel() {
		return controllPanel;
	}

	public void setControllPanel(JPanel jPanel) {
		this.controllPanel = jPanel;
	}

	public JButton getPreviousButton() {
		return previousButton;
	}

	public void setPreviousButton(JButton previousButton) {
		this.previousButton = previousButton;
	}

	public QueuePanel getQueuePanel() {
		return queuePanel;
	}
	public void setQueuePanel(QueuePanel queuePanel) {
		this.queuePanel = queuePanel;
	}

	public JScrollPane getScrollbar(){
		return scrollbar;
	}
	public void setScrollbar(JScrollPane scrollbar){
		this.scrollbar = scrollbar;
	}
	public boolean isPositionChange() {
		return positionChange;
	}
	public void setPositionChange(boolean positionChange) {
		this.positionChange = positionChange;
	}
	
	
}
