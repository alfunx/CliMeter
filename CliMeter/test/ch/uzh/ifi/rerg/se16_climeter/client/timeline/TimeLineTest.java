package ch.uzh.ifi.rerg.se16_climeter.client.timeline;

import static org.junit.Assert.*;
import org.junit.Test;
import com.google.gwt.junit.client.GWTTestCase;

public class TimeLineTest extends GWTTestCase {

	@Override
	public String getModuleName() {
		return "ch.uzh.ifi.rerg.se16_climeter.CliMeter";
	}

	@Test
	public void testTimeLine_null() {
		TimeLine timeLine = new TimeLine(1800, 2016);
		assertNotNull(timeLine);
	}

	@Test
	public void testTimeLine_1() {
		TimeLine timeLine = new TimeLine(1800, 2016);
		assertEquals(1800.0, timeLine.getMinValue());
		assertEquals(2016.0, timeLine.getMaxValue());
	}

}
