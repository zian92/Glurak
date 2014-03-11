package de.glurak.frontend.mainFrame.playQueue;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.ScrollPaneLayout;
import de.glurak.data.Playqueue;

/**
 * Gui für den MediaPlayer, mit ControllPanel(Play/Stop,Next/Prev) && QueueView
 * @author MMÜhlenjost
 *
 */
public class PlayQueueView extends JPanel{
	protected JButton 	playButton;
	private JButton 	nextButton;
	private JButton 	previousButton;
	private JButton		clearButton;
	private JButton 	saveButton;
	private EJSlider 	positionBar;
	private JPanel		controllPanel;
	private QueuePanel	queuePanel;
	private JScrollPane scrollbar;
	private boolean 	positionChange;
	
	/**
	 * Konstrukter 
	 */
	public PlayQueueView (){
		this(null);
			
	}
	/**
	 * @param Playqueue die abzuspielende Playqueue
	 * initialisiert Komponenten
	 */
	public PlayQueueView (Playqueue playqueue){
		super();
		positionChange = false;
		initComponents(playqueue);
	
	}
	/**
	 * initialisiert View f�r die angegebene Playqueue
	 */
	public void initComponents(Playqueue playqueue){
		
		this.setLayout(new BorderLayout());
		
		setPlayButton(new JButton("Play"));
		getPlayButton().setPreferredSize(new Dimension(80,70));
		setNextButton(new JButton("=>"));
		setPreviousButton(new JButton("<="));
		setClearButton(new JButton("Clear"));
		setSaveButton(new JButton("Save"));
		positionBar = new EJSlider(0,0,0);
		positionBar.setOrientation(JSlider.HORIZONTAL);
		positionBar.setExtent(0);
		setControllPanel(new JPanel());
		getControllPanel().setLayout(new BorderLayout());
		
		getControllPanel().add(getPlayButton(),BorderLayout.CENTER);
		getControllPanel().add(getNextButton(),BorderLayout.EAST);
		getControllPanel().add(getPreviousButton(),BorderLayout.WEST);
		getControllPanel().add(getClearButton(),BorderLayout.SOUTH);
		getControllPanel().add(getSaveButton(),BorderLayout.NORTH);
			
		this.add(getControllPanel(), BorderLayout.WEST);
		this.add(getPositionBar(),BorderLayout.NORTH);
		
	}
	
	/**
	 * Initialisiert QueueView(Scrollbar+Panels)
	 * @param Playqueue die abzuspielende Playqueue
	 */
	public void initQueueView(Playqueue playqueue){
		if(getQueuePanel()==null){
			setQueuePanel(new QueuePanel(playqueue));
			this.add(getQueuePanel(),BorderLayout.CENTER);}
		else {
			getQueuePanel().initComponents(playqueue);
		}
			scrollbar = new JScrollPane(getQueuePanel().getFirstPanel(),JScrollPane.VERTICAL_SCROLLBAR_NEVER,   
	        	JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
			scrollbar.setLayout(new ScrollPaneLayout());
			getQueuePanel().add(scrollbar);
			this.validate();
	}
		
	
	/**Setzt Value des JSlider
	 * Postionchange vorm Verarabeiten auf true, damit der Controller nicht auf diese Änderungen reagiert
	 * @param newValue
	 */
	public void changePositionBar(int newValue){
		positionChange = true;
		this.positionBar.setValue(newValue);
		positionChange = false;
	}

	/**Setzt Maximum und Value des JSlider
	 * Postionchange vorm Verarabeiten auf true, damit der Controller nicht auf diese Änderungen reagiert
	 * @param positionBar 
	 */
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
	public JButton getClearButton() {
		return clearButton;
	}
	public void setClearButton(JButton clearButton) {
		this.clearButton = clearButton;
	}
	public JButton getSaveButton() {
		return saveButton;
	}
	public void setSaveButton(JButton saveButton) {
		this.saveButton = saveButton;
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

	public JSlider getPositionBar() {
		return positionBar;
	}

	
}
