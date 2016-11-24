package ch.uzh.ifi.rerg.se16_climeter.client;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.widgetideas.client.SliderBar;
import com.google.gwt.widgetideas.client.SliderBar.LabelFormatter;

/**
 * The class TimeLine extends the SliderBar class
 * and creates a SliderBar object which is used
 * in the MapComposite class.
 * 
 * @author 	Timo Surbeck
 * @history 2016-11-23 TS First Version 
 * @version	2016-11-23 TS 1.0
 * 
 * @responsibilities 
 */
public class TimeLine extends SliderBar {

	public TimeLine(double minValue, double maxValue) {
		super(minValue, maxValue, new LabelFormatter() {
			@Override
			public String formatLabel(SliderBar slider, double value) {
				return (int) (10 * value) / 10 + "";
			}
		});
		
		this.setStepSize(50);

		this.setCurrentValue((minValue + maxValue)/2);
		this.setNumTicks((int) (maxValue - minValue));
	
		this.setNumLabels(13);
		this.setSize("100%", "100%");
		this.setVisible(true);
		
		this.addValueChangeHandler(new ValueChangeHandler(){

			@Override
			public void onValueChange(ValueChangeEvent event) {
			System.out.println();

			}

			});
		
	}
}