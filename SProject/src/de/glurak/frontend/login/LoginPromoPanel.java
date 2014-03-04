package de.glurak.frontend.login;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import de.glurak.feature.SliderPanel;

public class LoginPromoPanel extends JPanel {

	private int sliderCount = 0;
	private Dimension panelDim;
	private Dimension sliderDim;
	
	private List<SliderPanel> sliderPanArray = new ArrayList<SliderPanel>();
	
	public LoginPromoPanel(Dimension prefferedSize, Dimension prefferedSliderSize){
		super();
		// set the size of the panel itself and calculate the maximum amount of sliders fitting in
		setPreferredSize(prefferedSize);
		this.panelDim = prefferedSize;
		this.sliderDim = prefferedSliderSize;
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
