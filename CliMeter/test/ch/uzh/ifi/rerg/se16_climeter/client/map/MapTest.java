package ch.uzh.ifi.rerg.se16_climeter.client.map;

import static org.junit.Assert.*;
import org.junit.Test;
import com.google.gwt.junit.client.GWTTestCase;

import ch.uzh.ifi.rerg.se16_climeter.client.Data;

public class MapTest extends GWTTestCase {
	
	@Override
	public String getModuleName() {
		return "ch.uzh.ifi.rerg.se16_climeter.CliMeter";
	}
	
	@Test
	public void testMap_null() {
		Map map = new Map();
		assertNotNull(map.getPanel());
	}
	
	@Test
	public void testGetPanel_null() {
		Map map = new Map();
		assertNotNull(map.getPanel());
	}
	
}
