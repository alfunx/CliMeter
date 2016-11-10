package ch.uzh.ifi.rerg.se16_climeter.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;

public interface Resources extends ClientBundle {
	public static final Resources INSTANCE = GWT.create(Resources.class); 
	
	@Source("Custom.css")
	@CssResource.NotStrict
	CssResource css();
}
