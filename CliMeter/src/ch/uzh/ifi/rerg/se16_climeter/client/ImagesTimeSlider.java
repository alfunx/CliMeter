package ch.uzh.ifi.rerg.se16_climeter.client;

import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.DataResource;
import com.google.gwt.resources.client.ImageResource;

public interface ImagesTimeSlider extends ClientBundle {
    @Source("kdehdrag.png")

    ImageResource drag();



    @Source("kdehless.png")

    ImageResource less();



    @Source("kdehmore.png")

    ImageResource more();



    @Source("kdehscale.png")

    DataResource scale();

}
