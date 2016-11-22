package ch.uzh.ifi.rerg.se16_climeter.client;


import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Widget;
import com.kiouri.sliderbar.client.view.SliderBarHorizontal;



public class TimeSlider extends SliderBarHorizontal {

    

    ImagesTimeSlider images = GWT.create(ImagesTimeSlider.class);

    

    @SuppressWarnings("deprecation")
	public TimeSlider(int maxValue, String width, String height) {

        setLessWidget(new Image(images.less()));

        setMoreWidget(new Image(images.more()));

        setScaleWidget(new Image(images.scale().getUrl()), 16);

        setMoreWidget(new Image(images.more()));

        setDragWidget(new Image(images.drag()));

        this.setWidth(width);
        
        this.setHeight(height);

        this.setMaxValue(maxValue);     

    }
    
    public Widget getWidget(){
    	return this.getRootWidget();
    }
}