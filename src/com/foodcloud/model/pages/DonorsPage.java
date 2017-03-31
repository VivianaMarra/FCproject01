package com.foodcloud.model.pages;

import com.foodcloud.model.dialogs.InputTextField;
import com.foodcloud.test.server.FCTestNavigator;

/**
 * This class is mapping in our test environment the Donor Organizations Page from Copia 
 * Caracteristics:
 * 					* New donor creation functionality
 * 					* Search for Donor functionality
 * 					* refresh
 * @author Viviana
 *
 */
public class DonorsPage extends SearchPage  {

	protected SearchMenu searchMenu;
	
	public static String STORE_NAME_FIELD_NAME = "Name";	
	public static String LOCATION_FIELD_NAME = "Location";
	public static String REGION_FIELD_NAME = "Region";
	public static String CONTACT_PHONE_FIELD_NAME = "Contact Phone";
	public static String EMAIL_FIELD_NAME = "Email";

	private String STORE_NAME_FIELD_LOCATOR = "//input[@st-search='store name']";
	private String STORE_NUM_FIELD_LOCATOR = "//input[@st-search='code']";
	private String REGION_FIELD_LOCATOR = "//input[@st-search='region']";
	private String LOCATION_FIELD_LOCATOR = "//input[@st-search='location']";
	private String CONTACT_PHONE_FIELD_LOCATOR = "//input[@st-search='phone']";
	private String EMAIL_FIELD_LOCATOR = "//input[@st-search='email']";

	
	public DonorsPage(FCTestNavigator nav_driver) {
		super(nav_driver);
		setupMenuFields();
	}

	
	private void setupMenuFields() {
		searchMenu.addField(STORE_NAME_FIELD_NAME, new InputTextField(nav,STORE_NAME_FIELD_LOCATOR));
		searchMenu.addField(STORE_FIELD_NAME, new InputTextField(nav,STORE_NUM_FIELD_LOCATOR));
		searchMenu.addField(LOCATION_FIELD_NAME, new InputTextField(nav,LOCATION_FIELD_LOCATOR));
		searchMenu.addField(REGION_FIELD_NAME, new InputTextField(nav,REGION_FIELD_LOCATOR));
		searchMenu.addField(CONTACT_PHONE_FIELD_NAME, new InputTextField(nav,CONTACT_PHONE_FIELD_LOCATOR));
		searchMenu.addField(EMAIL_FIELD_NAME, new InputTextField(nav,EMAIL_FIELD_LOCATOR));
		// refresh		
		// clean up 
	}
		
}