package ch.uzh.ifi.rerg.se16_climeter.client.map;

import java.util.Date;
import java.util.List;
import java.util.TreeMap;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.maps.client.MapOptions;
import com.google.gwt.maps.client.MapTypeId;
import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.base.LatLng;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.LayoutPanel;

import ch.uzh.ifi.rerg.se16_climeter.client.Data;
import ch.uzh.ifi.rerg.se16_climeter.client.timeline.TimeLine;

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
 * 				2016-11-28 AM Optional caching for temperature overlays
 * @version 	2016-11-25 AM 1.4
 * @responsibilities 
 * 				This class contains the map and all layers on top of it. It 
 * 				loads the TimeLine aswell.
 */
public class MapComposite extends Composite {

	private final int MAP_ZOOM = 5;
	private final LatLng MAP_CENTER = LatLng.newInstance(47.37174, 8.54226);
	private final boolean MAP_STREETVIEW = false;
	private final MapTypeId MAP_TYPE = MapTypeId.TERRAIN;
	private final double SOUTHPANEL_HEIGHT = 3.6;

	private final double DATASET_MIN = -30.0;
	private final double DATASET_MAX = 30.0;
	private final int RANDOM_DATA_AMOUNT = 140;

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
		options.setStreetViewControl(MAP_STREETVIEW);
		options.setMapTypeId(MAP_TYPE);

		// add mapWidget to panel
		LayoutPanel mapPanel = new LayoutPanel();
		this.mapWidget = new MapWidget(options);
		mapPanel.clear();
		mapPanel.add(this.mapWidget);
		this.mapWidget.setSize("100%", "100%");

		// add timeLine to panel
		LayoutPanel timeLinePanel = new LayoutPanel();
		addTimeLineToPanel(timeLinePanel);

		// add to composite panel
		this.panel.addSouth(timeLinePanel, SOUTHPANEL_HEIGHT);
		this.panel.add(mapPanel);

		// TODO: remove
		addTemperatureOverlay(Data.getRandomData(RANDOM_DATA_AMOUNT));
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
	 * Add a set of data on the map (with caching).
	 * @pre temperatureOverlays != null
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

		if (this.activeTemperatureOverlay == null) {
			newTemperatureOverlay.setVisibility(true);
		} else if (this.activeTemperatureOverlay != newTemperatureOverlay) {
			newTemperatureOverlay.setVisibility(true);
			this.activeTemperatureOverlay.setVisibility(false);
		}

		this.activeTemperatureOverlay = newTemperatureOverlay;
	}

	/**
	 * Add a set of data on the map (without caching).
	 * @pre -
	 * @post -
	 * @param dataSet a list of Data to add on the map
	 */
	public void addTemperatureOverlay(List<Data> dataSet) {
		TemperatureOverlay newTemperatureOverlay = new TemperatureOverlay(this.mapWidget, this.colorTransition, dataSet);
		newTemperatureOverlay.setVisibility(true);

		if (this.activeTemperatureOverlay != null) {
			this.activeTemperatureOverlay.setVisibility(false);
		}

		this.activeTemperatureOverlay = newTemperatureOverlay;
	}

	/**
	 * Add timeLine to panel and return.
	 * @pre timeLinePanel != null
	 * @post -
	 * @param timeLinePanel the panel to add timeLine
	 * @return the panel with the timeLine in it
	 */
	protected LayoutPanel addTimeLineToPanel(LayoutPanel timeLinePanel) {
		timeLinePanel.setSize("100%", "100%");
		TimeLine timeLine = new TimeLine(1895, 2015);

		timeLinePanel.add(timeLine);
		return timeLinePanel;
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
