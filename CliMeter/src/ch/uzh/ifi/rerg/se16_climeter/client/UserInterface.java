package ch.uzh.ifi.rerg.se16_climeter.client;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TabLayoutPanel;

/**
 * The class UserInterface creates the tab structured GUI and adds data source
 * information to the visible part of the web site.
 * 
 * @author Timo Surbeck
 * @history 2016-11-01 First Version 
 * 			2016-11-03 Several layout improvements
 * @version 2016-11-07 Added data source information
 * 
 * @responsibilities This class holds several children-objects of the abstract
 *                   class Visualisation.
 */
public class UserInterface {

	private final String dataSource = "Berkeley Earth";
	private final String sourceURL = "http://www.berkeleyearth.org/";
	
	private TabLayoutPanel tabs = new TabLayoutPanel(20, Unit.PT);;
	private Visualisation map = new Map(Data.getRandomData(100));
	// private Visualisation table;
	// private FilterMenu filterMenu;
	
	/**
	 * Creates the tab structured GUI and displays the data source information.
	 * Requires an already existing TabLayoutPanel object called tabs.
	 * @pre	-
	 * @post -
	 * @param -
	 * @return - 
	 */
	public void createGUI() {
		
		tabs.add(map.getPanel(), "Map");
		tabs.add(new Label("Table"), "Table");
		tabs.add(new Label("Filter"), "Filter");

		RootPanel.get("tabContainer").add(tabs);

		tabs.setHeight("400px");

		HorizontalPanel sourceContainer = new HorizontalPanel();
		sourceContainer.addStyleName("sourceContainer");

		Label sourceInfo = new Label("Source of raw data:");
		Anchor sourceLink = new Anchor(dataSource, sourceURL);
		sourceLink.setTarget("_blank");

		sourceInfo.addStyleName("sourceInfo");

		sourceContainer.add(sourceInfo);
		sourceContainer.add(sourceLink);

		RootPanel.get("sourceContainer").add(sourceContainer);

	}
}
