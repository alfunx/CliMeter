package ch.uzh.ifi.rerg.se16_climeter.client;

/**
 * The interface Exportable must be implemented by classes which have an 
 * export function.
 * 
 * @author 		Alphonse Mariyagnanaseelan
 * @history 	2016-11-02 AM Initial Commit
 * @version 	2016-11-02 AM 1.0
 * @responsibilities 
 * 				This interface makes sure an exportable class implements 
 * 				the method export().
 */
public interface Exportable {
	
	/**
	 * Export (filtered) dataset or visualisations to filesystem.
	 * @throws Exception if export didn't work
	 */
	public void export() throws Exception;
	
}
