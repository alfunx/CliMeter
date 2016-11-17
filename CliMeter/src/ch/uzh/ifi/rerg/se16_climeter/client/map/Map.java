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
 * @version 	2016-11-14 AM 1.0
 * @responsibilities 
 * 				This class starts the map in a thread and passes the data 
 * 				to it [and has to pass filter details in future].
 */
public class Map extends Visualisation {
	
	private List<Data> dataSet;
	private MapComposite mapComposite;
	private boolean sensor = true;
	
	/**
	 * Initializes the map and adds it to the visualisation-panel.
	 * @pre -
	 * @post panel != null
	 * @param dataSet Data objects which will be visualised on the map
	 */
	public Map(List<Data> dataSet) {
		this.dataSet = dataSet;
		this.initMap();
	}
	
	/**
	 * Initializes the map inside a thread.
	 * @pre -
	 * @post panel != null
	 */
	private void initMap() {
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
		Runnable onLoad = new Runnable() {
			@Override
			public void run() {
				mapComposite = new MapComposite(dataSet);
				panel.add(mapComposite);
				
				// workaround to fix a glitch, where the map occasionally stays gray
				Timer timer = new Timer() {
					@Override
					public void run() {
						mapComposite.getMapWidget().triggerResize();
					}
				};
				timer.schedule(1);
			}
		};
		
		String keyParameter = "key=AIzaSyB4zRgy_BdYcjhDiMNv-kZboiLBCpmyYWs";
		LoadApi.go(onLoad, loadLibraries, sensor, keyParameter);
	}
	
}
