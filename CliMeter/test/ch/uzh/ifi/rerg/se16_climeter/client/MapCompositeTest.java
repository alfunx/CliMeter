package ch.uzh.ifi.rerg.se16_climeter.client;

import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.Test;
import com.google.gwt.junit.client.GWTTestCase;
import com.google.gwt.maps.client.LoadApi;
import com.google.gwt.maps.client.LoadApi.LoadLibrary;

public class MapCompositeTest extends GWTTestCase {
	
	private boolean sensor;
	private ArrayList<LoadLibrary> loadLibraries;
		
	@Override
	public String getModuleName() {
		return "ch.uzh.ifi.rerg.se16_climeter.CliMeter";
	}
	
	@Test
	public void testMapComposite0() {
		init();
		
		// thread with running map
		Runnable onLoad = new Runnable() {
			@Override
			public void run() {
				final MapComposite mapComposite = new MapComposite(null);
				assertNotNull(mapComposite);
			}
		};
		
		LoadApi.go(onLoad, loadLibraries, sensor);
	}
	
	@Test
	public void testMapComposite1() {
		init();
		
		// thread with running map
		Runnable onLoad = new Runnable() {
			@Override
			public void run() {
				final MapComposite mapComposite = new MapComposite(Data.getRandomData(100));
				assertNotNull(mapComposite);
			}
		};
		
		LoadApi.go(onLoad, loadLibraries, sensor);
	}
	
	@Test
	public void testGetMapWidget0() {
		init();
		
		// thread with running map
		Runnable onLoad = new Runnable() {
			@Override
			public void run() {
				final MapComposite mapComposite = new MapComposite(null);
				assertNotNull(mapComposite.getMapWidget());
			}
		};
		
		LoadApi.go(onLoad, loadLibraries, sensor);
	}
	
	@Test
	public void testGetMapWidget1() {
		init();
		
		// thread with running map
		Runnable onLoad = new Runnable() {
			@Override
			public void run() {
				final MapComposite mapComposite = new MapComposite(Data.getRandomData(100));
				assertNotNull(mapComposite.getMapWidget());
			}
		};
		
		LoadApi.go(onLoad, loadLibraries, sensor);
	}
	
	public void init() {
		sensor = true;
		
		// load all the libraries for use in the maps
		this.loadLibraries = new ArrayList<LoadApi.LoadLibrary>();
		this.loadLibraries.add(LoadLibrary.ADSENSE);
		this.loadLibraries.add(LoadLibrary.DRAWING);
		this.loadLibraries.add(LoadLibrary.GEOMETRY);
		this.loadLibraries.add(LoadLibrary.PANORAMIO);
		this.loadLibraries.add(LoadLibrary.PLACES);
		this.loadLibraries.add(LoadLibrary.WEATHER);
		this.loadLibraries.add(LoadLibrary.VISUALIZATION);
	}
	
}
