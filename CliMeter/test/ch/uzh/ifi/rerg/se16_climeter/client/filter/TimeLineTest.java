package ch.uzh.ifi.rerg.se16_climeter.client.filter;

import static org.junit.Assert.*;

import org.junit.Test;
import com.google.gwt.junit.client.GWTTestCase;

/**
 * The class TimeLine extends the SliderBar class
 * and creates a SliderBar object which is used
 * in the MapComposite class.
 * 
 * @author 	Timo Surbeck
 * @responsibilities 
 */
public class TimeLineTest extends GWTTestCase {

	@Override
	public String getModuleName() {
		return "ch.uzh.ifi.rerg.se16_climeter.CliMeter";
	}

	@Test
	public void testTimeLine_null() {
		Filterable filterable = new Filterable() {
			@Override
			public void apply(Filter filter) {
				// do something
			}
			@Override
			public Filter getOldFilter() {
				return new Filter();
			}
		};

		TimeLine timeLine = new TimeLine(filterable, 1800, 2016);
		assertNotNull(timeLine);
	}

	@Test
	public void testTimeLine_1() {
		Filterable filterable = new Filterable() {
			@Override
			public void apply(Filter filter) {
				// do something
			}
			@Override
			public Filter getOldFilter() {
				return new Filter();
			}
		};

		TimeLine timeLine = new TimeLine(filterable, 1800, 2016);
		assertEquals(1800.0, timeLine.getMinValue());
		assertEquals(2016.0, timeLine.getMaxValue());
	}

}
