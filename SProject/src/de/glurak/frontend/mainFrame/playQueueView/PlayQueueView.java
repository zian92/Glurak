package de.glurak.frontend.mainFrame.playQueueView;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSlider;

public class PlayQueueView extends JPanel{
	private JButton playButton;
	private JButton nextButton;
	private JButton previousButton;
	private JButton increaseVolumeButton;
	private JButton decreaseVolumeButton;
	private JSlider positionBar;
	private JPanel	controllPanel;
	private JPanel	playQueuePanel;
	
	public PlayQueueView (){
		super();
		initComponents();	
	}
	
	/**
	 * 
	 */
	public void initComponents(){
		
		this.setLayout(new BorderLayout());
		
		setPlayButton(new JButton("Play"));
		setNextButton(new JButton("=>"));
		setPreviousButton(new JButton("<="));
		setIncreaseVolumeButton(new JButton("+"));
		setDecreaseVolumeButton(new JButton("-"));
		setPositionBar(new JSlider(JSlider.HORIZONTAL,0,50,0));	
		setControllPanel(new JPanel());
		setPlayQueuePanel(new JPanel());
		getControllPanel().setLayout(new BorderLayout());
		
		getControllPanel().add(getPlayButton(),BorderLayout.CENTER);
		getControllPanel().add(getNextButton(),BorderLayout.EAST);
		getControllPanel().add(getPreviousButton(),BorderLayout.WEST);
		getControllPanel().add(getIncreaseVolumeButton(),BorderLayout.NORTH);
		getControllPanel().add(getDecreaseVolumeButton(),BorderLayout.SOUTH);
		
		this.add(getControllPanel(),BorderLayout.WEST);
		this.add(getPlayQueuePanel(),BorderLayout.CENTER);
		this.add(getPositionBar(),BorderLayout.NORTH);
		
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

	public JPanel getPlayQueuePanel() {
		return playQueuePanel;
	}

	public void setPlayQueuePanel(JPanel playQueuePanel) {
		this.playQueuePanel = playQueuePanel;
	}

	
	
	
	
}
