package ch.uzh.ifi.rerg.se16_climeter.client;

import com.google.gwt.dom.client.Style.Position;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.maps.client.MapOptions;
import com.google.gwt.maps.client.MapTypeId;
import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.base.LatLng;
import com.google.gwt.maps.client.base.Point;
import com.google.gwt.maps.client.overlays.Circle;
import com.google.gwt.maps.client.overlays.CircleOptions;
import com.google.gwt.maps.client.overlays.MapCanvasProjection;
import com.google.gwt.maps.client.overlays.OverlayView;
import com.google.gwt.maps.client.overlays.overlayhandlers.OverlayViewMethods;
import com.google.gwt.maps.client.overlays.overlayhandlers.OverlayViewOnAddHandler;
import com.google.gwt.maps.client.overlays.overlayhandlers.OverlayViewOnDrawHandler;
import com.google.gwt.maps.client.overlays.overlayhandlers.OverlayViewOnRemoveHandler;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.VerticalPanel;

public class TemperatureMap extends AbstractMap {
	
	private VerticalPanel htmlOverlayMessage;
	private OverlayView temperatureOverlayView;
	
	public TemperatureMap() {
		super();
	}
	
	@Override
	protected void draw() {
		// zoom out for the clouds
		LatLng center = LatLng.newInstance(47.37174, 8.54226);
		MapOptions options = MapOptions.newInstance();
		options.setZoom(11);
		options.setCenter(center);
		options.setMapTypeId(MapTypeId.TERRAIN);
		
		// set mapWidget object
		mapWidget = new MapWidget(options);
		panel.add(mapWidget);
		mapWidget.setSize("100%", "100%");
		
		htmlOverlayMessage = new VerticalPanel();
		drawShape();
		drawTemperatureOverlay(20.9);
		temperatureOverlayView.setMap(mapWidget);
	}
	
	private void drawShape() {
		// add some draw shapes for folks
		CircleOptions cOpts = CircleOptions.newInstance();
		cOpts.setCenter(LatLng.newInstance(47.37174, 8.54226));
		cOpts.setFillColor("#FF0000");
		cOpts.setStrokeColor("CCFF44");
		cOpts.setFillOpacity(0.5d);
		cOpts.setStrokeOpacity(0.9d);
		cOpts.setStrokeWeight(3);
		cOpts.setRadius(10000d);
		cOpts.setMap(mapWidget);
		
		@SuppressWarnings("unused")
		Circle circle = Circle.newInstance(cOpts);
	}
	
	private void drawTemperatureOverlay(final double temperature) {
		OverlayViewOnDrawHandler onDrawHandler = new OverlayViewOnDrawHandler() {
			@Override
			public void onDraw(OverlayViewMethods methods) {
				MapCanvasProjection projection = methods.getProjection();
				LatLng sw = LatLng.newInstance(47.37174, 8.54226);
				Point p = projection.fromLatLngToDivPixel(sw);
				htmlOverlayMessage.getElement().getStyle().setPosition(Position.ABSOLUTE);
				htmlOverlayMessage.getElement().getStyle().setLeft(p.getX() - htmlOverlayMessage.getElement().getClientWidth()/2, Unit.PX);
				htmlOverlayMessage.getElement().getStyle().setTop(p.getY() - htmlOverlayMessage.getElement().getClientHeight()/2, Unit.PX);
				
				String message = "projection world width: " + projection.getWorldWidth();
				message = temperature + "";
				htmlOverlayMessage.clear();
				htmlOverlayMessage.add(new HTML(message));
				System.out.println(message);
			}
		};
		
		OverlayViewOnAddHandler onAddHandler = new OverlayViewOnAddHandler() {
			@Override
			public void onAdd(OverlayViewMethods methods) {
				methods.getPanes().getFloatPane().appendChild(htmlOverlayMessage.getElement());
			}
		};
		
		OverlayViewOnRemoveHandler onRemoveHandler = new OverlayViewOnRemoveHandler() {
			@Override
			public void onRemove(OverlayViewMethods methods) {
				htmlOverlayMessage.getElement().removeFromParent();
			}
		};
		
		temperatureOverlayView = OverlayView.newInstance(mapWidget, onDrawHandler, onAddHandler, onRemoveHandler);
	}
	
}
