package com.foodcloud.community.model.pages;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.foodcloud.model.dialogs.DialogField;
import com.foodcloud.model.dialogs.InputTextField;
import com.foodcloud.model.dialogs.SelectField;
import com.foodcloud.model.pages.Page;
import com.foodcloud.model.pages.SearchMenu;
import com.foodcloud.test.server.FCTestNavigator;

public class OrganizationPage extends Page {

	public SearchMenu templateSectionMenu;
	public SearchMenu createSectionMenu;
	
	public Map<String, Section> sections;

	public static String ORGANIZATION_TYPE_NAME = "Org type";
	public static String ORGANIZATION_TEMPLATE_NAME = "Org template";
	public static String ORGANIZATION_NAME = "Org name";

	private String ORGANIZATION_TYPE_LOCATOR = "css=select[ng-change='getTemplateOrgs()']";
	private String ORGANIZATION_TEMPLATE_LOCATOR = "css=div:not([class='ng-hide']) select[ng-model='newOrg.template']";

	private String ORG_NAME_LOCATOR = "css=section#edit-org:not([class='ng-hide']) input[ng-model='org.name']";
	private String CREATE_NEW_BUTTON_LOCATOR = "//a[text()='New Organization']";
	private String CONTINUE_ENABLED_LOCATOR = "css=section#edit-org:not([class='ng-hide']) button[ng-click='createOrgSubmit()']";

	public OrganizationPage(FCTestNavigator nav) {
		super(nav);
		sections = new HashMap<String, Section>();
	}

	/**
	 * Clicks on new org button
	 * 
	 * @return OrganizationPage object
	 */
	public OrganizationPage createNew() {

		server.click(CREATE_NEW_BUTTON_LOCATOR);

		return this;
	}

	/**
	 * Clicks on new org button
	 * 
	 * @return OrganizationPage object
	 */
	public OrganizationPage clickContinue() {

		server.click(CONTINUE_ENABLED_LOCATOR);

		return this;
	}

	
	//version 1
	public SearchMenu getTemplateSection() {
		setupTemplateFields();
		return templateSectionMenu;
	}

	public SearchMenu getCreateSection() {
		setupCreateFields();
		return createSectionMenu;
	}

	private void setupTemplateFields() {
		templateSectionMenu = new SearchMenu(this.nav);
		templateSectionMenu.addField(ORGANIZATION_TYPE_NAME, new SelectField(nav, ORGANIZATION_TYPE_LOCATOR));
		templateSectionMenu.addField(ORGANIZATION_TEMPLATE_NAME, new SelectField(nav, ORGANIZATION_TEMPLATE_LOCATOR));
	}

	private void setupCreateFields() {
		createSectionMenu = new SearchMenu(this.nav);
		createSectionMenu.addField(ORGANIZATION_NAME, new InputTextField(nav, ORG_NAME_LOCATOR));
	}

	
	//version 2   list 
	public OrganizationPage addSection(Section section) {
		String sectionName = section.getName(); 
		if(!sections.containsKey(sectionName)) {
			sections.put(sectionName, section);
		}
		return this;
	}

	public Map<String, Section> getSections() {		 
		return sections;
	}

	public Section getSection(String sectionName) {		 
		return sections.get(sectionName);
	}

}
