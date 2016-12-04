package ch.uzh.ifi.rerg.se16_climeter.client;

import com.google.gwt.core.client.EntryPoint;

/**
 * @author 	Timo Surbeck
 * @history 2016-11-01 TS First Version
 * 			2016-11-14 TS restructured version 
 * @version	2016-11-14 TS 1.1
 * 
 * @responsibilities This class implements the entry-point method
 * 						called onModuleLoad.
 *                   
 */
public class CliMeter implements EntryPoint {

	private UserInterface ui;
	
	@Override

	/**
	 * Calls the createGUI method in ui (class UserInterface)
	 * @pre	-
	 * @post -
	 */
	public void onModuleLoad() {
		
		// override theme with custom css
		Resources.INSTANCE.css().ensureInjected(); 
		
		ui = new UserInterface();
		ui.createGUI();
		
	}
}
