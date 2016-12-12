package ch.uzh.ifi.rerg.se16_climeter.client.map;

import static org.junit.Assert.*;

import org.junit.Test;
import com.google.gwt.junit.client.GWTTestCase;

/**
 * The class Map initializes a running map and returns it in a pane.
 * 
 * @author 		Alphonse Mariyagnanaseelan
 * @responsibilities 
 * 				This class starts the map in a thread.
 */
public class MapTest extends GWTTestCase {

	@Override
	public String getModuleName() {
		return "ch.uzh.ifi.rerg.se16_climeter.CliMeter";
	}

	@Test
	public void testMap_null() {
		Map map = new Map();
		assertNotNull(map);
	}

	@Test
	public void testGetPanel_null() {
		Map map = new Map();
		assertNotNull(map.getPanel());
	}

}
