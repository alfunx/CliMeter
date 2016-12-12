package ch.uzh.ifi.rerg.se16_climeter.client.map;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;
import com.google.gwt.junit.client.GWTTestCase;
import com.google.gwt.maps.client.LoadApi;
import com.google.gwt.maps.client.LoadApi.LoadLibrary;

import ch.uzh.ifi.rerg.se16_climeter.client.Data;

/**
 * The class DataPoint is one Data object displayed on the map.
 * 
 * @author 		Alphonse Mariyagnanaseelan
 * @responsibilities 
 * 				This class displays one data object on the map.
 */
public class DataPointTest extends GWTTestCase {

	@Override
	public String getModuleName() {
		return "ch.uzh.ifi.rerg.se16_climeter.CliMeter";
	}

	@Test
	public void testDataPoint_null() {
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
				MapComposite mapComposite = new MapComposite();
				DataPoint dataPoint = new DataPoint(mapComposite.getMapWidget(), new ColorTransition(-30, 30), Data.getRandomData(1).get(0));

				assertNotNull(dataPoint);

				// false positive
				assertTrue(false);
			}
		};

		LoadApi.go(mapThread, loadLibraries, true);
	}

}
