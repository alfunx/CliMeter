/**
 * 
 */
package ch.uzh.ifi.rerg.se16_climeter.client.filtermenu;

import static org.junit.Assert.*;

import org.junit.Test;

import com.google.gwt.junit.client.GWTTestCase;

import ch.uzh.ifi.rerg.se16_climeter.client.Data;

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
		FilterMenu menu = new FilterMenu(Data.getRandomData(10));
		FilterMenu menu1 = null;
		
		assertNull(menu1);
		assertNotNull(menu);
		
	}

	

	
}