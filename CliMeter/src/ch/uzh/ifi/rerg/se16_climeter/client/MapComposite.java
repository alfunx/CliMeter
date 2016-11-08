package ch.uzh.ifi.rerg.se16_climeter.client;

import java.util.ArrayList;
import java.util.List;
import com.google.gwt.dom.client.Style.Position;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.maps.client.MapOptions;
import com.google.gwt.maps.client.MapTypeId;
import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.base.LatLng;
import com.google.gwt.maps.client.base.Point;
import com.google.gwt.maps.client.overlays.MapCanvasProjection;
import com.google.gwt.maps.client.overlays.OverlayView;
import com.google.gwt.maps.client.overlays.overlayhandlers.OverlayViewMethods;
import com.google.gwt.maps.client.overlays.overlayhandlers.OverlayViewOnAddHandler;
import com.google.gwt.maps.client.overlays.overlayhandlers.OverlayViewOnDrawHandler;
import com.google.gwt.maps.client.overlays.overlayhandlers.OverlayViewOnRemoveHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * The class MapComposite is a concrete Map, which shows data on 
 * corresponding coordinates.
 * 
 * @author Alphonse Mariyagnanaseelan
 * @history 2016-11-03 AM Initial Commit
 *          2016-11-04 AM Displays simple map
 *          2016-11-06 AM Displays data points on the map
 *          2016-11-07 AM Displays multiple data points
 * @version 2016-11-07 AM 1.0
 * 
 * @responsibilities This class contains the map and all layers ontop of it.
 */
public class MapComposite extends Composite {
	
	private LayoutPanel panel;
	private MapWidget mapWidget;
	private List<OverlayView> temperatureOverlays;
	
	/**
	 * @param dataSet Data objects which will be visualised on the map
	 */
	protected MapComposite(List<Data> dataSet) {
		this.panel = new LayoutPanel();
		initWidget(this.panel);
		draw();
		
		this.temperatureOverlays = new ArrayList<OverlayView>();
		for(Data data : dataSet) {
			addTemperatureOverlay(data);
		}
		for(OverlayView overlayView : this.temperatureOverlays) {
			overlayView.setMap(this.mapWidget);
		}
	}
	
	/**
	 * Draws the basic map.
	 */
	private void draw() {
		// set up basic map
		LatLng center = LatLng.newInstance(47.37174, 8.54226);
		MapOptions options = MapOptions.newInstance();
		options.setZoom(11);
		options.setCenter(center);
		options.setMapTypeId(MapTypeId.TERRAIN);
		
		// add mapWidget to panel
		this.mapWidget = new MapWidget(options);
		this.panel.clear();
		this.panel.add(this.mapWidget);
		
		this.mapWidget.triggerResize();
		this.mapWidget.setSize("100%", "100%");
	}
	
	/**
	 * Visualises one Data object on the map.
	 * @param data Data object to visualise on the map
	 */
	private void addTemperatureOverlay(final Data data) {
		final VerticalPanel temperatureOverlayPanel = new VerticalPanel();
		temperatureOverlayPanel.addStyleName("temperatureOverlay");
		
		OverlayViewOnDrawHandler onDrawHandler = new OverlayViewOnDrawHandler() {
			@Override
			public void onDraw(OverlayViewMethods methods) {
				MapCanvasProjection projection = methods.getProjection();
				LatLng coordinate = getLatLng(data);
				Point point = projection.fromLatLngToDivPixel(coordinate);
				temperatureOverlayPanel.getElement().getStyle().setPosition(Position.ABSOLUTE);
				temperatureOverlayPanel.getElement().getStyle().setLeft(point.getX() - temperatureOverlayPanel.getElement().getClientWidth()/2, Unit.PX);
				temperatureOverlayPanel.getElement().getStyle().setTop(point.getY() - temperatureOverlayPanel.getElement().getClientHeight()/2, Unit.PX);
				
				HTML text = new HTML(data.getAverageTemperature() + "");
				text.addStyleName("temperatureText");
				temperatureOverlayPanel.clear();
				temperatureOverlayPanel.add(text);
				System.out.println(text.getText());
			}
		};
		
		OverlayViewOnAddHandler onAddHandler = new OverlayViewOnAddHandler() {
			@Override
			public void onAdd(OverlayViewMethods methods) {
				methods.getPanes().getFloatPane().appendChild(temperatureOverlayPanel.getElement());
			}
		};
		
		OverlayViewOnRemoveHandler onRemoveHandler = new OverlayViewOnRemoveHandler() {
			@Override
			public void onRemove(OverlayViewMethods methods) {
				temperatureOverlayPanel.getElement().removeFromParent();
			}
		};
		
		this.temperatureOverlays.add(OverlayView.newInstance(this.mapWidget, onDrawHandler, onAddHandler, onRemoveHandler));
	}
	
	/**
	 * Converts the coordinates of a Data object into a LatLng Object.
	 * @param data Data object whose coordinates are needed
	 * @return LatLng object containing the coordinates
	 */
	private LatLng getLatLng(Data data) {
		return LatLng.newInstance(data.getLatitude(), data.getLongitude());
	}
	
}
