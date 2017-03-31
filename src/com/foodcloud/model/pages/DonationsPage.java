package com.foodcloud.model.pages;

import com.foodcloud.model.dialogs.DateField;
import com.foodcloud.model.dialogs.InputTextField;
import com.foodcloud.model.dialogs.SelectField;
import com.foodcloud.model.tables.ResultTable;
import com.foodcloud.test.server.FCTestNavigator;

public class DonationsPage extends SearchPage  {

	protected ResultTable resultTable;
	
	public static String COLLECTION_DATE_FIELD_NAME = "Collection Date";
	public static String TABLE_LOCATOR = "id=donation-table";
	
	private String DONATION_DATE_FIELD_LOCATOR = "//input[@st-search='date']";  //textarea


	public DonationsPage(FCTestNavigator nav_driver) {
		super(nav_driver);
		setupMenuFields();
	}

	private void setupMenuFields() {
		searchMenu.addField(COLLECTION_DATE_FIELD_NAME, new DateField(nav,DONATION_DATE_FIELD_LOCATOR));
		searchMenu.addField(DONOR_FIELD_NAME, new InputTextField(nav,DONOR_FIELD_LOCATOR));
		searchMenu.addField(STORE_FIELD_NAME, new InputTextField(nav,STORE_FIELD_LOCATOR));

		searchMenu.addField(CHARITY_FIELD_NAME, new InputTextField(nav,CHARITY_FIELD_LOCATOR));
		searchMenu.addField(STATUS_FIELD_NAME, new SelectField(nav, STATUS_FIELD_LOCATOR));		
	}

	public ResultTable getResultTable() {
		return new ResultTable(nav, TABLE_LOCATOR, this);
		
	}
		
}