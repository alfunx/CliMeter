package ch.uzh.ifi.rerg.se16_climeter.client;

import com.google.gwt.user.client.ui.LayoutPanel;

/**
 * The abstract class Visualisation represents a widget, that can be visualised 
 * in the class UserInterface. It has to be extended in order to be visualised.
 * 
 * @author 		Alphonse Mariyagnanaseelan
 * @history 	2016-11-02 AM Initial Commit
 * @version 	2016-11-02 AM 1.0
 * @responsibilities 
 * 				This class makes sure visualisations offer a method 
 * 				getPanel().
 */
public abstract class Visualisation {
	
	protected LayoutPanel panel = new LayoutPanel();
	
	/**
	 * @return the panel, which contains the visualisations.
	 */
	public LayoutPanel getPanel() {
		return panel;
	}
	
}
