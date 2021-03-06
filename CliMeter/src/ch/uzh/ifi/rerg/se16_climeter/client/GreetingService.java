package ch.uzh.ifi.rerg.se16_climeter.client;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import ch.uzh.ifi.rerg.se16_climeter.client.filter.Filter;

/**
 * The client-side stub for the RPC service.
 */
@RemoteServiceRelativePath("greet")
public interface GreetingService extends RemoteService {
	public ArrayList<Data> getData(Filter filter);
	public ArrayList<Data> getMapData(Filter filter);
	public ArrayList<String> getDistinctCity();
	public ArrayList<String> getDistinctCountry();
}
