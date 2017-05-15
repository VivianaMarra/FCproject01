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
//	private String URL = "https://community.food.cloud/#!/org?orgId=64&edit=true&form=charity-due-dil-form&section=food-types";

	private String URL = "https://community.food.cloud/#!/org?orgId=68&edit=true&form=charity-due-dil-form&section=food-types";
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
	
	// verify Sections presence 
	//	is there any locators for sections???
	// section id= edit-food-types
	
	
	
	
	
	
	/**
	 * Jira: 
	 * User Story:
	 * Given I am a new user, When I click on create new Organization, (then) I want to land on creation page 
	 * PROBLEM: Organization page to be recreated ALL the time ( for each test)
	 */
	@Test ( priority = 1 ) // data source
	public void verifyOpenLink_CanReadData() {
		String expectedStoredData = "15000";
				
		
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

					
	@AfterClass	
	public void tearDown(){
		server.getDriver().close();
	}
	
}

