package ch.uzh.ifi.rerg.se16_climeter.client;

import com.google.gwt.user.client.ui.LayoutPanel;

/**
 * The class Filter contains all filtering criteria.
 * TODO: implement Observable
 * 
 * @author Alphonse Mariyagnanaseelan
 * @history 2016-11-02 AM Initial Commit
 * @version 2016-11-02 AM 1.0
 * 
 * @responsibilities This class contains filtering criteria. Is subject and 
 *                   updates observers if changes occur.
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
