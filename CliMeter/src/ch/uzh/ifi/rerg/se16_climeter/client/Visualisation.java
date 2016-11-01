package ch.uzh.ifi.rerg.se16_climeter.client;

import java.util.Observer;

import com.google.gwt.user.client.ui.LayoutPanel;

/**
 * Abstract class, which has to be extended, to be visualised in the User Interface.
 */
public abstract class Visualisation implements Observer {
	
	private LayoutPanel panel = new LayoutPanel();
	
	/**
	 * @return the panel, which contains the visualisations.
	 */
	public LayoutPanel getPanel() {
		return panel;
	}
	
}
