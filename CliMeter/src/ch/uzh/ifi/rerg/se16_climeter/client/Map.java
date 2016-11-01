package ch.uzh.ifi.rerg.se16_climeter.client;

import java.util.ArrayList;

import com.google.gwt.maps.client.LoadApi;
import com.google.gwt.maps.client.LoadApi.LoadLibrary;
import com.google.gwt.user.client.ui.LayoutPanel;

public class Map {
	
	LayoutPanel panel = new LayoutPanel();
	
	public Map() {
		//super();
		loadMapApi();
	}
	
	private void loadMapApi() {
		boolean sensor = true;
		
		// load all the libs for use in the maps
		ArrayList<LoadLibrary> loadLibraries = new ArrayList<LoadApi.LoadLibrary>();
		loadLibraries.add(LoadLibrary.ADSENSE);
		loadLibraries.add(LoadLibrary.DRAWING);
		loadLibraries.add(LoadLibrary.GEOMETRY);
		loadLibraries.add(LoadLibrary.PANORAMIO);
		loadLibraries.add(LoadLibrary.PLACES);
		loadLibraries.add(LoadLibrary.WEATHER);
		loadLibraries.add(LoadLibrary.VISUALIZATION);
		
		Runnable onLoad = new Runnable() {
			@Override
			public void run() {
				SimpleMap map = new SimpleMap();
				panel.add(map);
			}
		};
		
		LoadApi.go(onLoad, loadLibraries, sensor);
	}
	
	/**
	 * @return the panel
	 */
	public LayoutPanel getPanel() {
		return panel;
	}
	
}
