package ch.uzh.ifi.rerg.se16_climeter.client;

import java.util.LinkedList;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client-side stub for the RPC service.
 */
@RemoteServiceRelativePath("greet")
public interface GreetingService extends RemoteService {
	// Oder mit get Data(String string) wenn mit query gearbeitet wird.
	LinkedList<LinkedList<String>> getData(String string);
}