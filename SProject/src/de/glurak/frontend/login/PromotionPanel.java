package de.glurak.frontend.login;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

import de.glurak.feature.SliderPanel;

public class PromotionPanel extends JPanel {
	private SliderPanel mySlider_0;
	private SliderPanel mySlider_1;
	private SliderPanel mySlider_2;
	private SliderPanel mySlider_3;
	
	private int sliderCount = 4;
	
	public JButton bt_start;
	
	public JButton bt_add;
	
	private SliderPanel[] sliderArray;
	
	public PromotionPanel(int maxSliders){
		super();
		
		this.sliderCount = maxSliders;
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		/*
		for (int i = 0; i < maxSliders; i++){
			sliderArray[i] =  new SliderPanel();
			c.gridx = i;
			c.gridy = 0;
			add(sliderArray[i] ,c);
		}
		*/
		mySlider_0 = new SliderPanel();
		mySlider_1 = new SliderPanel();
		mySlider_2 = new SliderPanel();
		mySlider_3 = new SliderPanel();
		/*
		mySlider_5 = new JSLSlider();
		mySlider_6 = new JSLSlider();
		*/
		
		
		
		c.gridx = 0;
		c.gridy = 0;
		add(mySlider_0,c);
		
		c.gridx = 1;
		c.gridy = 0;
		add(mySlider_1,c);
		c.gridx = 0;
		c.gridy = 1;
		add(mySlider_2,c);
		c.gridx = 1;
		c.gridy = 1;
		add(mySlider_3,c);
		/*
		c.gridx = 2;
		c.gridy = 0;
		add(mySlider_5,c);
		c.gridx = 2;
		c.gridy = 1;
		add(mySlider_6,c);
		*/
		c.fill = GridBagConstraints.HORIZONTAL;
	  	c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 2;
		
		bt_start = new JButton("Start");
		add(bt_start, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
	  	c.gridx = 0;
		c.gridy = 3;
		c.gridwidth = 2;
		
		bt_add = new JButton("add Bieber");
		add(bt_add, c); 
		setVisible(true);
	}
	
	public int getSliderCount(){ return this.sliderCount; }
	
	public SliderPanel getSLiderAtPos(int pos){
		
		switch (pos){
		
		case 0:	return mySlider_0;

		case 1:	return mySlider_1;

		case 2:	return mySlider_2;
		
		case 3: return mySlider_3;
		/*
		case 4: return mySlider_5;
		
		case 5: return mySlider_6;
		*/
		default: return mySlider_0;
		}
		
	}
	
}
