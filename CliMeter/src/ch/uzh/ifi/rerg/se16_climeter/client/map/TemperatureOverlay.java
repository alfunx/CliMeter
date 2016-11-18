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

public class TemperatureOverlay {
	
	private MapWidget mapWidget;
	private Data data;
	private VerticalPanel temperatureOverlayPanel;
	private ColorTransition colorTransition;
	
	private OverlayView temperatureOverlay;
	private OverlayViewOnDrawHandler onDrawHandler;
	private OverlayViewOnAddHandler onAddHandler;
	private OverlayViewOnRemoveHandler onRemoveHandler;
	
	public TemperatureOverlay(MapWidget mapWidget, ColorTransition colorTransition) {
		this.colorTransition = colorTransition;
		this.mapWidget = mapWidget;
	}
	
	public final OverlayView update(Data data) {
		this.data = data;
		initTemperatureOverlay();
		return getOverlay();
	}
	
	/**
	 * Visualises one Data object on the map.
	 * @pre data != null
	 * @post temperatureOverlays.size() = temperatureOverlays.size()@pre + 1
	 * @param data Data object to visualise on the map
	 */
	protected void initTemperatureOverlay() {
		temperatureOverlayPanel = new VerticalPanel();
		temperatureOverlayPanel.addStyleName("temperatureOverlay");
		
		// calculate corresponding color for a data object
		Color color = colorTransition.getPercentageColor(data.getAverageTemperature(), -30.0, 30.0);
		temperatureOverlayPanel.getElement().getStyle().setBackgroundColor(color.getHexString());
		
		onDrawHandler = new OverlayViewOnDrawHandler() {
			@Override
			public void onDraw(OverlayViewMethods methods) {
				// positioning of a data point
				MapCanvasProjection projection = methods.getProjection();
				Point point = projection.fromLatLngToDivPixel(data.getLatLng());
				temperatureOverlayPanel.getElement().getStyle().setPosition(Position.ABSOLUTE);
				temperatureOverlayPanel.getElement().getStyle().setLeft(point.getX()
						- temperatureOverlayPanel.getElement().getClientWidth()/2, Unit.PX);
				temperatureOverlayPanel.getElement().getStyle().setTop(point.getY()
						- temperatureOverlayPanel.getElement().getClientHeight()/2, Unit.PX);
				
				// setting text and style
				HTML text = new HTML(data.getAverageTemperature() + "");
				text.addStyleName("temperatureText");
				temperatureOverlayPanel.clear();
				temperatureOverlayPanel.add(text);
			}
		};
		
		onAddHandler = new OverlayViewOnAddHandler() {
			@Override
			public void onAdd(OverlayViewMethods methods) {
				methods.getPanes().getFloatPane().appendChild(temperatureOverlayPanel.getElement());
			}
		};
		
		onRemoveHandler = new OverlayViewOnRemoveHandler() {
			@Override
			public void onRemove(OverlayViewMethods methods) {
				temperatureOverlayPanel.getElement().removeFromParent();
			}
		};
		
		temperatureOverlay = OverlayView.newInstance(this.mapWidget, this.onDrawHandler, this.onAddHandler, this.onRemoveHandler);
	}
	
	public final OverlayView getOverlay() {
		return temperatureOverlay;
	}
	
}
