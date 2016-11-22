package ch.uzh.ifi.rerg.se16_climeter.client;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.layout.client.Layout.Alignment;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.TabLayoutPanel;
import com.google.gwt.widgetideas.client.SliderBar;
import com.google.gwt.widgetideas.client.SliderBar.LabelFormatter;

import ch.uzh.ifi.rerg.se16_climeter.client.filtermenu.FilterMenu;
import ch.uzh.ifi.rerg.se16_climeter.client.map.Map;
import ch.uzh.ifi.rerg.se16_climeter.client.table.Table;


/**
 * The class UserInterface creates the tab structured GUI and adds data source
 * information to the visible part of the web site.
 * 
 * @author 	Timo Surbeck
 * @history 2016-11-01 TS First Version 
 * 			2016-11-03 TS Several layout improvements
 *  		2016-11-07 TS Added data source information
 *  		2016-11-10 TS Layout reorganization, now using DockLayoutPanel
 *  		2016-11-19 TS Restructured version, extracted methods
 * @version	2016-11-19 TS 1.2
 * 
 * @responsibilities This class holds several children-objects of the abstract
 *                   class Visualisation.
 */
public class UserInterface {

	private final String DATA_SOURCE = "Berkeley Earth";
	private final String SOURCE_URL = "http://www.berkeleyearth.org/";

	private Visualisation map = new Map(Data.getRandomData(100));
	private Visualisation table = new Table(Data.getRandomData(300000));
	// private Visualisation filterMenu = new FilterMenu(Data.getRandomData(100));
	
	private TabLayoutPanel tabs;
	private HorizontalPanel southContainer;
	private DockLayoutPanel mainPanel;
	private DockLayoutPanel mapContainer;
	private DockLayoutPanel mapTabContainer;
	private DockLayoutPanel timeLineContainer;
	private LayoutPanel titleContainer;
	private SliderBar timeLine;
	
	/**
	 * Creates the GUI.
	 * @pre	-
	 * @post - 
	 */
	public void createGUI() {
					
		tabs = createTabs();
		titleContainer = createTitle();
		southContainer = createSouthContainer();
		
		// Creates a DockPanel called mainPanel and adds all GUI components
		
		mainPanel = new DockLayoutPanel(Unit.EM);
		
		mainPanel.addNorth(titleContainer, 6);
		mainPanel.addSouth(southContainer, 4);
		mainPanel.add(tabs);
		
		// Adds mainPanel to the RootLayoutPanel
		
		RootLayoutPanel rootLayoutPanel = RootLayoutPanel.get();
		rootLayoutPanel.add(mainPanel);

	}

	/**
	 * Creates the tab structure.
	 * @pre	-
	 * @post tabs != null 
	 * @return Returns a customized TabLayoutPanel object
	 */
	private TabLayoutPanel createTabs() {
		TabLayoutPanel tabs = new TabLayoutPanel(30, Unit.PX);
				
		mapTabContainer = new DockLayoutPanel(Unit.EM);		
		mapContainer = new DockLayoutPanel(Unit.EM);
		timeLineContainer = new DockLayoutPanel(Unit.PX);
		
		mapContainer.add(map.getPanel());
		timeLineContainer.add(createTimeLine());

		
		mapTabContainer.addNorth(mapContainer, 50);
		mapTabContainer.addSouth(timeLineContainer, 2.5);
		
		tabs.add(mapTabContainer, "Map");
		tabs.add(table.getPanel(), "Table");
		//tabs.add(filterMenu.getPanel(), "Filter");
		
		tabs.addStyleName("tabContainer");
		tabs.setAnimationDuration(450);
		tabs.setAnimationVertical(false);
		
		return tabs;
	}

	/**
	 * Creates the source info containing object.
	 * @pre	-
	 * @post southContainer != null 
	 * @return Returns a HorizontalPanel object containing 
	 * 		   source info and a hyperlink.
	 */
	private HorizontalPanel createSouthContainer() {
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
		
		return southContainer;
	}

	/**
	 * Creates the web application title.
	 * @pre	-
	 * @post titleContainer != null 
	 * @return Returns a LayoutPanel object containing the title
	 */
	private LayoutPanel createTitle() {
		LayoutPanel titleContainer = new LayoutPanel();
		Label title = new Label("CliMeter");
		
		title.addStyleName("title");
		titleContainer.add(title);
		titleContainer.addStyleName("titleContainer");
		titleContainer.setWidgetHorizontalPosition(title, Alignment.END);
		return titleContainer;
	}
	
	
	private SliderBar createTimeLine()
	{
	timeLine = new SliderBar(1900, 2015, new LabelFormatter() {
	public String formatLabel(SliderBar slider, double value) {
	return (int) (10 * value) / 10 + "";
	}
	});
	timeLine.setStepSize(1.0);
	timeLine.setMinValue(1900);
	timeLine.setMaxValue(2015);
	timeLine.setCurrentValue((1900 + 2015)/2);
	timeLine.setNumTicks((int) (2015 - 1900));
	timeLine.setNumLabels(13);

	return timeLine;
	}
}