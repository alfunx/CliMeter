package ch.uzh.ifi.rerg.se16_climeter.client.map;

import java.util.ArrayList;
import java.util.List;
import com.google.gwt.maps.client.LoadApi;
import com.google.gwt.maps.client.LoadApi.LoadLibrary;
import com.google.gwt.user.client.Timer;

import ch.uzh.ifi.rerg.se16_climeter.client.Data;
import ch.uzh.ifi.rerg.se16_climeter.client.Visualisation;

/**
 * The class Map initializes a running map and returns it in a pane.
 * 
 * @author 		Alphonse Mariyagnanaseelan
 * @history 	2016-11-02 AM Initial Commit
 * 				2016-11-03 AM Map runs in a thread
 * 				2016-11-07 AM Added parameter dataSet to constructor
 * 				2016-11-14 AM Gray-map glitch fixed
 * 				2016-11-20 AM New class MapRunnable instead of anonym class
 * @version 	2016-11-20 AM 1.2
 * @responsibilities 
 * 				This class starts the map in a thread and passes the data 
 * 				to it [and has to pass filter details in future].
 */
public class Map extends Visualisation {
	
	private MapRunnable mapRunnable;
	private boolean sensor = true;
	
	private TemperatureOverlay activeTemperatureOverlay;
	private List<TemperatureOverlay> temperatureOverlays;
	
	/**
	 * Initializes the map and adds it to the visualisation-panel.
	 * @pre -
	 * @post panel != null
	 * @param dataSet Data objects which will be visualised on the map
	 */
	public Map(List<Data> dataSet) {
		this.temperatureOverlays = new ArrayList<TemperatureOverlay>();
		
		initMap();
		
		addTemperatureOverlay(dataSet, 2000);
		
		// testing temperatureOverlays
//		for (int i = 1; i < 10; i++) {
//			addTemperatureOverlay(Data.getRandomData(120), i * 5000);
//		}
//		
//		for (int i = 100; i < 200; i++) {
//			showTemperatureOverlay((i - 100) % 9, i * 500);
//		}
	}
	
	/**
	 * Initializes the map inside a thread.
	 * @pre -
	 * @post panel != null
	 */
	protected void initMap() {
		// load all the libraries for use in the maps
		ArrayList<LoadLibrary> loadLibraries = new ArrayList<LoadApi.LoadLibrary>();
		loadLibraries.add(LoadLibrary.ADSENSE);
		loadLibraries.add(LoadLibrary.DRAWING);
		loadLibraries.add(LoadLibrary.GEOMETRY);
		loadLibraries.add(LoadLibrary.PANORAMIO);
		loadLibraries.add(LoadLibrary.PLACES);
		loadLibraries.add(LoadLibrary.WEATHER);
		loadLibraries.add(LoadLibrary.VISUALIZATION);
		
		// thread with running map
		this.mapRunnable = new MapRunnable(this);
		
		// set key for google maps
		String keyParameter = "key=AIzaSyB4zRgy_BdYcjhDiMNv-kZboiLBCpmyYWs";
		LoadApi.go(this.mapRunnable, loadLibraries, sensor, keyParameter);
	}
	
	/**
	 * Add one temperature layer to the map.
	 * @pre -
	 * @post -
	 * @param dataSet a list of Data to display on the map
	 * @param delay the delay to wait before starting process
	 */
	public void addTemperatureOverlay(final List<Data> dataSet, int delay) {
		Timer t = new Timer() {
			@Override
			public void run() {
				TemperatureOverlay newTemperatureOverlay = mapRunnable.addTemperatureOverlay(dataSet);
				temperatureOverlays.add(newTemperatureOverlay);
				
				if (activeTemperatureOverlay != null) {
					activeTemperatureOverlay.setVisibility(false);
				}
				
				activeTemperatureOverlay = newTemperatureOverlay;
			}
		};
		t.schedule(delay);
	}
	
	public void showTemperatureOverlay(final int index, int delay) {
		Timer t = new Timer() {
			@Override
			public void run() {
				if (activeTemperatureOverlay != null) {
					activeTemperatureOverlay.setVisibility(false);
				}
				
				activeTemperatureOverlay = temperatureOverlays.get(index);
				activeTemperatureOverlay.setVisibility(true);
			}
		};
		t.schedule(delay);
	}
	
	/**
	 * Add a MapComposite object to the panel of Map.
	 * @pre -
	 * @post -
	 * @param mapComposite the mapComposite
	 */
	protected void addToPanel(MapComposite mapComposite) {
		this.panel.add(mapComposite);
	}
	
}
