package com.foodcloud.model.pages;

import com.foodcloud.test.server.FCTestNavigator;

public class SearchPage extends OptionMenuPage  {

	protected SearchMenu searchMenu;
	
	public static String DONOR_FIELD_NAME = "Donor";
	public static String STORE_FIELD_NAME = "Store";
	public static String CHARITY_FIELD_NAME = "Charity";
	public static String STATUS_FIELD_NAME = "Status";

	//protected String DATE_FIELD_LOCATOR = "//input[@st-search='date']";  //textarea
	protected String DATE_FIELD_LOCATOR = "css=table[id='ticket-table'] button.input-sm";
	protected String DONOR_FIELD_LOCATOR = "//input[@st-search='donor']";
	protected String STORE_FIELD_LOCATOR = "//input[@st-search='store']";
	protected String CHARITY_FIELD_LOCATOR = "//input[@st-search='charity']";
	protected String STATUS_FIELD_LOCATOR = "//select[@id='statusFilter']";

	
	public SearchPage(FCTestNavigator nav) {
		super(nav);
		searchMenu = new SearchMenu(nav);
	}

	public SearchMenu getSearchMenu() {
		return searchMenu;
	}
		
}