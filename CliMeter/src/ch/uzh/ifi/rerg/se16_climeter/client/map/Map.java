package ch.uzh.ifi.rerg.se16_climeter.client.map;

import java.util.ArrayList;

import com.google.gwt.maps.client.LoadApi;
import com.google.gwt.maps.client.LoadApi.LoadLibrary;
import com.google.gwt.user.client.Timer;

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
 * 				2016-11-23 AM Completely restructured, ready for TimeLine
 * @version 	2016-11-23 AM 1.3
 * @responsibilities 
 * 				This class starts the map in a thread.
 */
public class Map extends Visualisation {
	
	private final boolean SENSOR = true;
	
	/**
	 * Initializes the map and adds it to the visualisation-panel.
	 * @pre -
	 * @post panel != null
	 * @param dataSet Data objects which will be visualised on the map
	 */
	public Map() {
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
		Runnable mapThread = new Runnable() {
			@Override
			public void run() {
				final MapComposite mapComposite = new MapComposite();
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
		
		// set key for google maps
		String keyParameter = "key=AIzaSyB4zRgy_BdYcjhDiMNv-kZboiLBCpmyYWs";
		LoadApi.go(mapThread, loadLibraries, this.SENSOR, keyParameter);
	}
	
}
