package ch.uzh.ifi.rerg.se16_climeter.client.map;

import com.google.gwt.dom.client.Style.Position;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.base.Point;
import com.google.gwt.maps.client.overlays.InfoWindow;
import com.google.gwt.maps.client.overlays.InfoWindowOptions;
import com.google.gwt.maps.client.overlays.MapCanvasProjection;
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
 * @history 	2016-11-20 AM Initial commit
 * 				2016-12-08 AM Restructured and added InfoWindow
 * @version 	2016-11-20 AM 1.1
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
	private DataOverlay dataPoint;

	/**
	 * Generate DataPoint object for a Data object.
	 * @pre -
	 * @post -
	 * @param mapWidget the MapWidget, on which the DataPoint should be displayed
	 * @param colorTransition the ColorTransition to choose a color for a DataPoint object
	 * @param data Data object that should be displayed
	 */
	public DataPoint(MapWidget mapWidget, ColorTransition colorTransition, Data data) {
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
		final VerticalPanel dataPanel = getDataPanel();
		InfoWindow infoWindow = getInfoWindow();

		onDrawHandler = new OverlayViewOnDrawHandler() {
			@Override
			public void onDraw(OverlayViewMethods methods) {
				// positioning of a dataPanel on map
				MapCanvasProjection projection = methods.getProjection();
				Point point = projection.fromLatLngToDivPixel(data.getLatLng());
				dataPanel.getElement().getStyle().setPosition(Position.ABSOLUTE);
				dataPanel.getElement().getStyle().setLeft(point.getX()
						- dataPanel.getElement().getClientWidth() / 2, Unit.PX);
				dataPanel.getElement().getStyle().setTop(point.getY()
						- dataPanel.getElement().getClientHeight() / 2, Unit.PX);
			}
		};

		onAddHandler = new OverlayViewOnAddHandler() {
			@Override
			public void onAdd(OverlayViewMethods methods) {
				methods.getPanes().getOverlayMouseTarget().appendChild(dataPanel.getElement());
			}
		};

		onRemoveHandler = new OverlayViewOnRemoveHandler() {
			@Override
			public void onRemove(OverlayViewMethods methods) {
				dataPanel.getElement().removeFromParent();
			}
		};

		this.dataPoint = DataOverlay.newInstance(this.mapWidget, 
				onDrawHandler, onAddHandler, onRemoveHandler, dataPanel.getElement(), infoWindow);
	}

	/**
	 * Generates a panel for the given Data object.
	 * @pre -
	 * @post -
	 * @return the DataPanel as VerticalPanel
	 */
	public VerticalPanel getDataPanel() {
		VerticalPanel dataPanel = new VerticalPanel();
		dataPanel.addStyleName("temperatureOverlay");

		// calculate corresponding color for a data object
		Color color = colorTransition.getPercentageColor(this.data.getAverageTemperature());
		dataPanel.getElement().getStyle().setBackgroundColor(color.getHexString());

		// setting text and style
		HTML paddingText = new HTML("<br>");
		paddingText.addStyleName("uncertaintyText");
		HTML avgTempText = new HTML(NumberFormat.getFormat("0.##").format(this.data.getAverageTemperature()));
		avgTempText.addStyleName("avgTempText");
		HTML uncertaintyText = new HTML("&plusmn;" + NumberFormat.getFormat("0.##").format(this.data.getUncertainty()));
		uncertaintyText.addStyleName("uncertaintyText");

		dataPanel.clear();
		dataPanel.add(paddingText);
		dataPanel.add(avgTempText);
		dataPanel.add(uncertaintyText);

		return dataPanel;
	}

	/**
	 * Generates an info window for the given Data object.
	 * @pre -
	 * @post -
	 * @return the InfoWindow
	 */
	public InfoWindow getInfoWindow() {
		VerticalPanel infoWindowPanel = new VerticalPanel();
		infoWindowPanel.clear();
		infoWindowPanel.add(new HTML("<b>" + data.getCity() + ", " + data.getCountry() + "</b>"));
		infoWindowPanel.add(new HTML("Avg. temperature: " + NumberFormat.getFormat("0.#####").format(this.data.getAverageTemperature())));
		infoWindowPanel.add(new HTML("Avg. uncertainty: &plusmn;" + NumberFormat.getFormat("0.#####").format(this.data.getUncertainty())));

		InfoWindowOptions infoWindowOptions = InfoWindowOptions.newInstance();
		infoWindowOptions.setContent(infoWindowPanel);
		infoWindowOptions.setPosition(data.getLatLng());

		return InfoWindow.newInstance(infoWindowOptions);
	}

	/**
	 * Set visibility of DataPoint.
	 * @pre -
	 * @post -
	 */
	public void setVisibility(boolean isVisible) {
		if (dataPoint == null) {
			return;
		}

		if (isVisible) {
			this.dataPoint.setMap(this.mapWidget);
		} else {
			this.dataPoint.setMap(null);
		}
	}

	/**
	 * @pre -
	 * @post -
	 * @return the DataPoint as OverlayView
	 */
	public DataOverlay getDataOverlay() {
		return this.dataPoint;
	}

}
