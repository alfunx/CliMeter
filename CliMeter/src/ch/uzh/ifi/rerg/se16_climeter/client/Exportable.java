package ch.uzh.ifi.rerg.se16_climeter.client;

public interface Exportable {
	
	/**
	 * Export (filtered) dataset or visualisations to filesystem.
	 * @throws Exception if export didn't work
	 */
	public void export() throws Exception;
	
}
