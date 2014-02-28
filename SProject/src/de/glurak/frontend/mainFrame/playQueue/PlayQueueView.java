package de.glurak.frontend.mainFrame.playQueueView;

import java.awt.BorderLayout;
import java.awt.ScrollPane;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.ScrollPaneLayout;

import de.glurak.data.Medium;
import de.glurak.data.Playlist;

public class PlayQueueView extends JPanel{
	private JButton 	playButton;
	private JButton 	nextButton;
	private JButton 	previousButton;
	private JButton 	increaseVolumeButton;
	private JButton 	decreaseVolumeButton;
	private JSlider 	positionBar;
	private JPanel		controllPanel;
	private JPanel		t;
	private QueueView	playQueuePanel;
	private JScrollPane scrollbar;
	
	public PlayQueueView (){
		this(null,null);
		
		
	}
	/**
	 * @param playlist die abzuspielende Playlist
	 * @param current  das gerade abgespielte Medium
	 */
	public PlayQueueView (Playlist playlist,Medium current){
		super();
		if(playlist!=null){
		initComponents(playlist,current);}
	}
	/**
	 * initialisiert View f�r die angegebene Playlist
	 */
	public void initComponents(Playlist playlist,Medium current){
		
		this.setLayout(new BorderLayout());
		
		setPlayButton(new JButton("Play"));
		setNextButton(new JButton("=>"));
		setPreviousButton(new JButton("<="));
		setIncreaseVolumeButton(new JButton("+"));
		setDecreaseVolumeButton(new JButton("-"));
		setPositionBar(new JSlider(JSlider.HORIZONTAL,0,50,0));	
		setControllPanel(new JPanel());
		getControllPanel().setLayout(new BorderLayout());
		
		getControllPanel().add(getPlayButton(),BorderLayout.CENTER);
		getControllPanel().add(getNextButton(),BorderLayout.EAST);
		getControllPanel().add(getPreviousButton(),BorderLayout.WEST);
		getControllPanel().add(getIncreaseVolumeButton(),BorderLayout.NORTH);
		getControllPanel().add(getDecreaseVolumeButton(),BorderLayout.SOUTH);
		
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
		
		setPlayQueuePanel(new QueueView(playlist,current));
		this.add(getPlayQueuePanel());
		scrollbar = new JScrollPane(getPlayQueuePanel().getFirstPanel(),JScrollPane.VERTICAL_SCROLLBAR_NEVER,   
	        	JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollbar.setLayout(new ScrollPaneLayout());
		getPlayQueuePanel().add(scrollbar);
	
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

	public void setNextButton(JButton nextButton) {
		this.nextButton = nextButton;
	}

	public JButton getIncreaseVolumeButton() {
		return increaseVolumeButton;
	}

	public void setIncreaseVolumeButton(JButton increaseVolumeButton) {
		this.increaseVolumeButton = increaseVolumeButton;
	}

	public JButton getDecreaseVolumeButton() {
		return decreaseVolumeButton;
	}

	public void setDecreaseVolumeButton(JButton decreaseVolumeButton) {
		this.decreaseVolumeButton = decreaseVolumeButton;
	}

	public JSlider getPositionBar() {
		return positionBar;
	}

	public void setPositionBar(JSlider positionBar) {
		this.positionBar = positionBar;
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

	public QueueView getPlayQueuePanel() {
		return playQueuePanel;
	}

	public void setPlayQueuePanel(QueueView playQueuePanel) {
		this.playQueuePanel = playQueuePanel;
	}

	
	
	
	
}
