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
 * @author 	Timo Surbeck
 * @history 2016-11-01 TS First Version 
 * 			2016-11-03 TS Several layout improvements
 *  		2016-11-07 TS Added data source information
 * @version	2016-11-07 TS 1.0
 * 
 * @responsibilities This class holds several children-objects of the abstract
 *                   class Visualisation.
 */
public class UserInterface {
<<<<<<< HEAD
<<<<<<< HEAD
=======

>>>>>>> origin/timolex/experimental

	private final String DATA_SOURCE = "Berkeley Earth";
	private final String SOURCE_URL = "http://www.berkeleyearth.org/";

<<<<<<< HEAD
=======
	
	private final String dataSource = "Berkeley Earth";
	private final String sourceURL = "http://www.berkeleyearth.org/";
	
>>>>>>> refs/remotes/origin/master
	private TabLayoutPanel tabs = new TabLayoutPanel(20, Unit.PT);;
=======

	private TabLayoutPanel tabs = new TabLayoutPanel(20, Unit.PT);
>>>>>>> origin/timolex/experimental
	private Visualisation map = new Map(Data.getRandomData(100));
	private Visualisation table = new Table(Data.getRandomData(100));
	// private FilterMenu filterMenu;
	
	/**
	 * Creates the tab structured GUI and displays the data source information.
	 * Requires an already existing TabLayoutPanel object called tabs.
	 * @pre	-
	 * @post - 
	 */

		

	public void createGUI() {
		
		// Handles tab structure
		
		tabs.add(map.getPanel(), "Map");
		tabs.add(table.getPanel(), "Table");
		tabs.add(new Label("Filter"), "Filter");
		
		tabs.setAnimationDuration(450);
		tabs.setAnimationVertical(false);
		
		RootPanel.get("tabContainer").add(tabs);
		
		tabs.setHeight("400px");
<<<<<<< HEAD
<<<<<<< HEAD
		
		// Adds data source info & link below tab structure
=======
		tabs.setAnimationDuration(1000);
>>>>>>> refs/remotes/origin/master
=======

		
		// Adds data source info & link below tab structure

		tabs.setAnimationDuration(1000);

>>>>>>> origin/timolex/experimental
		
		HorizontalPanel sourceContainer = new HorizontalPanel();
		sourceContainer.addStyleName("sourceContainer");
		
		Label sourceInfo = new Label("Source of raw data:");
		Anchor sourceLink = new Anchor(DATA_SOURCE, SOURCE_URL);
		sourceLink.setTarget("_blank");
		sourceInfo.addStyleName("sourceInfo");
		
		sourceContainer.add(sourceInfo);
		sourceContainer.add(sourceLink);
		
		RootPanel.get("sourceContainer").add(sourceContainer);
		
	}
	
}
