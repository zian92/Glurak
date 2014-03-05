package de.glurak.frontend.mainFrame.content.news;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;

import de.glurak.feature.SliderPanel;

public class PromotionView extends JPanel {
	
	private int sliderCount = 0;
	private Dimension panelDim;
	private Dimension sliderDim;
	
	public JButton bt_start;
	public JButton bt_add;
	
	private List<SliderPanel> sliderPanArray = new ArrayList<SliderPanel>();	
	
	/**
	 * Erzeugt ein Panel der Größe preferedSize. 
	 * Füllt dieses mit Slidern der Größe preferedSliderSize. 
	 * Anzahl der Slider wird in abhängigkeit von der Größe dynamisch berechnet
	 *  
	 * @param preferedSize Größe dieses Panels
	 * @param preferedSliderSize Größe der einzelnen Slider
	 * @author MxB
	 */
	public PromotionView(Dimension preferedSize, Dimension preferedSliderSize){
		super();
		// set the size of the panel itself and calculate the maximum amount of sliders fitting in
		setPreferredSize(preferedSize);
		this.panelDim = preferedSize;
		this.sliderDim = preferedSliderSize;
		int rowCount = getMaxRowCount();
		int colCount = getMaxColCount();
		this.sliderCount = (rowCount * colCount);
		setBackground(Color.GREEN);
		// create and position the sliders 
		for (int i = 0; i < sliderCount; i++){
			SliderPanel sp = new SliderPanel();
			sp.setPreferredSize(sliderDim);
			sliderPanArray.add(sp);
		}
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		int sliderPos = 0;
		for (int gy = 0; gy < colCount; gy++){
			for (int gx = 0; gx < rowCount; gx++){
				c.gridx = gx;
				c.gridy = gy;
				add(sliderPanArray.get(sliderPos) ,c);
				sliderPos++;
			}
		}	
		
		/* BUttons for testing 
		c.fill = GridBagConstraints.HORIZONTAL;
	  	c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 2;
		
		bt_start = new JButton("Start");
		add(bt_start, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
	  	c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 2;
		
		bt_add = new JButton("add Bieber");
		add(bt_add, c); 
		*/
		setVisible(true);
		
	}

	private int getMaxRowCount(){
		return panelDim.width / sliderDim.width;
	}
	private int getMaxColCount(){
		return panelDim.height / sliderDim.height;
	}
	
	public int getSliderCount(){ return this.sliderCount; }
	
	public SliderPanel getSLiderAtPos(int pos){
		return sliderPanArray.get(pos);
	}
	
}
