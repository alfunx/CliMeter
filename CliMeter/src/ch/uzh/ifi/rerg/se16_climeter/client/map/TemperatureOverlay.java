package ch.uzh.ifi.rerg.se16_climeter.client.map;

import java.util.ArrayList;
import java.util.List;
import com.google.gwt.maps.client.MapWidget;

import ch.uzh.ifi.rerg.se16_climeter.client.Data;

public class TemperatureOverlay {
	
	private MapWidget mapWidget;
	private ColorTransition colorTransition;
	private List<Data> dataSet;
	
	private List<DataPoint> dataPoints;
	
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
