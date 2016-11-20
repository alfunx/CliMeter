package ch.uzh.ifi.rerg.se16_climeter.client.map;

import java.util.List;
import com.google.gwt.user.client.Timer;

import ch.uzh.ifi.rerg.se16_climeter.client.Data;

/**
 * The class MapRunnable is a thread with a running MapComposite.
 * 
 * @author 		Alphonse Mariyagnanaseelan
 * @history 	2016-11-20 AM Initial Commit
 * @version 	2016-11-20 AM 1.0
 * @responsibilities 
 * 				This class contains the map and all layers on top of it.
 */
public class MapRunnable implements Runnable {
	
	private Map map;
	private MapComposite mapComposite;
	
	/**
	 * Start thread with a MapComposite.
	 * @pre -
	 * @post -
	 * @param map the Map object, in which the mapComposite should be displayed
	 */
	public MapRunnable(Map map) {
		super();
		this.map = map;
	}
	
	@Override
	public void run() {
		this.mapComposite = new MapComposite(this.map);
		this.map.addToPanel(this.mapComposite);
		
		// workaround to fix a glitch, where the map occasionally stays gray
		Timer timer = new Timer() {
			@Override
			public void run() {
				mapComposite.getMapWidget().triggerResize();
			}
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
	public TemperatureOverlay addTemperatureOverlay(List<Data> dataSet) {
		return this.mapComposite.addTemperatureOverlay(dataSet);
	}
	
}
