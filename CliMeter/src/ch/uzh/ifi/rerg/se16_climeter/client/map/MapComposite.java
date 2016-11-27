package ch.uzh.ifi.rerg.se16_climeter.client.map;

import java.util.Date;
import java.util.List;
import java.util.TreeMap;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.maps.client.MapOptions;
import com.google.gwt.maps.client.MapTypeId;
import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.base.LatLng;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.LayoutPanel;

import ch.uzh.ifi.rerg.se16_climeter.client.Console;
import ch.uzh.ifi.rerg.se16_climeter.client.Data;

/**
 * The class MapComposite is a concrete Map, load into a Composite object.
 * 
 * @author 		Alphonse Mariyagnanaseelan
 * @history 	2016-11-03 AM Initial Commit
 * 				2016-11-04 AM Displays simple map
 * 				2016-11-06 AM Displays data points on the map
 * 				2016-11-07 AM Displays multiple data points
 * 				2016-11-14 AM Gray-map glitch fixed
 * 				2016-11-16 AM Added dynamic colored data points
 * 				2016-11-20 AM Moved overlay creation to TemperatureOverlay
 * 				2016-11-23 AM Completely restructured, ready for TimeLine
 * 				2016-11-25 AM ShuffleButton as placeholder for TimeLine
 * 				2016-11-25 AM Map constants added
 * @version 	2016-11-25 AM 1.3
 * @responsibilities 
 * 				This class contains the map and all layers on top of it. It 
 * 				loads the TimeLine aswell.
 */
public class MapComposite extends Composite {
	
	private final double DATASET_MIN = -30.0;
	private final double DATASET_MAX = 30.0;
	private final int MAP_ZOOM = 5;
	private final LatLng MAP_CENTER = LatLng.newInstance(47.37174, 8.54226);
	private final double SOUTHPANEL_HEIGHT = 2.5;
	
	private long counter = 0;
	
	private ColorTransition colorTransition;
	private DockLayoutPanel panel;
	private MapWidget mapWidget;
	
	private TemperatureOverlay activeTemperatureOverlay;
	private TreeMap<Long, TemperatureOverlay> temperatureOverlays;
	
	/**
	 * Initialize as Composite and add google map on it.
	 * @pre -
	 * @post panel != null, mapWidget != null
	 * @param dataSet Data objects which will be visualised on the map
	 */
	public MapComposite() {
		this.colorTransition = new ColorTransition(DATASET_MIN, DATASET_MAX);
		this.panel = new DockLayoutPanel(Unit.EM);
		
		this.temperatureOverlays = new TreeMap<Long, TemperatureOverlay>();
		
		initWidget(this.panel);
		draw();
	}
	
	/**
	 * Draws the basic map.
	 * @pre panel != null
	 * @post mapWidget.getParent == panel
	 */
	private void draw() {
		// set up basic map
		MapOptions options = MapOptions.newInstance();
		options.setZoom(MAP_ZOOM);
		options.setCenter(MAP_CENTER);
		options.setMapTypeId(MapTypeId.TERRAIN);
		
		// add mapWidget to panel
		LayoutPanel mapPanel = new LayoutPanel();
		this.mapWidget = new MapWidget(options);
		mapPanel.clear();
		mapPanel.add(this.mapWidget);
		this.mapWidget.setSize("100%", "100%");
		
		// TODO: Change Button to TimeLine.
		
		// add shuffle button (later: timeline)
		LayoutPanel timeLinePanel = new LayoutPanel();
		Button shuffleButton = new Button("Shuffle Data");
		shuffleButton.setSize("100%", "100%");
		shuffleButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				Console.log("counter: " + counter);
				addTemperatureOverlay(new Date(counter++ % 5), Data.getRandomData(140));
			}
		});
		timeLinePanel.add(shuffleButton);
		
		// add to composite panel
		this.panel.addSouth(timeLinePanel, SOUTHPANEL_HEIGHT);
		this.panel.add(mapPanel);
	}
	
	@Override
	/**
	 * Workaround to fix a bug in the API.
	 * @pre -
	 * @post -
	 * @see com.google.gwt.user.client.ui.Composite#onAttach()
	 */
	protected void onAttach() {
		super.onAttach();
		
		// workaround to fix a glitch, where the map occasionally stays gray
		// needed for Internet Explorer
		Timer timer = new Timer() {
			@Override
			public void run() {}
		};
		timer.schedule(1);
	}
	
	/**
	 * Add a set of data on the map.
	 * @pre -
	 * @post -
	 * @param date date of the temperatureOverlay
	 * @param dataSet a list of Data to add on the map
	 */
	public void addTemperatureOverlay(Date date, List<Data> dataSet) {
		TemperatureOverlay newTemperatureOverlay = this.temperatureOverlays.get(date.getTime());
		
		if (newTemperatureOverlay == null) {
			newTemperatureOverlay = new TemperatureOverlay(this.mapWidget, this.colorTransition, dataSet);
			this.temperatureOverlays.put(date.getTime(), newTemperatureOverlay);
		}
		newTemperatureOverlay.setVisibility(true);
		
		if (activeTemperatureOverlay != null) {
			this.activeTemperatureOverlay.setVisibility(false);
		}
		
		this.activeTemperatureOverlay = newTemperatureOverlay;
	}
	
	/**
	 * @pre -
	 * @post -
	 * @return the mapWidget
	 */
	protected MapWidget getMapWidget() {
		return this.mapWidget;
	}
	
}
