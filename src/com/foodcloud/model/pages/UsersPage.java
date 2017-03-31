package com.foodcloud.model.pages;

import com.foodcloud.model.dialogs.InputTextField;
import com.foodcloud.model.dialogs.SelectField;
import com.foodcloud.test.server.FCTestNavigator;

/**
 * This class is mapping in our test environment the Users Page from Copia 
 * Caracteristics:
 * 					* Search for User functionality
 * 					* refresh
 * @author Viviana
 *
 */
public class UsersPage extends SearchPage  {

	protected SearchMenu searchMenu;
	
	public static String USER_NAME_FIELD_NAME = "Name";	
	public static String ORG_TYPE_FIELD_NAME = "Org Type";	
	public static String ORG_NAME_FIELD_NAME = "Organization";		
	public static String ROLE_FIELD_NAME = "Role";
	public static String REGION_FIELD_NAME = "Region";
	public static String CONTACT_PHONE_FIELD_NAME = "Contact Phone";
	public static String EMAIL_FIELD_NAME = "Email";
	public static String LOCALE_FIELD_NAME = "Locale";

	private String USER_NAME_FIELD_LOCATOR = "//input[@st-search='name']";
	private String ORG_TYPE_FIELD_LOCATOR = "//select[@st-search='orgtype']";
	private String ORG_NAME_FIELD_LOCATOR = "//input[@st-search='orgname']";
	private String ROLE_FIELD_LOCATOR = "//input[@st-search='role']";
	private String REGION_FIELD_LOCATOR = "//input[@st-search='region']";
	private String CONTACT_PHONE_FIELD_LOCATOR = "//input[@st-search='phone']";
	private String EMAIL_FIELD_LOCATOR = "//input[@st-search='email']";
	private String LOCALE_FIELD_LOCATOR = "//input[@st-search='locale']";

	
	public UsersPage(FCTestNavigator nav_driver) {
		super(nav_driver);
		setupMenuFields();
	}

	
	private void setupMenuFields() {
		searchMenu.addField(USER_NAME_FIELD_NAME, new InputTextField(nav,USER_NAME_FIELD_LOCATOR));
		searchMenu.addField(ORG_TYPE_FIELD_NAME, new SelectField(nav,ORG_TYPE_FIELD_LOCATOR));
		searchMenu.addField(ORG_NAME_FIELD_NAME, new InputTextField(nav,ORG_NAME_FIELD_LOCATOR));		
		searchMenu.addField(ROLE_FIELD_NAME, new SelectField(nav,ROLE_FIELD_LOCATOR));
		searchMenu.addField(REGION_FIELD_NAME, new InputTextField(nav,REGION_FIELD_LOCATOR));
		searchMenu.addField(CONTACT_PHONE_FIELD_NAME, new InputTextField(nav,CONTACT_PHONE_FIELD_LOCATOR));
		searchMenu.addField(EMAIL_FIELD_NAME, new InputTextField(nav,EMAIL_FIELD_LOCATOR));
		searchMenu.addField(LOCALE_FIELD_NAME, new InputTextField(nav,LOCALE_FIELD_LOCATOR));
		searchMenu.addField(STATUS_FIELD_NAME, new SelectField(nav,STATUS_FIELD_LOCATOR));

		// refresh		
		// clean up 
	}
		
}