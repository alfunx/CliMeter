package ch.uzh.ifi.rerg.se16_climeter.client.timeline;

import com.google.gwt.widgetideas.client.SliderBar;

import ch.uzh.ifi.rerg.se16_climeter.client.Console;
import ch.uzh.ifi.rerg.se16_climeter.client.Filter;
import ch.uzh.ifi.rerg.se16_climeter.client.map.MapComposite;

import java.util.Date;

import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.event.dom.client.MouseUpEvent;
import com.google.gwt.event.dom.client.MouseUpHandler;

/**
 * The class TimeLine extends the SliderBar class
 * and creates a SliderBar object which is used
 * in the MapComposite class.
 * 
 * @author 	Timo Surbeck
 * @history 2016-11-23 TS First Version
 * 			2016-11-26 TS on-change stub method
 * 			2016-12-02 TS step size fixed
 * 			2016-12-03 TS added updateValue method
 * 			2016-12-03 TS fixed arrow key problem
 * 			2016-12-05 TS submitting new filter objects
 * 						  to MapComposite class
 * @version	2016-12-05 TS 1.2
 * @responsibilities 
 */
public class TimeLine extends SliderBar {
	
	// reference time value for change checking in updateValue(double value)
	protected double value = this.getCurrentValue();
	private MapComposite mapComposite = null;
	
	public TimeLine(double minValue, double maxValue, MapComposite mapComposite) {
		super(minValue, maxValue, new LabelFormatter() {
			@Override
			public String formatLabel(SliderBar slider, double value) {
				return (int) (10 * value) / 10 + "";
			}
		});
		
		this.mapComposite = mapComposite;
		
		this.setStepSize(1);
		this.setCurrentValue(minValue + ((maxValue-minValue)/2));
		
		this.setNumTicks((int)(maxValue - minValue));
		this.setNumLabels((int)(maxValue - minValue)/10);
		
		this.setSize("100%", "100%");
		
		this.addMouseUpHandler(new MouseUpHandler() {
			@Override
			public void onMouseUp(MouseUpEvent event) {
				updateValue(getCurrentValue());
				
			}
		});

		this.addKeyDownHandler(new KeyDownHandler() {
			@Override
			public void onKeyDown(KeyDownEvent event) {
				if(event.isRightArrow() && getCurrentValue() < getMaxValue()){
					updateValue(getCurrentValue()+1);
				}
				if(event.isLeftArrow() && getCurrentValue() > getMinValue()){
					updateValue(getCurrentValue()-1);
				}
				
			}
		});
	}
	
	/**
	 * Checks whether there has been an actual change on the currentValue.
	 * If there was a change on value, the method creates a new filter
	 * object containing new date info (year) and submits it to the 
	 * MapComposite class object.
	 * @pre this.value != null
	 * @post this.value == value
	 * @return -
	 */
	@SuppressWarnings("deprecation")
	public void updateValue(double value){
		if(this.value != value){
			this.value = value;
			
			Console.log("TimeLine: Value changed, new year: " + value);
			
			Filter filter = new Filter();
			Date chosenYear = new Date((int) value-1900, 0, 1);
			
			filter.setBeginDate(chosenYear);
			filter.setEndDate(chosenYear);
			
			mapComposite.apply(filter);
			
		} else {
			
			Console.log("TimeLine: NO change on value, year: " + this.value);
			
		}
		
	}
	
}
