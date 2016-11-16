package ch.uzh.ifi.rerg.se16_climeter.client;

import static org.junit.Assert.*;
import org.junit.Test;
import com.google.gwt.junit.client.GWTTestCase;

public class MapTest extends GWTTestCase {
	
	@Override
	public String getModuleName() {
		return "ch.uzh.ifi.rerg.se16_climeter.CliMeter";
	}
	
	@Test
	public void testMap_null() {
		Map map = new Map(null);
		assertNotNull(map.getPanel());
	}
	
	@Test
	public void testMap_1() {
		Map map = new Map(Data.getRandomData(100));
		assertNotNull(map.getPanel());
	}
	
	@Test
	public void testGetPanel_null() {
		Map map = new Map(null);
		assertNotNull(map.getPanel());
	}
	
	@Test
	public void testGetPanel_1() {
		Map map = new Map(Data.getRandomData(100));
		assertNotNull(map.getPanel());
	}
	
}
