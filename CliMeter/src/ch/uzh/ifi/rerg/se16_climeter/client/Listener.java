package ch.uzh.ifi.rerg.se16_climeter.client;

import java.util.LinkedList;
/**
 * @author Christian Himmel
 *	Gives a message if there are results from the queries.
 */
public interface Listener {
	public void onQuerySuccess(LinkedList<LinkedList<String>> result);
}
