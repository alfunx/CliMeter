package ch.uzh.ifi.rerg.se16_climeter.client.filter;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;
import com.google.gwt.junit.client.GWTTestCase;
import com.google.gwt.maps.client.LoadApi;
import com.google.gwt.maps.client.LoadApi.LoadLibrary;

import ch.uzh.ifi.rerg.se16_climeter.client.map.MapComposite;

public class TimeLineTest extends GWTTestCase {

	@Override
	public String getModuleName() {
		return "ch.uzh.ifi.rerg.se16_climeter.CliMeter";
	}

	@Test
	public void testTimeLine_null() {
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
				TimeLine timeLine = new TimeLine(1800, 2016, mapComposite);
				assertNotNull(timeLine);
			}
		};

		LoadApi.go(onLoad, loadLibraries, true);
	}

	@Test
	public void testTimeLine_1() {
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
				TimeLine timeLine = new TimeLine(1800, 2016, mapComposite);
				assertEquals(1800.0, timeLine.getMinValue());
				assertEquals(2016.0, timeLine.getMaxValue());
			}
		};

		LoadApi.go(onLoad, loadLibraries, true);
	}

}
