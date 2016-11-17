package ch.uzh.ifi.rerg.se16_climeter.client;

import static org.junit.Assert.*;
import org.junit.Test;
import com.google.gwt.junit.client.GWTTestCase;

public class ColorTest extends GWTTestCase {
	
	@Override
	public String getModuleName() {
		return "ch.uzh.ifi.rerg.se16_climeter.CliMeter";
	}
	
	@Test
	public void testGetHexString_1() {
		Color color = new Color(160, 82, 22);
		assertEquals("#a05216", color.getHexString());
	}
	
	@Test
	public void testGetHexString_2() {
		Color color = new Color(350, -80, 75);
		assertEquals("#ff004b", color.getHexString());
	}
	
	@Test
	public void testIntTo2Bytes_1() {
		Color color = new Color(0, 0, 0);
		assertEquals(255, color.intTo2Bytes(400));
	}
	
	@Test
	public void testIntTo2Bytes_2() {
		Color color = new Color(0, 0, 0);
		assertEquals(0, color.intTo2Bytes(-20));
	}
	
	@Test
	public void testIntTo2BytesStr_1() {
		Color color = new Color(0, 0, 0);
		assertEquals("09", color.intTo2BytesStr(9));
	}
	
	@Test
	public void testIntTo2BytesStr_2() {
		Color color = new Color(0, 0, 0);
		assertEquals("b0", color.intTo2BytesStr(176));
	}
	
	@Test
	public void testPadding_1() {
		Color color = new Color(0, 0, 0);
		assertEquals("04", color.padding("4"));
	}
	
	@Test
	public void testPadding_2() {
		Color color = new Color(0, 0, 0);
		assertEquals("6a", color.padding("6a"));
	}
	
}
