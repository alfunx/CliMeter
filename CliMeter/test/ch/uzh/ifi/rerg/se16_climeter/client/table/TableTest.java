/**
 * 
 */
package ch.uzh.ifi.rerg.se16_climeter.client.table;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import com.google.gwt.junit.client.GWTTestCase;

import ch.uzh.ifi.rerg.se16_climeter.client.Data;


/** JUnit test for class Table
 * @author Jonathan Stahl
 * @history 2016-11-14 initial commit
 * @version 2016-11-14 1.0
 * @responsibilities This JUnit class tests all methods of the class Table
 *
 */
public class TableTest extends GWTTestCase {
	
	@Override
	public String getModuleName() {
		return "ch.uzh.ifi.rerg.se16_climeter.CliMeter";
	}
	

	@Test
	public void testTableConstructor() {
		Table table = new Table();
		Table table1 = null;
		
		// tests if table is initialized
		assertNotNull(table);
		assertNull(table1);
	}
	
	@Test
	public void testAddData() {
		
		
	}

	

}
