package ch.uzh.ifi.rerg.se16_climeter.client;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TabLayoutPanel;

public class UserInterface {
	/**
	 * The message displayed to the user when the server cannot be reached or
	 * returns an error.
	 */
	private static final String SERVER_ERROR = "An error occurred while "
			+ "attempting to contact the server. Please check your network " + "connection and try again.";

	private TabLayoutPanel tabs;
	private Visualisation map;
	//private Table table;
	
	/**
	 * This is the entry point method.
	 */
	public void createGUI() {
		tabs = new TabLayoutPanel(20, Unit.PT);
		map = new Map(Data.getRandomData());
		
		tabs.add(map.getPanel(), "Map");
		tabs.add(new Label("Table"), "Table");
		tabs.add(new Label("Filter"), "Filter");
		
		RootPanel.get("tabContainer").add(tabs);

		tabs.setHeight("400px");
	}
}
