package ch.uzh.ifi.rerg.se16_climeter.client;

/**
 * The interface Filterable allows a filtering object to select which 
 * items should be displayed.
 * 
 * @author 		Alphonse Mariyagnanaseelan
 * @history 	2016-12-03 AM Initial Commit
 * @version 	2016-12-03 AM 1.0
 * @responsibilities 
 * 				This interface makes sure specific visualisations can be 
 * 				filtered.
 */
public interface Filterable {

	/**
	 * Apply the given filter to the visualisation.
	 * @param filter the filter
	 */
	public void apply(Filter filter);

}
