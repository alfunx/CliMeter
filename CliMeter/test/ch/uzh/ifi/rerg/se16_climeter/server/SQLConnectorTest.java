package ch.uzh.ifi.rerg.se16_climeter.server;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;
import com.google.gwt.junit.client.GWTTestCase;

import ch.uzh.ifi.rerg.se16_climeter.client.filter.Filter;

/**
 * The class MapComposite is a concrete Map, load into a Composite object.
 * 
 * @author 		Christian Himmel
 * @responsibilities 
 * 				This class creates a connection between the project and the database 
 * 				and converts the results into a LinkedList.
 */
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

		SQLConnector sql = new SQLConnector();
		
		assertEquals("SELECT * FROM primaryTable"
				+ " WHERE AverageTemperatureUncertainty<='" + 2.33F + "'"
				+ " AND dt>='" + "10-03-2006" + "'"
				+ " AND dt<='" + "31-06-2013" + "'"
				+ " AND Country='" + "Zurich" + "'"
				+ " AND City='" + "Switzerland" + "'"
				+ " GROUP BY YEAR(dt), City;", sql.getQuery(filter, "*"));
	}

}
