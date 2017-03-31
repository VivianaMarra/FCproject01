package com.foodcloud.model.pages;

import com.foodcloud.model.dialogs.InputTextField;
import com.foodcloud.model.dialogs.SelectField;
import com.foodcloud.test.server.FCTestNavigator;

public class CharitiesPage extends SearchPage  {

	protected SearchMenu searchMenu;
	
	public static String SUPPORT_FIELD_NAME = "Support Rating";
	public static String CHARITY_NAME_FIELD_NAME = "Name";
	public static String LOCATION_FIELD_NAME = "Location";
	public static String REGION_FIELD_NAME = "Region";
	public static String CONTACT_PHONE_FIELD_NAME = "Contact Phone";
	public static String EMAIL_FIELD_NAME = "Email";

	private String CHARITY_NAME_FIELD_LOCATOR = "//input[@st-search='charity name']";
	private String SUPPORT_FIELD_LOCATOR = "//input[@st-search='support']";
	private String REGION_FIELD_LOCATOR = "//input[@st-search='region']";
	private String LOCATION_FIELD_LOCATOR = "//input[@st-search='location']";
	private String CONTACT_PHONE_FIELD_LOCATOR = "//input[@st-search='phone']";
	private String EMAIL_FIELD_LOCATOR = "//input[@st-search='email']";

	
	public CharitiesPage(FCTestNavigator nav_driver) {
		super(nav_driver);
		setupMenuFields();
	}

	
	private void setupMenuFields() {
		searchMenu.addField(SUPPORT_FIELD_NAME, new SelectField(nav,SUPPORT_FIELD_LOCATOR));
		searchMenu.addField(CHARITY_NAME_FIELD_NAME, new InputTextField(nav,CHARITY_NAME_FIELD_LOCATOR));
		searchMenu.addField(LOCATION_FIELD_NAME, new InputTextField(nav,LOCATION_FIELD_LOCATOR));
		searchMenu.addField(REGION_FIELD_NAME, new InputTextField(nav,REGION_FIELD_LOCATOR));
		searchMenu.addField(CONTACT_PHONE_FIELD_NAME, new InputTextField(nav,CONTACT_PHONE_FIELD_LOCATOR));
		searchMenu.addField(EMAIL_FIELD_NAME, new InputTextField(nav,EMAIL_FIELD_LOCATOR));
		// refresh		
		// clean up 
	}
		
}