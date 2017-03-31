package com.foodcloud.model.dialogs;

import com.foodcloud.test.server.FCTestNavigator;

public class DateField extends DialogField {


	/**
	 * constructor that uses driver and locator
	 * @param nav_driver
	 * @param fieldLocator
	 */
	public DateField(FCTestNavigator nav_driver, String fieldLocator) {
		super(nav_driver, fieldLocator);
	}

	@Override
	public void setField(String value) {
		server.click(fieldLocator);
		//server.type(fieldLocator, value);		
	}
	// // ul.uib-datepicker-popup
	
}
