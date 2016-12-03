package ch.uzh.ifi.rerg.se16_climeter.client;

import static org.junit.Assert.*;
import org.junit.Test;
import com.google.gwt.junit.client.GWTTestCase;
import com.google.gwt.maps.client.base.LatLng;

public class DataTest extends GWTTestCase {

	@Override
	public String getModuleName() {
		return "ch.uzh.ifi.rerg.se16_climeter.CliMeter";
	}

	@Test
	public void testGetLatLng_null() {
		Data data = new Data();
		data.setLatitude("150.0S");
		data.setLongitude("199.7E");

		LatLng latLng = data.getLatLng();
		assertEquals(data.getLatitude(), latLng.getLatitude());
		assertEquals(data.getLongitude(), latLng.getLongitude());
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
