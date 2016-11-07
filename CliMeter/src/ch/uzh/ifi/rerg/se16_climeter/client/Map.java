package ch.uzh.ifi.rerg.se16_climeter.client;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import com.google.gwt.maps.client.LoadApi;
import com.google.gwt.maps.client.LoadApi.LoadLibrary;

public class Map extends Visualisation {
	
	private List<Data> dataSet;
	
	public Map(List<Data> dataSet) {
		this.dataSet = dataSet;
		initMap();
	}
	
	public Map() {
		this.dataSet = new ArrayList<Data>();
		addRandomData();
		initMap();
	}
	
	public void addRandomData() {
		Data d = new Data();
		d.setAverageTemperature(88.888);
		d.setCity("Zurich");
		d.setCountry("Switzerland");
		d.setDate(new Date());
		d.setLatitude(47.37174);
		d.setLongitude(8.54226);
		d.setUncertainty(1.5);
		this.dataSet.add(d);
		
		Random r = new Random();
		for(int i = 0; i < 100; i++) {
			d = new Data();
			d.setAverageTemperature((int) ((r.nextDouble() * 2 - 1) * 30));
			d.setLatitude((r.nextDouble() * 2 - 1) * 100);
			d.setLongitude((r.nextDouble() * 2 - 1) * 100);
			this.dataSet.add(d);
		}
	}
	
	private void initMap() {
		boolean sensor = true;
		
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
				MapComposite map = new MapComposite(dataSet);
				panel.add(map);
			}
		};
		
		LoadApi.go(onLoad, loadLibraries, sensor);
	}
	
}
