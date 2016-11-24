package ch.uzh.ifi.rerg.se16_climeter.client.map;

import static org.junit.Assert.*;
import org.junit.Test;
import com.google.gwt.junit.client.GWTTestCase;

public class ColorTransitionTest extends GWTTestCase {
	
	@Override
	public String getModuleName() {
		return "ch.uzh.ifi.rerg.se16_climeter.CliMeter";
	}
	
	@Test
	public void testColorTransition_null() {
		ColorTransition colorTransition = new ColorTransition(null, -30.0, 30.0);
		assertEquals(new Color(0, 255, 0).getHexString(), colorTransition.getColor()[0].getHexString());
		assertEquals(new Color(255, 255, 0).getHexString(), colorTransition.getColor()[1].getHexString());
		assertEquals(new Color(255, 0, 0).getHexString(), colorTransition.getColor()[2].getHexString());
	}
	
	@Test
	public void testColorTransition_1() {
		ColorTransition colorTransition = new ColorTransition(-30.0, 30.0);
		assertEquals(new Color(0, 255, 0).getHexString(), colorTransition.getColor()[0].getHexString());
		assertEquals(new Color(255, 255, 0).getHexString(), colorTransition.getColor()[1].getHexString());
		assertEquals(new Color(255, 0, 0).getHexString(), colorTransition.getColor()[2].getHexString());
	}
	
	@Test
	public void testGetPercentageColor_1() {
		Color[] c = {new Color(0, 0, 0), new Color(255, 255, 255)};
		ColorTransition colorTransition = new ColorTransition(c, -10.0, 30.0);
		Color color = colorTransition.getPercentageColor(10);
		assertEquals("#808080", color.getHexString());
	}
	
	@Test
	public void testGetPercentageColor_2() {
		Color[] c = {new Color(70, 70, 255), new Color(255, 70, 70)};
		ColorTransition colorTransition = new ColorTransition(c, -75.0, 95.0);
		Color color = colorTransition.getPercentageColor(-10);
		assertEquals("#8d46b8", color.getHexString());
	}
	
	@Test
	public void testNormalize_1() {
		Color[] c = {new Color(0, 0, 0), new Color(255, 255, 255)};
		ColorTransition colorTransition = new ColorTransition(c, 0.0, 50.0);
		assertEquals(0.5, colorTransition.normalize(25, 0, 50));
	}
	
	@Test
	public void testNormalize_2() {
		Color[] c = {new Color(0, 0, 0), new Color(255, 255, 255)};
		ColorTransition colorTransition = new ColorTransition(c, -100.0, 0.0);
		assertEquals(0.75, colorTransition.normalize(-25, -100, 0));
	}
	
	@Test
	public void testNormalize_3() {
		Color[] c = {new Color(0, 0, 0), new Color(255, 255, 255)};
		ColorTransition colorTransition = new ColorTransition(c, 30.0, 50.0);
		assertEquals(0.0, colorTransition.normalize(1, 30, 50));
	}
	
	@Test
	public void testNormalize_4() {
		Color[] c = {new Color(0, 0, 0), new Color(255, 255, 255)};
		ColorTransition colorTransition = new ColorTransition(c, 50.0, 20.0);
		assertEquals(1.0 / 3.0, colorTransition.normalize(30, 50, 20));
	}
	
	@Test
	public void testNormalize_5() {
		Color[] c = {new Color(0, 0, 0), new Color(255, 255, 255)};
		ColorTransition colorTransition = new ColorTransition(c, 20.0, 20.0);
		Throwable exception = null;
		
		try {
			colorTransition.normalize(20, 20, 20);
		} catch (Throwable e) {
			exception = e;
		}
		assertTrue(exception instanceof IllegalArgumentException);
	}
	
}
