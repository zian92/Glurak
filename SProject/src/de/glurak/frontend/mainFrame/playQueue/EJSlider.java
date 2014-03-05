package de.glurak.frontend.mainFrame.playQueue;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JSlider;

/**JSlider , der direkt zur angeklickten Value springt
 * @author MMÜhlenjost
 *
 */
public class EJSlider extends JSlider {

	   public EJSlider(int min,int max,int value) {
	      super(min,max,value);
	      addMouseListener(new MouseAdapter() {
	         @Override
	         public void mousePressed(MouseEvent e) {
	            Point p = e.getPoint();
	            double percent = p.x / ((double) getWidth());
	            int range = getMaximum() - getMinimum();
	            double newVal = range * percent;
	            int result = (int)(getMinimum() + newVal);
	            System.out.println(result);
	            setValue(result);
	         }
	      });
	   }
}	   