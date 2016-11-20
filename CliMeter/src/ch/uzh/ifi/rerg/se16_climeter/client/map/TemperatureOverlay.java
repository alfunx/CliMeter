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
	 * @param mapWidget the MapWidget, on which the overlay should be displayed
	 * @param colorTransition the ColorTransition to choose a color for a Data object
	 * @param dataSet 
	 */
	public TemperatureOverlay(MapWidget mapWidget, ColorTransition colorTransition, List<Data> dataSet) {
		this.mapWidget = mapWidget;
		this.colorTransition = colorTransition;
		this.dataSet = dataSet;
		this.dataPoints = new ArrayList<DataPoint>();
		
		initTemperatureOverlay();
	}
	
	protected void initTemperatureOverlay() {
		if (this.dataSet != null) {
			for (Data data : this.dataSet) {
				DataPoint dataPoint = new DataPoint(this.mapWidget, this.colorTransition, data);
				this.dataPoints.add(dataPoint);
			}
		}
		
		setVisibility(true);
	}
	
	public void setVisibility(boolean isVisible) {
		if (isVisible) {
			for (DataPoint dataPoint : this.dataPoints) {
				dataPoint.getDataPointOverlayView().setMap(this.mapWidget);
			}
		} else {
			for (DataPoint dataPoint : this.dataPoints) {
				dataPoint.getDataPointOverlayView().setMap(null);
			}
		}
	}
	
}
