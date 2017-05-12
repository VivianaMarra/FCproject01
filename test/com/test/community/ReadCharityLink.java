package com.test.community;

import java.io.FileNotFoundException;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.foodcloud.community.model.pages.DataTriplet;
import com.foodcloud.community.model.pages.OrganizationPage;
import com.foodcloud.community.model.pages.Section;
import com.foodcloud.test.server.FCTestNavigator;
import com.foodcloud.test.server.FCTestServer;

public class ReadCharityLink {

	FCTestServer server;
	private String URL = "https://community.food.cloud/#!/org?orgId=64&edit=true&form=charity-due-dil-form&section=food-types";
	private FCTestNavigator nav; 
	
	@BeforeClass
	public void setupBeforeClass() throws FileNotFoundException {
		
	nav = new FCTestNavigator();
	nav.setup();	
	server = nav.getServer();
		
	server.openURL(URL);

	String titleLocator = "//div[contains(text(),'Edit organization')]";
	
	server.waitForElement(server.getLocatorType(titleLocator));				
	} 


	/*
	 * High level 
	 * check that in food types section there are:
	 * Monthly spend 
	 * Top Dairy
	 * Top Meat
	 * Top Non-perishable
	 * Top Fruit&Veg
	 * Top Other
	 * */
	
	
	//Read charity expected section 
	// add a section list 
	// in the section list , add 
	
	
	
	
	
	
	
	/**
	 * Jira: 
	 * User Story:
	 * Given I am a new user, When I click on create new Organization, (then) I want to land on creation page 
	 * PROBLEM: Organization page to be recreated ALL the time ( for each test)
	 */
	@Test ( priority = 1 ) // data source
	public void verifyOpenLink_CanReadData() {
		String expectedStoredData = "15000";
				
		
//		List<DataTriplet> = 
		
		OrganizationPage orgPage = new OrganizationPage(nav);
		orgPage.addSection(
				new Section(nav,"Food Types",new DataTriplet("Monthly Spend" , DataTriplet.FieldType.TEXT, "css=input[name='food-spend']")));
		
		orgPage.getSection("Food Types").setField("Monthly Spend", expectedStoredData );
	
		server.waitForActionToComplete();

		String actualStoredData = (String) orgPage.getSection("Food Types").getField("Monthly Spend");
		
		Assert.assertEquals(actualStoredData, expectedStoredData, "Error: New value not displayed.");

		} 

	/**
	 * Jira: 
	 * User Story:
	 * Given I am an admin user, When I select on organization type and choose charity, (then) I want select a charity template 
	 */
	@Test ( priority = 2 )
	public void verifySelectCharity_SelectTemplate() {
		String inputNameLocator = "css=section#edit-org:not([class='ng-hide']) input[ng-model='org.name']";
		String buttonContinueDisabledLocator = "css=section#edit-org:not([class='ng-hide']) button[disabled='disabled'][ng-click='createOrgSubmit()']";
		
		
		OrganizationPage orgPage = new OrganizationPage(nav);
		orgPage.getTemplateSection().setField(OrganizationPage.ORGANIZATION_TYPE_NAME,"Charity");
		orgPage.getTemplateSection().setField(OrganizationPage.ORGANIZATION_TEMPLATE_NAME,"Template Charity");

		server.waitForActionToComplete();

		boolean isNewOrgNamePresent = server.isElementPresent(inputNameLocator);	
		boolean isContinueButtonDisabled = server.isElementPresent(buttonContinueDisabledLocator);	
		
		//revert choices ?? make this test independent from the other (following)
		Assert.assertTrue(isNewOrgNamePresent, "Error: New organization Name not displayed.");		
		Assert.assertTrue(isContinueButtonDisabled, "Error: Continue button not displayed.");		

} 
	
	
	@Test(priority = 3)
	public void verifySelectCharity_UseTemplateAndContinue() {
	
		OrganizationPage orgPage = new OrganizationPage(nav);
		orgPage.getCreateSection().setField(OrganizationPage.ORGANIZATION_NAME,"Test Charity 1");
		orgPage.clickContinue();
		
		server.waitForActionToComplete();

		boolean isDescriptionPresent = server.isElementPresent("css=textarea[name='description']");

		Assert.assertTrue(isDescriptionPresent, "Error: Description not displayed.");

	}
					
	
	
	@Test(priority = 4)
	public void charityTemplate_FillMandatoryFields() {

		String buttonSaveEnabledLocator = "css=button[ng-click='editOrgSubmit()']";

		String descriptionLocator = "css=textarea[name='description']";
		String postCodeLocator = "css=input[name='addr-code']";

		String addressLocator = "css=input[name='address']";
		String townLocator = "css=input[name='town']";

		String countyLocator = "css=input[name='county']";
		String countryLocator = "css=input[name='country']";
		
		String branchPhoneLocator = "css=input[name='branch-phone']";
		String branchEmailLocator = "css=input[name='branch-email']";

		String branchManagerLocator = "css=input[name='branch-name']";
		String branchRoleLocator = "css=input[name='branch-role']";

		String currentFoodUseLocator = "css=input[name='existing-food-user']";
		
		server.type(descriptionLocator, "Test Charity 1 description");
		server.type(postCodeLocator, "4444");
		server.type(addressLocator, "Street 1");
		server.type(townLocator, "Town 1");
		server.type(countyLocator, "county 1");
		server.type(countryLocator, "country 1");
		server.type(branchPhoneLocator, "+353871640017");
		
		server.type(branchEmailLocator, "viviana@foodcloud.ie");

		server.type(branchManagerLocator, "Manager 1");
		server.type(branchRoleLocator, "Role 1");
		
		server.checkbox(currentFoodUseLocator, true);
	
		server.waitForActionToComplete();

		server.click(buttonSaveEnabledLocator);
		
		server.waitForActionToComplete();

		boolean isThanksMessagePresent = server.isElementPresent("css=form:not([class*= ng-hide]) p[ng-show='updateStatus']");

		Assert.assertTrue(isThanksMessagePresent, "Error: Thank you Message not displayed.");
	
	}
					
	@AfterClass	
	public void tearDown(){
		server.getDriver().close();
	}
	
}

