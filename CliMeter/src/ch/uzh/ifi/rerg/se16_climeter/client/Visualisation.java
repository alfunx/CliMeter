package ch.uzh.ifi.rerg.se16_climeter.client;

import com.google.gwt.user.client.ui.LayoutPanel;

/**
 * Abstract class, which has to be extended, to be visualised in the User Interface.
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
