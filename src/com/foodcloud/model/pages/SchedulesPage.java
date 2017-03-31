package com.foodcloud.model.pages;

import org.openqa.selenium.WebDriver;

import com.foodcloud.model.dialogs.InputTextField;
import com.foodcloud.model.dialogs.SelectField;
import com.foodcloud.test.server.FCTestNavigator;

/**
 * This class is mapping in our test environment the Schedules Page from Copia 
 * Caracteristics:
 * 					* Progress bar functionality
 * 					* Search for schedule functionality
 * 					* refresh
 * @author Viviana
 *
 */
public class SchedulesPage extends SearchPage  {


	public static String DAY_DATE_FIELD_NAME = "Day/Date";
	private String DAY_DATE_FIELD_LOCATOR = "//select[@id='dowSelect']";

	
	public SchedulesPage(FCTestNavigator nav_driver) {
		super(nav_driver);
		setupMenuFields();
	}

	
	private void setupMenuFields() {
		searchMenu.addField(DAY_DATE_FIELD_NAME, new SelectField(nav,DAY_DATE_FIELD_LOCATOR));
		searchMenu.addField(DONOR_FIELD_NAME, new InputTextField(nav,DONOR_FIELD_LOCATOR));
		searchMenu.addField(STORE_FIELD_NAME, new InputTextField(nav,STORE_FIELD_LOCATOR));
		searchMenu.addField(CHARITY_FIELD_NAME, new InputTextField(nav,CHARITY_FIELD_LOCATOR));
		searchMenu.addField(STATUS_FIELD_NAME, new SelectField(nav,STATUS_FIELD_LOCATOR));
		// refresh		
		// clean up 
	}
		
}