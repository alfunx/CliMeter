package ch.uzh.ifi.rerg.se16_climeter.client;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.TabLayoutPanel;

/**
 * The class UserInterface creates the tab structured GUI and adds data source
 * information to the visible part of the web site.
 * 
 * @author 	Timo Surbeck
 * @history 2016-11-01 TS First Version 
 * 			2016-11-03 TS Several layout improvements
 *  		2016-11-07 TS Added data source information
 *  		2016-11-10 TS Layout reorganization, now using DockLayoutPanel
 * @version	2016-11-10 TS 1.1
 * 
 * @responsibilities This class holds several children-objects of the abstract
 *                   class Visualisation.
 */
public class UserInterface {

	private final String DATA_SOURCE = "Berkeley Earth";
	private final String SOURCE_URL = "http://www.berkeleyearth.org/";

	private TabLayoutPanel tabs;
	
	private Visualisation map = new Map(Data.getRandomData(100));
	private Visualisation table = new Table(Data.getRandomData(1000));
	// private FilterMenu filterMenu;
	
	/**
	 * Creates the GUI and displays the data source information.
	 * @pre	-
	 * @post - 
	 */
	public void createGUI() {
			
		// Creates tab structure
		
		tabs = new TabLayoutPanel(20, Unit.PT);
		
		tabs.add(map.getPanel(), "Map");
		tabs.add(table.getPanel(), "Table");
		tabs.add(new Label("Filter"), "Filter");
		tabs.addStyleName("tabContainer");
		
		tabs.setAnimationDuration(450);
		tabs.setAnimationVertical(false);
		
		// Adds title label

		Label title = new Label("CliMeter");
		title.addStyleName("title");
		
		// Adds data source info & link below tab structure
		
		HorizontalPanel sourceContainer = new HorizontalPanel();
		sourceContainer.addStyleName("sourceContainer");
		
		Label sourceInfo = new Label("Source of raw data:");
		Anchor sourceLink = new Anchor(DATA_SOURCE, SOURCE_URL);
		sourceLink.setTarget("_blank");
		sourceInfo.addStyleName("sourceInfo");
		
		sourceContainer.add(sourceInfo);
		sourceContainer.add(sourceLink);
		
		HorizontalPanel southContainer = new HorizontalPanel();
		southContainer.setSize("100%", "100%");
		southContainer.add(sourceContainer);
		
		// Creates a DockPanel called mainPanel and adds all GUI components
		
		DockLayoutPanel mainPanel = new DockLayoutPanel(Unit.EM);
		
		mainPanel.addNorth(title, 10);
		mainPanel.addSouth(southContainer, 5);
		mainPanel.add(tabs);
		
		// Adds mainPanel to the RootLayoutPanel
		
		RootLayoutPanel rootLayoutPanel = RootLayoutPanel.get();
		rootLayoutPanel.add(mainPanel);
		
	}
	
}
