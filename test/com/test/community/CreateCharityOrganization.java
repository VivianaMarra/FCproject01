package com.test.community;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.foodcloud.community.model.pages.DataTriplet;
import com.foodcloud.community.model.pages.OrganizationPage;
import com.foodcloud.community.model.pages.Section;
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
		//String today="20170512";
		String today="20170516";
				
		OrganizationPage orgPage = new OrganizationPage(nav);
		orgPage.addSection(
				new Section(nav,"Create org", 
						new ArrayList<DataTriplet>(){{
							add(new DataTriplet("Organization Name", DataTriplet.FieldType.TEXT, "css=section#edit-org:not([class='ng-hide']) input[ng-model='org.name']"));
						}} ));
	
			
		orgPage.getSection("Create org").setField("Organization Name", "Test Charity 5 " + today);	
		orgPage.clickContinue();
		
		server.waitForActionToComplete();

		boolean isDescriptionPresent = server.isElementPresent("css=textarea[name='description']");

		Assert.assertTrue(isDescriptionPresent, "Error: Description not displayed.");

	}
					
	
	
	@Test(priority = 4)
	public void charityTemplate_FillMandatoryFields() {

		List<DataTriplet> profileList = new ArrayList<DataTriplet>();
		profileList.add(new DataTriplet("Description" , DataTriplet.FieldType.TEXT, "css=textarea[name='description']")); 
		profileList.add(new DataTriplet("Website" , DataTriplet.FieldType.TEXT, "css=input[name='website']")); 
		
		List<DataTriplet> locationList = new ArrayList();		
		locationList.add(new DataTriplet("Post Code" , DataTriplet.FieldType.TEXT, "css=input[name='addr-code']"));				
		locationList.add(new DataTriplet("Address" , DataTriplet.FieldType.TEXT, "css=input[name='address']"));				
		locationList.add(new DataTriplet("Town" , DataTriplet.FieldType.TEXT, "css=input[name='town']"));				
		locationList.add(new DataTriplet("County" , DataTriplet.FieldType.TEXT, "css=input[name='county']"));				
		locationList.add(new DataTriplet("Country" , DataTriplet.FieldType.TEXT, "css=input[name='country']"));				
		

		List<DataTriplet> contactList = new ArrayList();		
		contactList.add(new DataTriplet("Branch Phone" , DataTriplet.FieldType.TEXT, "css=input[name='branch-phone']"));				
		contactList.add(new DataTriplet("Branch Email" , DataTriplet.FieldType.TEXT, "css=input[name='branch-email']"));				
		contactList.add(new DataTriplet("Branch Manager" , DataTriplet.FieldType.TEXT, "css=input[name='branch-name']"));				
		contactList.add(new DataTriplet("Branch Role" , DataTriplet.FieldType.TEXT, "css=input[name='branch-role']"));				

		List<DataTriplet> foodList = new ArrayList();		
		foodList.add(new DataTriplet("Current Food Use" , DataTriplet.FieldType.CHECKBOX, "css=input[name='existing-food-user']"));				
		foodList.add(new DataTriplet("Redistribution of food" , DataTriplet.FieldType.CHECKBOX, "css=input[name='food-use-redist-parcels']"));				
		foodList.add(new DataTriplet("Provision of food externally" , DataTriplet.FieldType.CHECKBOX, "css=input[name='food-use-provide-own-use']"));				
		foodList.add(new DataTriplet("Provision of food internally" , DataTriplet.FieldType.CHECKBOX, "css=input[name='food-use-provide-in-house']"));				
		foodList.add(new DataTriplet("Cook and provide meals externally" , DataTriplet.FieldType.CHECKBOX, "css=input[name='food-use-cook-for-external']"));				
		foodList.add(new DataTriplet("Cook and provide meals internally" , DataTriplet.FieldType.CHECKBOX, "css=input[name='food-use-cook-for-on-site']"));				

		OrganizationPage orgPage = new OrganizationPage(nav);
		orgPage.addSection(new Section(nav,"Profile",profileList));
		orgPage.addSection(new Section(nav,"Location", locationList));
		orgPage.addSection(new Section(nav,"Contact", contactList));
		orgPage.addSection(new Section(nav,"FoodUse", foodList));

		
		String buttonSaveEnabledLocator = "css=button[ng-click='editOrgSubmit()']";
		
		orgPage.getSection("Profile").setField("Description", "20170516 Test Charity 1 description" );

		orgPage.getSection("Location").setField("Post Code","PostCode 4444" );
		orgPage.getSection("Location").setField("Address","Street 1");
		orgPage.getSection("Location").setField("Town", "Town 1" );
		orgPage.getSection("Location").setField("County","county 1" );
		orgPage.getSection("Location").setField("Country", "country 1" );


		orgPage.getSection("Contact").setField("Branch Phone", "+3538716400111");
		orgPage.getSection("Contact").setField("Branch Email", "viviana@foodcloud.ie");
		orgPage.getSection("Contact").setField("Branch Manager", "Manager 1");
		orgPage.getSection("Contact").setField("Branch Role", "Role 1");

		orgPage.getSection("FoodUse").setField("Current Food Use", "true");
		orgPage.getSection("FoodUse").setField("Redistribution of food", "true");
		orgPage.getSection("FoodUse").setField("Provision of food externally", "true");
		orgPage.getSection("FoodUse").setField("Provision of food internally", "true");
		
		orgPage.getSection("FoodUse").setField("Cook and provide meals externally", "true");
		orgPage.getSection("FoodUse").setField("Cook and provide meals internally", "true");

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

