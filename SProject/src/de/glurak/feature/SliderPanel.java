package de.glurak.feature;


import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.Timer;

import de.glurak.FrontendColors;

public class SliderPanel extends JPanel {
	JPanel pan_main = new JPanel(new SliderCardLayout());
	private int itemCount;
	
	public SliderPanel(){
		setLayout(new BorderLayout());
		pan_main.setBackground(FrontendColors.DARK_GREY);
		add(pan_main, BorderLayout.CENTER);
		this.itemCount = -1;
	}
	
	public int getItemCount(){ return this.itemCount; }
	
	public void previous(){
		SliderCardLayout cl=(SliderCardLayout) pan_main.getLayout();
		Component currentComp = cl.getCurrentComponent(pan_main);
		Component prevComp = cl.getPreviousComponent(pan_main);
		Rectangle b = currentComp.getBounds();
		prevComp.setBounds(b);
		prevComp.setVisible(true);
		SliderListener sl = new SliderListener(20, currentComp, prevComp, false);
		Timer tm = new Timer(40, sl);
		sl.timer= tm;
		tm.start();
	}
	
	public void next(){
		SliderCardLayout cl=(SliderCardLayout) pan_main.getLayout();
		Component currentComp = cl.getCurrentComponent(pan_main);
		Component nextComp = cl.getNextComponent(pan_main);
		Rectangle b = currentComp.getBounds();
		nextComp.setVisible(true);
		SliderListener sl = new SliderListener(20, currentComp, nextComp, true);
		Timer tm = new Timer(40, sl);
		sl.timer= tm;
		tm.start();
	}
	
	public void addSliderComponent(JComponent c){
		pan_main.add(c);
		itemCount = itemCount + 1;
	}
	
	public void removeSliderCompinent(JComponent c){
		pan_main.remove(c);
		itemCount = itemCount - 1;
	}
	
	public void refresh(){
		revalidate();
		repaint();
	}
	
	public class SliderListener implements ActionListener{
		Component c1;
		Component c2;
		
		int steps;
		int step= 0;
		Timer timer;
		boolean isNext;
		
		public SliderListener(int steps, Component c1, Component c2, boolean isNext){
			this.c1 = c1;
			this.c2 = c2;
			this.steps = steps;
			this.isNext = isNext;
		}
		
		public void actionPerformed(ActionEvent arg0) {
			Rectangle bounds = c1.getBounds();
			int shift=bounds.width/steps;
			
			if(!isNext){
				c1.setLocation(bounds.x+shift, bounds.y);
				c2.setLocation(bounds.x+shift-bounds.width, bounds.y);
			}else{
				c1.setLocation(bounds.x-shift, bounds.y);
				c2.setLocation(bounds.x-shift+bounds.width, bounds.y);
				
			}
			
			pan_main.repaint();
			step++;
			if (step == steps ){
				timer.stop();
				c2.setVisible(false);
				CardLayout cl = (CardLayout)pan_main.getLayout();
				if (isNext){
					cl.next(pan_main);
				}else{
					cl.previous(pan_main);
				}
			
			}
			
		}
		
	}
	
}
