package ch.uzh.ifi.rerg.se16_climeter.client.map;

import java.util.List;
import com.google.gwt.user.client.Timer;

import ch.uzh.ifi.rerg.se16_climeter.client.Data;

public class MapRunnable implements Runnable {
	
	private Map map;
	private MapComposite mapComposite;
	
	public MapRunnable(Map map) {
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
	
	public TemperatureOverlay addTemperatureOverlay(List<Data> dataSet) {
		return this.mapComposite.addTemperatureOverlay(dataSet);
	}
	
}
