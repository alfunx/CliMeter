package ch.uzh.ifi.rerg.se16_climeter.server;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;
import com.google.gwt.junit.client.GWTTestCase;

import ch.uzh.ifi.rerg.se16_climeter.client.SQL;
import ch.uzh.ifi.rerg.se16_climeter.client.filter.Filter;

public class SQLConnectorTest extends GWTTestCase {

	@Override
	public String getModuleName() {
		return "ch.uzh.ifi.rerg.se16_climeter.CliMeter";
	}

	@Test
	public void testGetQuery_1() {
		Filter filter = new Filter();
		filter.setCity("Zurich");
		filter.setCountry("Switzerland");
		filter.setMaxUncertainty(2.33F);
		filter.setBeginDate(new Date(10, 2, 2006 - 1900));
		filter.setEndDate(new Date(31, 5, 2013 - 1900));
		filter.setGroupByYear(true);

		SQL sql = new SQL();
//		sql.g
		
//		assertEquals("", sql.ge);
	}

}
