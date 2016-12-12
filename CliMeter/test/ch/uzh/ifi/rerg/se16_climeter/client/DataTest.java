package ch.uzh.ifi.rerg.se16_climeter.client;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;
import com.google.gwt.junit.client.GWTTestCase;
import com.google.gwt.maps.client.LoadApi;
import com.google.gwt.maps.client.LoadApi.LoadLibrary;
import com.google.gwt.maps.client.base.LatLng;

import ch.uzh.ifi.rerg.se16_climeter.client.map.MapComposite;

public class DataTest extends GWTTestCase {

	@Override
	public String getModuleName() {
		return "ch.uzh.ifi.rerg.se16_climeter.CliMeter";
	}

	@Test
	public void testGetLatLng_null() {
		final Data data = new Data();
		data.setLatitude("150.0S");
		data.setLongitude("199.7E");

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
				MapComposite mapComposite = new MapComposite();

				LatLng latLng = data.getLatLng();
				assertEquals(data.getLatitude(), latLng.getLatitude());
				assertEquals(data.getLongitude(), latLng.getLongitude());

				// false positive!
				assertTrue(false);
			}
		};

		LoadApi.go(onLoad, loadLibraries, true);
	}

	@Test
	public void testGetLatLng_1() {
		Data data = new Data();
		data.setLatitude("150.0S");
		data.setLongitude("199.7E");
		assertEquals(-150.0, data.getLatitude());
		assertEquals(199.7, data.getLongitude());
	}

}
