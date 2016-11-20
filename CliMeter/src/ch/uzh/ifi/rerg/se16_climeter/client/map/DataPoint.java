package ch.uzh.ifi.rerg.se16_climeter.client.map;

import com.google.gwt.dom.client.Style.Position;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.base.Point;
import com.google.gwt.maps.client.overlays.MapCanvasProjection;
import com.google.gwt.maps.client.overlays.OverlayView;
import com.google.gwt.maps.client.overlays.overlayhandlers.OverlayViewMethods;
import com.google.gwt.maps.client.overlays.overlayhandlers.OverlayViewOnAddHandler;
import com.google.gwt.maps.client.overlays.overlayhandlers.OverlayViewOnDrawHandler;
import com.google.gwt.maps.client.overlays.overlayhandlers.OverlayViewOnRemoveHandler;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.VerticalPanel;

import ch.uzh.ifi.rerg.se16_climeter.client.Data;

/**
 * The class DataPoint is one Data object displayed on the map.
 * 
 * @author 		Alphonse Mariyagnanaseelan
 * @history 	2016-11-20 AM Initial Commit
 * @version 	2016-11-20 AM 1.0
 * @responsibilities 
 * 				This class displays one data object on the map.
 */
public class DataPoint {
	
	private MapWidget mapWidget;
	private ColorTransition colorTransition;
	private Data data;
	
	private OverlayViewOnDrawHandler onDrawHandler;
	private OverlayViewOnAddHandler onAddHandler;
	private OverlayViewOnRemoveHandler onRemoveHandler;
	private OverlayView dataPoint;
	
	/**
	 * Generate DataPoint object for a Data object.
	 * @pre -
	 * @post -
	 * @param mapWidget the MapWidget, on which the DataPoint should be displayed
	 * @param colorTransition the ColorTransition to choose a color for a DataPoint object
	 * @param data Data object that should be displayed
	 */
	protected DataPoint(MapWidget mapWidget, ColorTransition colorTransition, Data data) {
		this.mapWidget = mapWidget;
		this.colorTransition = colorTransition;
		this.data = data;
		
		initDataPoint();
	}
	
	/**
	 * Visualises one Data object on the map.
	 * @pre data != null
	 * @post temperatureOverlays.size() = temperatureOverlays.size()@pre + 1
	 * @param data Data object to visualise on the map
	 */
	protected void initDataPoint() {
		final VerticalPanel dataPointPanel = new VerticalPanel();
		dataPointPanel.addStyleName("temperatureOverlay");
		
		onDrawHandler = new OverlayViewOnDrawHandler() {
			@Override
			public void onDraw(OverlayViewMethods methods) {
				// calculate corresponding color for a data object
				Color color = colorTransition.getPercentageColor(data.getAverageTemperature());
				dataPointPanel.getElement().getStyle().setBackgroundColor(color.getHexString());
				
				// positioning of a data point
				MapCanvasProjection projection = methods.getProjection();
				Point point = projection.fromLatLngToDivPixel(data.getLatLng());
				dataPointPanel.getElement().getStyle().setPosition(Position.ABSOLUTE);
				dataPointPanel.getElement().getStyle().setLeft(point.getX()
						- dataPointPanel.getElement().getClientWidth() / 2, Unit.PX);
				dataPointPanel.getElement().getStyle().setTop(point.getY()
						- dataPointPanel.getElement().getClientHeight() / 2, Unit.PX);
				
				// setting text and style
				HTML text = new HTML(data.getAverageTemperature() + "");
				text.addStyleName("temperatureText");
				dataPointPanel.clear();
				dataPointPanel.add(text);
			}
		};
		
		onAddHandler = new OverlayViewOnAddHandler() {
			@Override
			public void onAdd(OverlayViewMethods methods) {
				methods.getPanes().getFloatPane().appendChild(dataPointPanel.getElement());
			}
		};
		
		onRemoveHandler = new OverlayViewOnRemoveHandler() {
			@Override
			public void onRemove(OverlayViewMethods methods) {
				dataPointPanel.getElement().removeFromParent();
			}
		};
		
		this.dataPoint = OverlayView.newInstance(this.mapWidget, onDrawHandler, onAddHandler, onRemoveHandler);
	}
	
	/**
	 * @pre -
	 * @post -
	 * @return the DataPoint as OverlayView
	 */
	protected OverlayView getDataPointOverlayView() {
		return this.dataPoint;
	}
	
}
