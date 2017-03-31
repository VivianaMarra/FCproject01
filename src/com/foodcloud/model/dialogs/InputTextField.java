package com.foodcloud.model.dialogs;

import com.foodcloud.test.server.FCTestNavigator;

public class InputTextField extends DialogField {

	/**
	 * constructor that uses driver and locator
	 * @param nav_driver
	 * @param fieldLocator
	 */
	public InputTextField(FCTestNavigator nav_driver, String fieldLocator) {
		super(nav_driver, fieldLocator);
	}

	@Override
	public void setField(String value) {
		server.type(fieldLocator, value);		
	}
	
}
