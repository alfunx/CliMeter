/**
 * 
 */
package ch.uzh.ifi.rerg.se16_climeter.client;

import static org.junit.Assert.*;

import org.junit.Test;

import com.google.gwt.junit.client.GWTTestCase;

/**
 * @author Jonathan Stahl
 *
 */
public class TableTest extends GWTTestCase {
	
	@Override
	public String getModuleName() {
		return "ch.uzh.ifi.rerg.se16_climeter.CliMeter";
	}
	

	@Test
	public void testTable() {
		Table table = new Table(Data.getRandomData(100));
		Table table1 = null;
		
		assertNotNull(table);
		assertNull(table1);
	}

	
	@Test
	public void testAddData() {
		
	}

	

}
