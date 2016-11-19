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

public class DataPoint {
	
	private MapWidget mapWidget;
	private ColorTransition colorTransition;
	private Data data;
	
	private OverlayView dataPoint;
	
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
		
		OverlayViewOnDrawHandler onDrawHandler = new OverlayViewOnDrawHandler() {
			@Override
			public void onDraw(OverlayViewMethods methods) {
				// calculate corresponding color for a data object
				Color color = colorTransition.getPercentageColor(data.getAverageTemperature(), -30.0, 30.0);
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
		
		OverlayViewOnAddHandler onAddHandler = new OverlayViewOnAddHandler() {
			@Override
			public void onAdd(OverlayViewMethods methods) {
				methods.getPanes().getFloatPane().appendChild(dataPointPanel.getElement());
			}
		};
		
		OverlayViewOnRemoveHandler onRemoveHandler = new OverlayViewOnRemoveHandler() {
			@Override
			public void onRemove(OverlayViewMethods methods) {
				dataPointPanel.getElement().removeFromParent();
			}
		};
		
		this.dataPoint = OverlayView.newInstance(this.mapWidget, onDrawHandler, onAddHandler, onRemoveHandler);
	}
	
	protected OverlayView getDataPointOverlayView() {
		return this.dataPoint;
	}
	
}
