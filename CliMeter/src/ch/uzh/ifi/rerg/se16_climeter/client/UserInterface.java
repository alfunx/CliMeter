package ch.uzh.ifi.rerg.se16_climeter.client;


import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TabLayoutPanel;
import com.google.gwt.user.client.ui.VerticalPanel;


public class UserInterface {
	/**
	 * The message displayed to the user when the server cannot be reached or
	 * returns an error.
	 */
	/*private static final String SERVER_ERROR = "An error occurred while "
			+ "attempting to contact the server. Please check your network " + "connection and try again.";
	*/
	
	private TabLayoutPanel tabs;
	//private Visualisation map;
	//private Visualisation table;
	//private Filter filter;
	
	
	public void createGUI() {
		tabs = new TabLayoutPanel(20, Unit.PT);
		
		tabs.add(new Label("Map"), "Map");
		tabs.add(new Label("Table"), "Table");
		tabs.add(new Label("Filter"), "Filter");
		
		RootPanel.get("tabContainer").add(tabs);

		tabs.setHeight("400px");
		
		HorizontalPanel sourceContainer = new HorizontalPanel();
		sourceContainer.addStyleName("sourceContainer");
		
		Label sourceInfo = new Label("Source of raw data:");
		Anchor sourceLink = new Anchor("Berkeley Earth", "http://www.berkeleyearth.org/");
		sourceLink.setTarget("_blank");
		
		sourceInfo.addStyleName("sourceInfo");
		
		sourceContainer.add(sourceInfo);
		sourceContainer.add(sourceLink);
		
		RootPanel.get("sourceContainer").add(sourceContainer);

	}
}
