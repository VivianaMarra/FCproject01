package com.foodcloud.model.dialogs;

import org.openqa.selenium.WebDriver;

import com.foodcloud.test.server.FCTestNavigator;
import com.foodcloud.test.server.FCTestServer;

public class DialogField {

	protected String fieldLocator;
	protected WebDriver driver;
	protected FCTestServer server;
	protected FCTestNavigator nav;
	
	public DialogField(FCTestNavigator nav_driver, String fieldLocator) {
		this.driver = nav_driver.getDriver();
		this.server = nav_driver.getServer();
		this.nav = nav_driver;		
		this.fieldLocator = fieldLocator;	
	}
	// to implement according to class type
	public void setField(String value) {
	}

	//Default to Text,  to implement according to class type
	public Object getField() {
		return driver.findElement(server.getLocatorType(fieldLocator)).getText();
	}
	
}
