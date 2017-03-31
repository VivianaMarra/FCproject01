package com.foodcloud.model.pages;

import com.foodcloud.model.dialogs.DialogField;
import com.foodcloud.model.dialogs.InputTextField;
import com.foodcloud.model.dialogs.SelectField;
import com.foodcloud.test.server.FCTestNavigator;

public class SupportTicketsPage extends SearchPage  {

	public static String TICKET_DATE_FIELD_NAME = "Ticket Date";
	public static String TICKET_CATEGORY_FIELD_NAME = "Ticket Category";	
	
	private String TICKET_CATEGORY_FIELD_LOCATOR = "//input[@st-search='category']";	
	
	public SupportTicketsPage(FCTestNavigator nav_driver) {
		super(nav_driver);
		setupMenuFields();
	}

	private void setupMenuFields() {
		
		searchMenu.addField(TICKET_DATE_FIELD_NAME, new DialogField(nav, DATE_FIELD_LOCATOR));
		searchMenu.addField(TICKET_CATEGORY_FIELD_NAME, new InputTextField(nav,TICKET_CATEGORY_FIELD_LOCATOR));		
		searchMenu.addField(DONOR_FIELD_NAME, new InputTextField(nav,DONOR_FIELD_LOCATOR));		
		searchMenu.addField(STORE_FIELD_NAME, new InputTextField(nav,STORE_FIELD_LOCATOR));				
		searchMenu.addField(CHARITY_FIELD_NAME, new InputTextField(nav,CHARITY_FIELD_LOCATOR));		
		searchMenu.addField(STATUS_FIELD_NAME, new SelectField(nav,STATUS_FIELD_LOCATOR));

	}

	
	
	
	
}