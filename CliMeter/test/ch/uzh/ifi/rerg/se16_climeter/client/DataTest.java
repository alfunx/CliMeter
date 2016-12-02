package ch.uzh.ifi.rerg.se16_climeter.client;

import static org.junit.Assert.*;
import org.junit.Test;
import com.google.gwt.junit.client.GWTTestCase;

public class DataTest extends GWTTestCase {

	@Override
	public String getModuleName() {
		return "ch.uzh.ifi.rerg.se16_climeter.CliMeter";
	}

	@Test
	public void testGetLatLng_null() {
		Data data = new Data();
		assertEquals(data.getLatLng().getLatitude(), data.getLatitude());
		assertEquals(data.getLatLng().getLongitude(), data.getLongitude());
	}

	@Test
	public void testGetLatLng_1() {
		Data data = new Data();
		data.setLatitude(-150.0);
		data.setLongitude(199.7);
		assertEquals(data.getLatLng().getLatitude(), -150.0);
		assertEquals(data.getLatLng().getLongitude(), 199.7);
	}

}
