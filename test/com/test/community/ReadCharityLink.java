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

public class ReadCharityLink {

	FCTestServer server;
	private String URL = "https://community.food.cloud/#!/org?orgId=77&edit=true&//form=charity-due-dil-form&section=food-types";
	
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


	
//	public class ParamTestWithDataProvider2 {
//		   @DataProvider(name = "test1")
//		   public static Object[][] primeNumbers() {
//		      return new Object[][] { { new Bean("hi I am the bean", 111) } };
//		   }
//
//		   @Test(dataProvider = "test1")
//		   public void testMethod(Bean myBean) {
//		      System.out.println(myBean.getVal() + " " + myBean.getI());
//		   }
//		}
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
	
	// verify Sections presence 
	//	is there any locators for sections???
	// section id= edit-food-types
	
	
	
	
	
	
	/**
	 * Jira: 
	 * User Story:
	 * Given I am a new user, When I click on create new Organization, (then) I want to land on creation page 
	 * PROBLEM: Organization page to be recreated ALL the time ( for each test)
	 */
	@Test ( priority = 1 , enabled = false) // data source??
	public void verifyOpenLink_CanReadData() {
		String expectedStoredData = "15000";
				
		// can the following list be part of the dataprovider???
		List<DataTriplet> list = new ArrayList();
		list.add(new DataTriplet("Monthly Spend" , DataTriplet.FieldType.TEXT, "css=input[name='food-spend']")); 
		list.add(new DataTriplet("Top Dairy" , DataTriplet.FieldType.TEXT, "css=textarea[name='food-top-dairy']")); 
		list.add(new DataTriplet("Top Meat" , DataTriplet.FieldType.TEXT, "css=textarea[name='food-top-meat']"));				
		list.add(new DataTriplet("Top Non-Perishable" , DataTriplet.FieldType.TEXT, "css=textarea[name='food-top-nonperish']"));				
		list.add(new DataTriplet("Top Other" , DataTriplet.FieldType.TEXT, "css=textarea[name='food-top-other']"));
		list.add(new DataTriplet("Top FruitVeg" , DataTriplet.FieldType.TEXT, "css=textarea[name='food-top-ffv']") );

		OrganizationPage orgPage = new OrganizationPage(nav);
//		orgPage.addSection(
//				new Section(nav,"Food Types",new DataTriplet("Monthly Spend" , DataTriplet.FieldType.TEXT, "css=input[name='food-spend']")));

		orgPage.addSection(new Section(nav,"Food Types",list));


		orgPage.getSection("Food Types").setField("Monthly Spend", expectedStoredData );
		orgPage.getSection("Food Types").setField("Top Dairy", "Goats Milk" );
		orgPage.getSection("Food Types").setField("Top Meat", "Steak" );
		orgPage.getSection("Food Types").setField("Top FruitVeg", "Banana" );
	
		server.waitForActionToComplete();

		String actualStoredData = (String) orgPage.getSection("Food Types").getField("Monthly Spend");
		
		Assert.assertEquals(actualStoredData, expectedStoredData, "Error: New value not displayed.");

		} 


	
	@Test (priority = 2) // data source??
	public void verifyOpenLink_FoodSafetySection_CanReadData() {
		
		List<DataTriplet> list = new ArrayList();
		list.add(new DataTriplet("Registered Food Organization" , DataTriplet.FieldType.CHECKBOX, "css=input[name='food-use-registration']")); 
		list.add(new DataTriplet("Food Safety Inspection" , DataTriplet.FieldType.CHECKBOX, "css=input[name='food-use-audit']")); 
		list.add(new DataTriplet("Food Safety Policy" , DataTriplet.FieldType.CHECKBOX, "css=input[name='food-safety-management-policy']"));				
		list.add(new DataTriplet("Donation Sorting" , DataTriplet.FieldType.CHECKBOX, "css=input[name='food-safety-management-sorting']"));				
		list.add(new DataTriplet("Food Supplier List" , DataTriplet.FieldType.CHECKBOX, "css=input[name='food-safety-management-supplier']"));
		list.add(new DataTriplet("Appropriate Transport" , DataTriplet.FieldType.CHECKBOX, "css=input[name='food-safety-management-transport']"));
		list.add(new DataTriplet("Storage Facilities" , DataTriplet.FieldType.CHECKBOX, "css=input[name='food-safety-management-storage']"));
		list.add(new DataTriplet("Hygiene Facilities" , DataTriplet.FieldType.CHECKBOX, "css=input[name='food-safety-management-hygiene']"));
		list.add(new DataTriplet("Food Preparation Facilities" , DataTriplet.FieldType.CHECKBOX, "css=input[name='food-safety-management-preparation']"));
		list.add(new DataTriplet("Waste Disposal Facilities" , DataTriplet.FieldType.CHECKBOX, "css=input[name='food-safety-management-waste']"));
		list.add(new DataTriplet("Pest Control" , DataTriplet.FieldType.CHECKBOX, "css=input[name='food-safety-management-pest']"));
		list.add(new DataTriplet("Temperature Checks" , DataTriplet.FieldType.CHECKBOX, "css=input[name='food-safety-management-temperature']"));
		list.add(new DataTriplet("Food Records" , DataTriplet.FieldType.CHECKBOX, "css=input[name='food-use-records']"));
		list.add(new DataTriplet("Food Safety Training" , DataTriplet.FieldType.TEXT, "css=textarea[name='food-safety-training']") );
		list.add(new DataTriplet("Safety Training System" , DataTriplet.FieldType.TEXT, "css=input[name='food-safety-training-system']") );


		OrganizationPage orgPage = new OrganizationPage(nav);
		orgPage.addSection(new Section(nav,"Food Safety",list));

		//adding fields to section
		orgPage.getSection("Food Safety").setField("Food Supplier List", "true" );
		orgPage.getSection("Food Safety").setField("Appropriate Transport", "true" );
		orgPage.getSection("Food Safety").setField("Food Records", "true" );
		orgPage.getSection("Food Safety").setField("Storage Facilities", "false" );
		orgPage.getSection("Food Safety").setField("Safety Training System", "Banana" );
	
		server.waitForActionToComplete();

		String actualStoredData = (String) orgPage.getSection("Food Safety").getField("Safety Training System");
		Boolean actualSuppliers = (Boolean) orgPage.getSection("Food Safety").getField("Food Supplier List");
		Boolean actualTransport = (Boolean) orgPage.getSection("Food Safety").getField("Appropriate Transport");
		Boolean actualStorage = (Boolean) orgPage.getSection("Food Safety").getField("Storage Facilities");
//		String actualSafetyTraining = (String) orgPage.getSection("Food Safety").getField("Safety Training System");
		Boolean actualRecords = (Boolean) orgPage.getSection("Food Safety").getField("Food Records");
		
		Assert.assertEquals(actualStoredData, "Banana", "Error: New value not displayed.");
		Assert.assertTrue(actualSuppliers, "Error: New value not displayed.");
		Assert.assertTrue(actualTransport, "Error: New value not displayed.");
		Assert.assertFalse(actualStorage,  "Error: New value not displayed.");
	//	Assert.assertEquals(actualSafetyTraining, "true", "Error: New value not displayed.");
		Assert.assertTrue(actualRecords, "Error: New value not displayed.");

		} 

	
	
	@Test (priority = 3) // data source??
	public void verifyOpenLink_TermsAndConsSection_SaveData() {				
		
		List<DataTriplet> list = new ArrayList();
		list.add(new DataTriplet("Marketing Permission" , DataTriplet.FieldType.CHECKBOX, "css=input[name='marketing-opt-in']")); 
		list.add(new DataTriplet("Terms and Conditions" , DataTriplet.FieldType.CHECKBOX, "css=input[name='terms-conditions']")); 
	
		OrganizationPage orgPage = new OrganizationPage(nav);
		orgPage.addSection(new Section(nav,"Terms and Conditions",list));

		//adding fields to section
		orgPage.getSection("Terms and Conditions").setField("Marketing Permission", "true" );
		orgPage.getSection("Terms and Conditions").setField("Terms and Conditions", "true" );

		orgPage.clickSave();
		
	}
	
	@AfterClass	
	public void tearDown(){
		server.getDriver().close();
	}
	
}

