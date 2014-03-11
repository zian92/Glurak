package de.glurak.frontend.mainFrame.content.news;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;

import de.glurak.FrontendColors;
import de.glurak.feature.SliderPanel;

public class PromotionView extends JPanel {
	
	private int sliderCount = 0;
	private Dimension panelDim;
	private Dimension sliderDim;
	
	public JButton bt_start;
	public JButton bt_add;
	
	private List<SliderPanel> sliderPanArray = new ArrayList<SliderPanel>();	
	
	/**
	 * Erzeugt ein Panel der Groesse preferedSize. 
	 * Faellt dieses mit Slidern der Groesse preferedSliderSize. 
	 * Anzahl der Slider wird in abhängigkeit von der Groesse dynamisch berechnet
	 *  
	 * @param preferedSize Groesse dieses Panels
	 * @param preferedSliderSize Groesse der einzelnen Slider
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
		this.setBackground(FrontendColors.DARK_GREY);
		// create and position the sliders 
		for (int i = 0; i < sliderCount; i++){
			SliderPanel sp = new SliderPanel();
			sp.setBackground(FrontendColors.DARK_GREY);
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
