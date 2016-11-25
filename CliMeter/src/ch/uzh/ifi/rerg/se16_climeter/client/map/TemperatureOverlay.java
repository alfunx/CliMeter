package ch.uzh.ifi.rerg.se16_climeter.client.map;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.maps.client.MapWidget;

import ch.uzh.ifi.rerg.se16_climeter.client.Data;

/**
 * The class TemperatureOverlay contains DataPoints for one point in 
 * time.
 * 
 * @author 		Alphonse Mariyagnanaseelan
 * @history 	2016-11-20 AM Initial Commit
 * @version 	2016-11-20 AM 1.0
 * @responsibilities 
 * 				This class displays DataPoints for one point in time.
 */
public class TemperatureOverlay {
	
	private MapWidget mapWidget;
	private ColorTransition colorTransition;
	private List<Data> dataSet;
	
	private List<DataPoint> dataPoints;
	
	/**
	 * Generate DataPoint objects for a list of Data.
	 * @pre -
	 * @post -
	 * @param mapWidget the MapWidget, on which the DataPoints should be displayed
	 * @param colorTransition the ColorTransition to choose a color for a DataPoint object
	 * @param dataSet list of all Data objects that should be displayed
	 */
	public TemperatureOverlay(MapWidget mapWidget, ColorTransition colorTransition, List<Data> dataSet) {
		this.mapWidget = mapWidget;
		this.colorTransition = colorTransition;
		this.dataSet = dataSet;
		this.dataPoints = new ArrayList<DataPoint>();
		
		initTemperatureOverlay();
	}
	
	/**
	 * Initialize DataPoints for all Data in dataSet.
	 * @pre -
	 * @post -
	 */
	protected void initTemperatureOverlay() {
		if (this.dataSet != null) {
			for (Data data : this.dataSet) {
				DataPoint dataPoint = new DataPoint(this.mapWidget, this.colorTransition, data);
				this.dataPoints.add(dataPoint);
			}
		}
	}
	
	/**
	 * Set visibility of all DataPoints.
	 * @pre -
	 * @post -
	 * @param isVisible the visibility to set
	 */
	public void setVisibility(boolean isVisible) {
		if (isVisible) {
			for (DataPoint dataPoint : this.dataPoints) {
				dataPoint.setVisibility(isVisible);;
			}
		} else {
			for (DataPoint dataPoint : this.dataPoints) {
				dataPoint.setVisibility(isVisible);
			}
		}
	}
	
}
