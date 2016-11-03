package ch.uzh.ifi.rerg.se16_climeter.client;


import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TabLayoutPanel;


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
		
		
		Label sourceInfo = new Label("Following hyperlink leads to datasource: ");
		sourceInfo.addStyleName("sourceInfo");
		
		Anchor sourceLink = new Anchor("Google.ch", "www.google.ch");
		
		RootPanel.get("sourceInfoContainer").add(sourceInfo);
		RootPanel.get("sourceLinkContainer").add(sourceLink);
	}
}
