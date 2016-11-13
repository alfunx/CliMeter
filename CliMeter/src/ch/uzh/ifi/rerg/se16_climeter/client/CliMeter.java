package ch.uzh.ifi.rerg.se16_climeter.client;

import com.google.gwt.core.client.EntryPoint;

/**
 * @author 	Timo Surbeck
 * @history 2016-11-01 TS First Version 
 * @version	2016-11-01 TS 1.0
 * 
 * @responsibilities This class implements the entry-point method
 * 						called onModuleLoad.
 *                   
 */
public class CliMeter implements EntryPoint {

	
	UserInterface ui = new UserInterface();
	
	@Override

	/**
	 * Calls the createGUI method in ui (class UserInterface)
	 * @pre	There exists an UserInterface object called ui.
	 * @post - 
	 */
	public void onModuleLoad() {
		
		// override theme with custom css
		Resources.INSTANCE.css().ensureInjected(); 
		
		ui.createGUI();
		
	}
	
}
