package ch.uzh.ifi.rerg.se16_climeter.client.filter;

import static org.junit.Assert.*;

import org.junit.Test;

import com.google.gwt.junit.client.GWTTestCase;

import ch.uzh.ifi.rerg.se16_climeter.client.filter.FilterMenu;
import ch.uzh.ifi.rerg.se16_climeter.client.table.Table;

/**
 * @author Joni
 *
 */
public class FilterMenuTest extends GWTTestCase {
	
	@Override
	public String getModuleName() {
		return "ch.uzh.ifi.rerg.se16_climeter.CliMeter";
	}
	
	
	@Test
	public void testFilterMenu() {
		FilterMenu menu = new FilterMenu(new Table());
		FilterMenu menu1 = null;
		
		assertNull(menu1);
		assertNotNull(menu);
	}
	
}
