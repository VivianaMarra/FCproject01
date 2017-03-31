package com.foodcloud.model.pages;

import org.openqa.selenium.WebDriver;

import com.foodcloud.model.dialogs.DateField;
import com.foodcloud.model.dialogs.InputTextField;
import com.foodcloud.model.dialogs.SelectField;
import com.foodcloud.test.server.FCTestNavigator;

public class CollectionsPage extends SearchPage  {

	protected SearchMenu searchMenu;
	
	public static String DATE_FIELD_NAME = "Date";
	public static String COLLECTED_BY_FIELD_NAME = "Collected By";

	private String COLLECTED_BY_FIELD_LOCATOR = "//input[@st-search='collector']";

	public CollectionsPage(FCTestNavigator nav_driver) {
		super(nav_driver);
		setupMenuFields();
	}

	private void setupMenuFields() {
		
		searchMenu.addField(DATE_FIELD_NAME, new DateField(nav,DATE_FIELD_LOCATOR));
		searchMenu.addField(DONOR_FIELD_NAME, new InputTextField(nav,DONOR_FIELD_LOCATOR));
		searchMenu.addField(STORE_FIELD_NAME, new InputTextField(nav,STORE_FIELD_LOCATOR));

		searchMenu.addField(CHARITY_FIELD_NAME, new InputTextField(nav,CHARITY_FIELD_LOCATOR));
		searchMenu.addField(COLLECTED_BY_FIELD_NAME, new InputTextField(nav,COLLECTED_BY_FIELD_LOCATOR));
		// total value
		// total weight 
		// refresh
		
	}
		
}