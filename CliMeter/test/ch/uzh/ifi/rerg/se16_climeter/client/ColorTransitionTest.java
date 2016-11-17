package ch.uzh.ifi.rerg.se16_climeter.client;

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
		ColorTransition colorTransition = new ColorTransition(new Color(12, 34, 56), null);
		assertNotNull(colorTransition.getMinColor());
		assertNotNull(colorTransition.getMaxColor());
		assertEquals(new Color(12, 34, 56).getHexString(), colorTransition.getMinColor().getHexString());
		assertEquals(new Color(0, 0, 0).getHexString(), colorTransition.getMaxColor().getHexString());
	}
	
	@Test
	public void testColorTransition_1() {
		ColorTransition colorTransition = new ColorTransition();
		assertNotNull(colorTransition.getMinColor());
		assertNotNull(colorTransition.getMaxColor());
		assertEquals(new Color(70, 70, 255).getHexString(), colorTransition.getMinColor().getHexString());
		assertEquals(new Color(255, 70, 70).getHexString(), colorTransition.getMaxColor().getHexString());
	}
	
	@Test
	public void testGetPercentageColor_1() {
		ColorTransition colorTransition = new ColorTransition(new Color(0, 0, 0), new Color(255, 255, 255));
		Color color = colorTransition.getPercentageColor(10, -10, 30);
		assertEquals("#808080", color.getHexString());
	}
	
	@Test
	public void testGetPercentageColor_2() {
		ColorTransition colorTransition = new ColorTransition();
		Color color = colorTransition.getPercentageColor(-10, -75, 95);
		assertEquals("#8d46b8", color.getHexString());
	}
	
	@Test
	public void testNormalize_1() {
		ColorTransition colorTransition = new ColorTransition();
		assertEquals(0.5, colorTransition.normalize(25, 0, 50));
	}
	
	@Test
	public void testNormalize_2() {
		ColorTransition colorTransition = new ColorTransition();
		assertEquals(0.75, colorTransition.normalize(-25, -100, 0));
	}
	
	@Test
	public void testNormalize_3() {
		ColorTransition colorTransition = new ColorTransition();
		assertEquals(0.0, colorTransition.normalize(1, 30, 50));
	}
	
	@Test
	public void testNormalize_4() {
		ColorTransition colorTransition = new ColorTransition();
		assertEquals(1.0 / 3.0, colorTransition.normalize(30, 50, 20));
	}
	
	@Test
	public void testNormalize_5() {
		ColorTransition colorTransition = new ColorTransition();
		Throwable exception = null;
		
		try {
			colorTransition.normalize(20, 20, 20);
		} catch (Throwable e) {
			exception = e;
		}
		assertTrue(exception instanceof IllegalArgumentException);
	}
	
}
