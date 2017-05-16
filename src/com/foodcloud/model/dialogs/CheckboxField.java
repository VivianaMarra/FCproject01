package com.foodcloud.model.dialogs;

import com.foodcloud.test.server.FCTestNavigator;

public class CheckboxField extends DialogField {

	/**
	 * constructor that uses driver and locator
	 * @param nav_driver
	 * @param fieldLocator
	 */
	public CheckboxField(FCTestNavigator nav_driver, String fieldLocator) {
		super(nav_driver, fieldLocator);
	}

	/**
	 * set the field for checkbox. 
	 * Requires True or false passed as String
	 */
	@Override
	public void setField(String value) {
		//check if checkbox does not contain a value already
		String fieldClass = server.getDriver().findElement(server.getLocatorType(fieldLocator)).getAttribute("class");
		
		if(value.equalsIgnoreCase("true") && fieldClass.contains("ng-empty")) {
			server.checkbox(fieldLocator, true);
		} else {
			server.checkbox(fieldLocator, false);
		}
	}
	
	@Override
	public Object getField() {
		
		String currentValue = server.getDriver().findElement(server.getLocatorType(fieldLocator))
				.getAttribute("checked");
		System.out.println("Reading attribute value from InputTextField " + fieldLocator + " value: " + currentValue);

		boolean bool_value = false;
		if (currentValue != null && currentValue.equalsIgnoreCase("true")) {
			return bool_value = true;
		} else {
			return bool_value;
		}
	}
	
}
