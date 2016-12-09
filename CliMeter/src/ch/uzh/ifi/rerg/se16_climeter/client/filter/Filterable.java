package ch.uzh.ifi.rerg.se16_climeter.client.filter;

/**
 * The interface Filterable allows a filtering object to select which 
 * items should be displayed.
 * 
 * @author 		Alphonse Mariyagnanaseelan
 * @history 	2016-12-03 AM Initial Commit
 * 				2016-12-06 AM New structure
 * @version 	2016-12-06 AM 1.1
 * @responsibilities 
 * 				This interface makes sure specific visualisations can be 
 * 				updated by filtering objects.
 */
public interface Filterable {

	/**
	 * Apply the given filter to the visualisation.
	 * @pre -
	 * @post -
	 */
	public void apply(Filter filter);

	/**
	 * Return old filter, to rewrite with new information.
	 * @pre -
	 * @post -
	 */
	public Filter getOldFilter();

}
