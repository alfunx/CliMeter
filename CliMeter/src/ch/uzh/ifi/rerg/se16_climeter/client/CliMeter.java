package ch.uzh.ifi.rerg.se16_climeter.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import java.util.LinkedList;
import java.util.logging.Logger;

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

	private GreetingServiceAsync greet = GWT.create(GreetingService.class);
	Logger log = null;
	Listener listener = null;

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
		
		//FUnktion um eine 	query Anfrage an die Datenbank zu liefern.
	    AsyncCallback<LinkedList<LinkedList<String>>> callback = new AsyncCallback<LinkedList<LinkedList<String>>>(){
	    	public void onFailure(Throwable caught) {
	    		log.warning("AsyncCallback failed: " + caught.getMessage());
	    	}
	    	public void onSuccess(LinkedList<LinkedList<String>> result) {
	    		// Eigentlich wäre es listener != null aber wurde für Testzwecke so umgeschrieben
	    		// Es würde eine LinkedList<LinkedList<String>> übergeben werden -> onQuerySuccess -> query
	    		if(listener == null){
	    			listener.onQuerySuccess(result);
	    			Console.log("Success");
	    		}
	    	}	
	    };
	    // "Select * from primaryTable" liefert alles aus der Datenbank
	    // Mit "Select * from primaryTable where Country = Switzerland" zeigt nur bestimmte Daten an (hier Swizerland)
		greet.getData("select * from primaryTable", callback);
		
		ui = new UserInterface();
		ui.createGUI();
		
	}
	
}
