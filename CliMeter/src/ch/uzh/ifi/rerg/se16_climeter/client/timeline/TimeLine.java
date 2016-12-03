package ch.uzh.ifi.rerg.se16_climeter.client.timeline;

import com.google.gwt.widgetideas.client.SliderBar;

import ch.uzh.ifi.rerg.se16_climeter.client.Console;

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
 * @version	2016-12-03 TS 1.1
 * 
 * @responsibilities 
 */
public class TimeLine extends SliderBar {
	
	// reference time value for change checking in updateValue(double value)
	protected double value = this.getCurrentValue();
	
	public TimeLine(double minValue, double maxValue) {
		super(minValue, maxValue, new LabelFormatter() {
			@Override
			public String formatLabel(SliderBar slider, double value) {
				return (int) (10 * value) / 10 + "";
			}
		});

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
		
		updateValue(this.getCurrentValue());
	}
	
	/**
	 * Checks whether there has been an actual change on the currentValue
	 * @pre this.value != null
	 * @post this.value == value
	 * @return -
	 */
	public void updateValue(double value){
		if(this.value != value){
			this.value = value;
			Console.log("There HAS been a change on the class instance value: " + value);
			//TODO: call update method of Filter class and transmit new value
		} else {
			Console.log("There has been NO change on the class instance value: " + this.value);
		}
		
	}
	
}
