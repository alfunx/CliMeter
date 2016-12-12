package ch.uzh.ifi.rerg.se16_climeter.client.filter;

import static org.junit.Assert.*;

import org.junit.Test;

import com.google.gwt.junit.client.GWTTestCase;

import ch.uzh.ifi.rerg.se16_climeter.client.filter.FilterMenu;
import ch.uzh.ifi.rerg.se16_climeter.client.table.Table;

/**
 * The class FilterMenu includes the different widgets with which the date can be filtered.
 * 
 * @author 		Joachim Baumann
 * @responsibilities 
 * 				This class inherits from the class Visualisation.
 */
public class FilterMenuTest extends GWTTestCase {
	
	@Override
	public String getModuleName() {
		return "ch.uzh.ifi.rerg.se16_climeter.CliMeter";
	}
	
	
	@Test
	public void testFilterMenu() {
		FilterMenu menu = new FilterMenu(new Table(), true);
		FilterMenu menu1 = null;
		
		assertNull(menu1);
		assertNotNull(menu);
	}
	
}
