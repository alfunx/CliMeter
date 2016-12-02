package ch.uzh.ifi.rerg.se16_climeter.client;

import com.google.gwt.widgetideas.client.SliderBar;
import com.google.gwt.user.client.ui.ChangeListener;
import com.google.gwt.user.client.ui.Widget;

/**
 * The class TimeLine extends the SliderBar class
 * and creates a SliderBar object which is used
 * in the MapComposite class.
 * 
 * @author 	Timo Surbeck
 * @history 2016-11-23 TS First Version
 * 			2016-11-26 TS on-change stub method
 * @version	2016-11-23 TS 1.0
 * 
 * @responsibilities 
 */
@SuppressWarnings("deprecation")
public class TimeLine extends SliderBar {

	public TimeLine(double minValue, double maxValue) {
		super(minValue, maxValue, new LabelFormatter() {
			@Override
			public String formatLabel(SliderBar slider, double value) {
				return (int) (10 * value) / 10 + "";
			}
		});

		this.setStepSize(1);
		this.setCurrentValue(maxValue);

		this.setNumTicks((int) (maxValue - minValue));
		this.setNumLabels(10);

		this.setSize("100%", "100%");
//		this.addChangeListener(new ChangeListener() {
//
//			@Override
//			public void onChange(Widget sender) {
//				Console.log("HelloWorld");
//
//				
//			}
//			
//		});


	}
}