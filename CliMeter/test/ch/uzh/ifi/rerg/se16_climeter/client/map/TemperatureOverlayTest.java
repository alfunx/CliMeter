package ch.uzh.ifi.rerg.se16_climeter.client.map;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;
import com.google.gwt.junit.client.GWTTestCase;
import com.google.gwt.maps.client.LoadApi;
import com.google.gwt.maps.client.LoadApi.LoadLibrary;

import ch.uzh.ifi.rerg.se16_climeter.client.Data;

public class TemperatureOverlayTest extends GWTTestCase {

	@Override
	public String getModuleName() {
		return "ch.uzh.ifi.rerg.se16_climeter.CliMeter";
	}

	@Test
	public void testTemperatureOverlay_null() {
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
				TemperatureOverlay temperatureOverlay = new TemperatureOverlay(mapComposite.getMapWidget(), new ColorTransition(-30, 30), Data.getRandomData(100), false);
				assertNotNull(temperatureOverlay);
			}
		};

		LoadApi.go(mapThread, loadLibraries, true);
	}

	@Test
	public void testTemperatureOverlay_1() {
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
				TemperatureOverlay temperatureOverlay = new TemperatureOverlay(mapComposite.getMapWidget(), new ColorTransition(-30, 30), Data.getRandomData(100), false);
				assertEquals(100, temperatureOverlay.getDataPoints().size());
			}
		};

		LoadApi.go(mapThread, loadLibraries, true);
	}

}
