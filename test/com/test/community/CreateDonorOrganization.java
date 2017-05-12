package com.test.community;

import java.io.FileNotFoundException;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.foodcloud.community.model.pages.OrganizationPage;
import com.foodcloud.test.server.FCTestNavigator;
import com.foodcloud.test.server.FCTestServer;

public class CreateDonorOrganization {

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
		
		OrganizationPage orgPage = new OrganizationPage(nav);
		orgPage.createNew();
		
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
	public void verifySelectDonor_SelectTemplate() {
		String inputNameLocator = "css=section#edit-org:not([class='ng-hide']) input[ng-model='org.name']";
		String buttonContinueDisabledLocator = "css=section#edit-org:not([class='ng-hide']) button[disabled='disabled'][ng-click='createOrgSubmit()']";
		
		
		OrganizationPage orgPage = new OrganizationPage(nav);
		orgPage.getTemplateSection().setField(OrganizationPage.ORGANIZATION_TYPE_NAME,"Donor");
		orgPage.getTemplateSection().setField(OrganizationPage.ORGANIZATION_TEMPLATE_NAME,"Template Donor");

		server.waitForActionToComplete();

		boolean isNewOrgNamePresent = server.isElementPresent(inputNameLocator);	
		boolean isContinueButtonDisabled = server.isElementPresent(buttonContinueDisabledLocator);	
		
		//revert choices ?? make this test independent from the other (following)
		Assert.assertTrue(isNewOrgNamePresent, "Error: New organization Name not displayed.");		
		Assert.assertTrue(isContinueButtonDisabled, "Error: Continue button not displayed.");		

} 
	
	
	@Test(priority = 3)
	public void verifySelectDonor_UseTemplateAndContinue() {
	
		OrganizationPage orgPage = new OrganizationPage(nav);
		orgPage.getCreateSection().setField(OrganizationPage.ORGANIZATION_NAME,"Test Donor 1");
		orgPage.clickContinue();
		
		server.waitForActionToComplete();

		boolean isDescriptionPresent = server.isElementPresent("css=textarea[name='description']");

		Assert.assertTrue(isDescriptionPresent, "Error: Description not displayed.");

	}
					
	
	
	@Test(priority = 4)
	public void donorTemplate_FillMandatoryFields() {

		String buttonSaveEnabledLocator = "css=button[ng-click='editOrgSubmit()']";

		String postCodeLocator = "css=input[name='addr-code']";
		String addressLocator = "css=input[name='address']";
		String townLocator = "css=input[name='town']";

		String countyLocator = "css=input[name='county']";
		String countryLocator = "css=input[name='country']";
		
		String branchPhoneLocator = "css=input[name='branch-phone']";
		String branchEmailLocator = "css=input[name='branch-email']";

		String branchManagerLocator = "css=input[name='branch-name']";
		String branchRoleLocator = "css=input[name='branch-role']";
		server.type(postCodeLocator, "4444");
		server.type(addressLocator, "Street 1");
		server.type(townLocator, "Town 1");
		server.type(countyLocator, "county 1");
		server.type(countryLocator, "country 1");
		server.type(branchPhoneLocator, "+353871640017");
		
		server.type(branchEmailLocator, "viviana@foodcloud.ie");

		server.type(branchManagerLocator, "Manager 1");
		server.type(branchRoleLocator, "Role 1");
	
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

