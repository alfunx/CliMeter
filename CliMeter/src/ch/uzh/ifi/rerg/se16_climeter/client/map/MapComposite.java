package ch.uzh.ifi.rerg.se16_climeter.client.map;

import java.util.ArrayList;
import java.util.List;

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
 * @version 	2016-11-23 AM 1.3
 * @responsibilities 
 * 				This class contains the map and all layers on top of it.
 */
public class MapComposite extends Composite {
	
	private ColorTransition colorTransition;
	private DockLayoutPanel panel;
	private MapWidget mapWidget;
	
	private TemperatureOverlay activeTemperatureOverlay;
	private List<TemperatureOverlay> temperatureOverlays;
	
	/**
	 * Initialize as Composite and add google map on it.
	 * @pre -
	 * @post panel != null, mapWidget != null
	 * @param dataSet Data objects which will be visualised on the map
	 */
	public MapComposite() {
		this.colorTransition = new ColorTransition(-30.0, 30.0);
		this.panel = new DockLayoutPanel(Unit.EM);
		
		this.temperatureOverlays = new ArrayList<TemperatureOverlay>();
		
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
				addTemperatureOverlay(Data.getRandomData(140));
			}
		});
		timeLinePanel.add(shuffleButton);
		
		// add to composite panel
		this.panel.addSouth(timeLinePanel, 2.5);
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
	public void addTemperatureOverlay(List<Data> dataSet) {
		
		// TODO: Change type of temperatureOverlays
		//       from ArrayList to Hashtable.
		
//		TemperatureOverlay newTemperatureOverlay = temperatureOverlays.get(0);
//		
//		if (newTemperatureOverlay == null) {
//			newTemperatureOverlay = new TemperatureOverlay(this.mapWidget, this.colorTransition, dataSet);
//			this.temperatureOverlays.add(newTemperatureOverlay);
//		}
		
		TemperatureOverlay newTemperatureOverlay = new TemperatureOverlay(this.mapWidget, this.colorTransition, dataSet);
		this.temperatureOverlays.add(newTemperatureOverlay);
		
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
