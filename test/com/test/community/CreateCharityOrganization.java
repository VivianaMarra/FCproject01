package com.test.community;

import java.io.FileNotFoundException;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.foodcloud.model.pages.DashboardPage;
import com.foodcloud.model.pages.DonationsPage;
import com.foodcloud.model.pages.LoginPage;
import com.foodcloud.model.pages.OptionMenu.MENU_ITEMS;
import com.foodcloud.test.server.FCTestNavigator;
import com.foodcloud.test.server.FCTestServer;

public class CreateCharityOrganization {

	FCTestServer server;
	private String URL = "https://community.food.cloud";
	private FCTestNavigator nav; 
	
	@BeforeClass
	public void setupBeforeClass() throws FileNotFoundException {
		
	nav = new FCTestNavigator();
	nav.setup();	
	server = nav.getServer();
		
	server.openURL(URL);

	String titleLocator = "//h1[text()='Welcome to FoodCloud.']";
	
	server.waitForElement(server.getLocatorType(titleLocator));				
	} 


	/**
	 * Jira: 
	 * User Story:
	 * Given I am a new user, When I click on create new Organization, (then) I want to land on creation page 
	 */
	@Test ( priority = 1 )
	public void verifyClickingOnNewOrg_OpensCreationPage() {
		String createNewOrgLocator = "//a[text()='New Organization']";
		
		server.click(createNewOrgLocator);	
		
//		OrganizationPage orgPage = new OrganizationPage(nav);
		server.waitForActionToComplete();

		boolean isNewOrgPage = server.isElementPresent("//div[contains(text(), 'New organization')]");	
		
		Assert.assertTrue(isNewOrgPage, "Error: New organization Page not displayed.");

		} 

	/**
	 * Jira: 
	 * User Story:
	 * Given I am an admin user, When I select on organization type and choose charity, (then) I want select a charity template 
	 */
	@Test ( priority = 2 )
	public void verifySelectCharity_SelectTemplate() {
		String selectOrganizationTypeLocator = "css=select[ng-change='getTemplateOrgs()']";
		String selectOrganizationTemplateLocator = "css=div:not([class='ng-hide']) select[ng-model='newOrg.template']";
		String inputNameLocator = "css=section#edit-org:not([class='ng-hide']) input[ng-model='org.name']";
		String buttonContinueDisabledLocator = "css=section#edit-org:not([class='ng-hide']) button[disabled='disabled'][ng-click='createOrgSubmit()']";
		
		server.select(selectOrganizationTypeLocator, "Charity");
		
		server.waitForActionToComplete();

		server.select(selectOrganizationTemplateLocator, "Template Charity");
		
		server.waitForActionToComplete();

		boolean isNewOrgNamePresent = server.isElementPresent(inputNameLocator);	
		boolean isContinueButtonDisabled = server.isElementPresent(buttonContinueDisabledLocator);	
		
		//revert choices ?? make this test independent from the other (following)
		Assert.assertTrue(isNewOrgNamePresent, "Error: New organization Name not displayed.");		
		Assert.assertTrue(isContinueButtonDisabled, "Error: Continue button not displayed.");		

} 
	
	
	@Test(priority = 3)
	public void verifySelectCharity_UseTemplateAndContinue() {

		String inputNameLocator = "css=section#edit-org:not([class='ng-hide']) input[ng-model='org.name']";
		String buttonContinueEnabledLocator = "css=section#edit-org:not([class='ng-hide']) button[ng-click='createOrgSubmit()']";
	
		server.type(inputNameLocator, "Test Charity 1");
		
		server.waitForActionToComplete();

		server.click(buttonContinueEnabledLocator);
		
		server.waitForActionToComplete();

		boolean isDescriprionPresent = server.isElementPresent("css=textarea[name='description']");

		Assert.assertTrue(isDescriprionPresent, "Error: Description not displayed.");

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

