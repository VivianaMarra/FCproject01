package com.foodcloud.model.pages;

import java.util.HashMap;
import org.openqa.selenium.WebDriver;
import com.foodcloud.model.dialogs.DialogField;
import com.foodcloud.test.server.FCTestNavigator;
import com.foodcloud.test.server.FCTestServer;

public class Menu {
	
	protected WebDriver driver;
	protected HashMap<String, DialogField> fields;
	protected FCTestNavigator nav;
	protected FCTestServer server;
	
	public Menu(FCTestNavigator navigator) {
		this.nav = navigator;
		this.server = navigator.getServer();
		this.driver = navigator.getDriver();
		fields = new HashMap<String, DialogField>();
	}

	/**
	 * Sets a value in a field
	 * NOTE: not defined so far in Calendar fields
	 * @param fieldName - String identifying the name of the field
	 * @param value - String identifying the value 
	 */
	public void setField(String fieldName, String value) {
		fields.get(fieldName).setField(value);
	}
	

	/**
	 * Adds a field in the field list
	 * @param fieldName - 
	 * @param genericField -
	 */
	public void addField(String fieldName, DialogField genericField) {
		fields.put(fieldName, genericField);
		
	}

	/**
	 * Clears a field
	 * @param fieldName
	 */
	public void cleanField(String fieldName) {
		fields.get(fieldName).setField("");
	}

	/**
	 * Sets a value in a field
	 * NOTE: not defined so far in Calendar fields
	 * @param fieldName - String identifying the name of the field
	 * @param value - String identifying the value 
	 * @return 
	 */
	protected HashMap<String, DialogField> getFieldList() {
		return fields;
	}
	
	
	/**
	 * Adds a field in the field list
	 * @param fieldName - 
	 * @param genericField -
	 */
	public Object getField(String fieldName) {
		return fields.get(fieldName).getField();		
	}
}
