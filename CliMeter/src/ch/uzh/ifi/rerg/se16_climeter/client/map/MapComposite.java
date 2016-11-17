package ch.uzh.ifi.rerg.se16_climeter.client.map;

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
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import ch.uzh.ifi.rerg.se16_climeter.client.Data;

/**
 * The class MapComposite is a concrete Map, load into a Composite object, 
 * which shows data on corresponding coordinates.
 * 
 * @author 		Alphonse Mariyagnanaseelan
 * @history 	2016-11-03 AM Initial Commit
 * 				2016-11-04 AM Displays simple map
 * 				2016-11-06 AM Displays data points on the map
 * 				2016-11-07 AM Displays multiple data points
 * 				2016-11-14 AM Gray-map glitch fixed
 * 				2016-11-16 AM Added dynamic colored data points
 * @version 	2016-11-16 AM 1.1
 * @responsibilities 
 * 				This class contains the map and all layers on top of it.
 */
public class MapComposite extends Composite {
	
	private LayoutPanel panel;
	private MapWidget mapWidget;
	private List<Data> dataSet;
	private ColorTransition colorTransition;
	private List<OverlayView> temperatureOverlays;
	
	/**
	 * Initialize as Composite and add google map on it.
	 * @pre -
	 * @post panel != null, mapWidget != null
	 * @param dataSet Data objects which will be visualised on the map
	 */
	public MapComposite(List<Data> dataSet) {
		this.panel = new LayoutPanel();
		this.temperatureOverlays = new ArrayList<OverlayView>();
		this.dataSet = dataSet;
		
		this.initWidget(this.panel);
		this.draw();
		this.addData();
	}
	
	/**
	 * Add Data to the map.
	 * @pre -
	 * @post -
	 */
	private void addData() {
		colorTransition = new ColorTransition();
		
		if (this.dataSet != null) {
			for (Data data : this.dataSet) {
				this.addTemperatureOverlay(data);
			}
		}
		
		for (OverlayView overlayView : this.temperatureOverlays) {
			overlayView.setMap(this.mapWidget);
		}
	}
	
	/**
	 * Draws the basic map.
	 * @pre panel != null
	 * @post mapWidget.getParent == panel
	 */
	private void draw() {
		// set up basic map
		LatLng center = LatLng.newInstance(47.37174, 8.54226);
		MapOptions options = MapOptions.newInstance();
		options.setZoom(5);
		options.setCenter(center);
		options.setMapTypeId(MapTypeId.TERRAIN);
		
		// add mapWidget to panel
		this.mapWidget = new MapWidget(options);
		this.panel.clear();
		this.panel.add(this.mapWidget);
		this.mapWidget.setSize("100%", "100%");
	}
	
	/**
	 * Visualises one Data object on the map.
	 * @pre data != null
	 * @post temperatureOverlays.size() = temperatureOverlays.size()@pre + 1
	 * @param data Data object to visualise on the map
	 */
	private void addTemperatureOverlay(final Data data) {
		final VerticalPanel temperatureOverlayPanel = new VerticalPanel();
		temperatureOverlayPanel.addStyleName("temperatureOverlay");
		
		// calculate corresponding color for a data object
		Color color = colorTransition.getPercentageColor(data.getAverageTemperature(), -30.0, 30.0);
		temperatureOverlayPanel.getElement().getStyle().setBackgroundColor(color.getHexString());
		
		OverlayViewOnDrawHandler onDrawHandler = new OverlayViewOnDrawHandler() {
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
		
//		TemperatureOverlay temperatureOverlay = new TemperatureOverlay(data);
//		this.temperatureOverlays.add(temperatureOverlay.newInstance(mapWidget));
	}
	
	@Override
	protected void onAttach() {
		super.onAttach();
		
		// workaround to fix a glitch, where the map occasionally stays gray
		// needed for Internet Explorer
		Timer timer = new Timer() {
			@Override
			public void run() {}
		};
		timer.schedule(1);
	}
	
	/**
	 * @pre -
	 * @post -
	 * @return the mapWidget
	 */
	protected MapWidget getMapWidget() {
		return this.mapWidget;
	}
	
}
