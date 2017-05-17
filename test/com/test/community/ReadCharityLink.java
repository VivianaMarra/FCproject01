package com.test.community;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.foodcloud.community.model.pages.DataTriplet;
import com.foodcloud.community.model.pages.OrganizationPage;
import com.foodcloud.community.model.pages.Section;
import com.foodcloud.community.model.pages.SectionData;
import com.foodcloud.test.server.FCTestNavigator;
import com.foodcloud.test.server.FCTestServer;

public class ReadCharityLink {

	FCTestServer server;
	private String URL = "https://community.food.cloud/#!/org?orgId=80&edit=true&form=charity-due-dil-form&section=food-types";
		
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
		
	 @Test(dataProvider = "foodTypeData", dataProviderClass = readCharityData.class)
	public void verifyOpenLink_FoodTypeSection_SetAndGetData(SectionData sectionData, ArrayList<String>  expectedResults) {
	//	String[] expectedStoredData = {"15000",  "Goats Milk", "Steak",  "Banana"} ;
		 String section = sectionData.getName();
		List<DataTriplet> fields = sectionData.getFields();
		 
		OrganizationPage orgPage = new OrganizationPage(nav);
		orgPage.addSection(new Section(nav, sectionData));

		System.out.println("0/1: " + fields.get(0).getName());
		System.out.println("1/2: " + fields.get(1).getName());
		System.out.println("2/3: " + fields.get(2).getName());
		System.out.println("3/4: " + fields.get(3).getName());
		System.out.println("4/5: " + fields.get(4).getName());
		System.out.println("5/6: " + fields.get(5).getName());

		System.out.println("result : " + expectedResults.get(0));
		System.out.println("result : " + expectedResults.get(1));
		System.out.println("result : " + expectedResults.get(2));
		System.out.println("result : " + expectedResults.get(3));
		System.out.println("result : " + expectedResults.get(4));
		System.out.println("result : " + expectedResults.get(5));

		
		// "Monthly Spend"
		//"Top Dairy"
		//"Top Meat"
		//"Top FruitVeg"
		orgPage.getSection(section).setField(fields.get(0).getName(), expectedResults.get(0) );
		orgPage.getSection(section).setField(fields.get(1).getName(), expectedResults.get(1) );
		orgPage.getSection(section).setField(fields.get(2).getName(), expectedResults.get(2) );
		orgPage.getSection(section).setField(fields.get(5).getName(), expectedResults.get(5) );
	
		server.waitForActionToComplete();

		String actualStoredData = (String) orgPage.getSection(section).getField(fields.get(0).getName());
		
		Assert.assertEquals(actualStoredData, expectedResults.get(0), "Error: New value not displayed.");

		} 
	
	
	
	@Test (priority = 2, dataProvider = "foodSafetyData", dataProviderClass = readCharityData.class)
	public void verifyOpenLink_FoodSafetySection_SetAndGetData(String section, ArrayList<DataTriplet>  fields) {
		
		String[] expectedStoredData = {"true",  "true", "true", "level 11", "Banana"} ;

		//adding fields to section
		OrganizationPage orgPage = new OrganizationPage(nav);
		orgPage.addSection(new Section(nav,section,fields));

		System.out.println("0/4: " + fields.get(4).getName());
		System.out.println("1/5: " + fields.get(5).getName());
		System.out.println("2/6: " + fields.get(6).getName());
		System.out.println("3/12: " + fields.get(12).getName());
		System.out.println("4/13: " + fields.get(13).getName());
		System.out.println("5/14: " + fields.get(14).getName());


		// "Food Supplier List"
		//"Appropriate Transport"
		//"Food Records"
		//""Storage Facilities"
		//Food Safety Training
		//"Safety Training System"
		
		//adding values to fields 
		orgPage.getSection(section).setField(fields.get(4).getName(), expectedStoredData[0]); // "true" );
		orgPage.getSection(section).setField(fields.get(5).getName(), expectedStoredData[1] ); //"true" );
		orgPage.getSection(section).setField(fields.get(12).getName(), expectedStoredData[2] ); //"true" );
		orgPage.getSection(section).setField(fields.get(13).getName(), expectedStoredData[3] ); //"false" );
		orgPage.getSection(section).setField(fields.get(14).getName(), expectedStoredData[4] ); //"Banana" );
	
		server.waitForActionToComplete();

		Boolean actualSuppliers = (Boolean) orgPage.getSection(section).getField(fields.get(4).getName());
		Boolean actualTransport = (Boolean) orgPage.getSection(section).getField(fields.get(5).getName());
		Boolean actualStorage = (Boolean) orgPage.getSection(section).getField(fields.get(12).getName());
		String actualSafetyTraining = (String) orgPage.getSection(section).getField(fields.get(13).getName());
	//	Boolean actualRecords = 
		String actualFoodSafety = (String) orgPage.getSection(section).getField(fields.get(14).getName());
		
		Assert.assertTrue(actualSuppliers, "Error: New value not displayed.");
		Assert.assertTrue(actualTransport, "Error: New value not displayed.");
		Assert.assertTrue(actualStorage,  "Error: New value not displayed.");
		Assert.assertEquals(actualSafetyTraining,  expectedStoredData[3],  "Error: New value not displayed.");
		Assert.assertEquals(actualFoodSafety, expectedStoredData[4], "Error: New value not displayed.");

		} 

	
	
//	@Test (priority = 3) // data source??
//	public void verifyOpenLink_TermsAndConsSection_SaveData() {				
//		
//		List<DataTriplet> list = new ArrayList();
//		list.add(new DataTriplet("Marketing Permission" , DataTriplet.FieldType.CHECKBOX, "css=input[name='marketing-opt-in']")); 
//		list.add(new DataTriplet("Terms and Conditions" , DataTriplet.FieldType.CHECKBOX, "css=input[name='terms-conditions']")); 
//	
//		OrganizationPage orgPage = new OrganizationPage(nav);
//		orgPage.addSection(new Section(nav,"Terms and Conditions",list));
//
//		//adding fields to section
//		orgPage.getSection("Terms and Conditions").setField("Marketing Permission", "true" );
//		orgPage.getSection("Terms and Conditions").setField("Terms and Conditions", "true" );
//
//		orgPage.clickSave();
//		
//	}
	
	@AfterClass	
	public void tearDown(){
		server.getDriver().close();
	}
	
}

