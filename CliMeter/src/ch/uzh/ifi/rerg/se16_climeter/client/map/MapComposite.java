package ch.uzh.ifi.rerg.se16_climeter.client.map;

import java.util.List;
import com.google.gwt.maps.client.MapOptions;
import com.google.gwt.maps.client.MapTypeId;
import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.base.LatLng;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.LayoutPanel;

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
 * @version 	2016-11-20 AM 1.2
 * @responsibilities 
 * 				This class contains the map and all layers on top of it.
 */
public class MapComposite extends Composite {
	
	private ColorTransition colorTransition;
	private LayoutPanel panel;
	private MapWidget mapWidget;
	
	/**
	 * Initialize as Composite and add google map on it.
	 * @pre -
	 * @post panel != null, mapWidget != null
	 * @param dataSet Data objects which will be visualised on the map
	 */
	public MapComposite(Map map) {
		this.colorTransition = new ColorTransition(-30.0, 30.0);
		this.panel = new LayoutPanel();
		
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
		LatLng center = LatLng.newInstance(47.37174, 8.54226);
		MapOptions options = MapOptions.newInstance();
		options.setZoom(5);
		options.setCenter(center);
		options.setMapTypeId(MapTypeId.TERRAIN);
		
		// add mapWidget to panel
		this.mapWidget = new MapWidget(options);
		this.panel.clear();
		this.panel.add(this.mapWidget);
		this.mapWidget.setSize("100%", "100%");
	}
	
	@Override
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
	 * @param dataSet a list of Data to add on the map
	 * @return the temperatureOverlay
	 */
	protected TemperatureOverlay addTemperatureOverlay(List<Data> dataSet) {
		TemperatureOverlay temperatureOverlay = new TemperatureOverlay(this.mapWidget, this.colorTransition, dataSet);
		return temperatureOverlay;
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
