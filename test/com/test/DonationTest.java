package com.test;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.FileNotFoundException;
import com.foodcloud.model.pages.DashboardPage;
import com.foodcloud.model.pages.DonationsPage;
import com.foodcloud.model.pages.LoginPage;
import com.foodcloud.model.pages.OptionMenu.MENU_ITEMS;
import com.foodcloud.model.pages.SearchMenu;
import com.foodcloud.model.tables.ResultTable;
import com.foodcloud.model.tables.TableRow;
import com.foodcloud.test.server.FCTestNavigator;
import com.foodcloud.test.server.FCTestServer;

/**
 * 
 * Trial page :) 
 * Need a way to extract data from file (custom file) 
 * Need a way to  :  data-driven testing (with @DataProvider and/or XML configuration).
 */
public class DonationTest {

	FCTestServer server;
	private String URL = "http://copia-c.herokuapp.com/";
//	private String URL = "https://foodcloud.eu.ngrok.io/dashboard";
	private FCTestNavigator nav; 
	
	@BeforeClass
	public void setupBeforeClass() throws FileNotFoundException {
		
	nav = new FCTestNavigator();
	nav.setup();	
	server = nav.getServer();
		
	server.openURL(URL);

	String username = "im.admin@foodcloud.ie"; 
	String password = "copiate2015";
	String adminTitleLocator = "//div[@class='copia-top row']";
	
//	String username = "4045@uk.tesco.com";
//	String password = "12345";
//	String userTitleLocator = "//div[@class='span12 org-header']";

	
	LoginPage loginPage = new LoginPage(nav);
	loginPage.setLoginField(username);
	loginPage.setPasswordField(password);
	loginPage.clickSubmit();
	
	String titleLocator = adminTitleLocator;	
	//String titleLocator = userTitleLocator;
	
	server.waitForElement(server.getLocatorType(titleLocator));				
	} 
	
	
	// test ideas donation page:
	// open page, returning latest results (not empty table) 	
//		string search criteria  without result, returning empty table 
//		string search criteria  with result, returning table with rows on one page / multiple pages
	// verify returning pages via API / DB??  	

	/**
	 * Jira: 
	 * User Story:
	 * Given I am logged into Copia, When I click on Donations, (then) I want to land on Donations page 
	 */
	@Test ( priority = 1 )
	public void verifyClickingOnDonations_OpensDonationsPage() {
		String normalUserTitleLocator = "id=donation-table-container";
		String adminTitleLocator = "//h2/span[@translate='Donations']";
		
		String titleLocator = normalUserTitleLocator; // adminTitleLocator;
		
 		DashboardPage dashPage = new DashboardPage(nav);
		dashPage.getMenu().clickItem(MENU_ITEMS.DONATIONS);
		
		server.waitForElement(server.getLocatorType(titleLocator));
		
		DonationsPage donationsPage = new DonationsPage(nav);
		server.waitForActionToComplete();

		boolean isEmpty = donationsPage.getResultTable().load().isEmpty();	
		
		Assert.assertFalse(isEmpty, "Error: Donations Page with data not displayed.");

		} 
	
	/**
	 * Jira: 
	 * User Story:
	 * Given I am logged into Copia as Search User, When I type an incorrect search criteria, (then) I want the Donations page to display an empty table.
	 * Note: Search criteria can vary from user to user depending on Role / Organization / Region they have access to 
	 */
	@Test(priority = 2)
	public void verifyDonationsSearchCriterion_ReturningEmptyTable() {
		DonationsPage donationsPage = new DonationsPage(nav);
		
		server.waitForActionToComplete();
		
		SearchMenu searchMenu = donationsPage.getSearchMenu().open();	//not working for all pages 
		searchMenu.setField(DonationsPage.DONOR_FIELD_NAME,"Thursoup");

		server.waitForActionToComplete();

		boolean isEmpty = donationsPage.getResultTable().load().isEmpty();	
		
		Assert.assertTrue(isEmpty, "Error: Donations Page no results not displayed.");
	}

	@Test(priority = 3) // , enabled = false)
	public void verifyDonationsSearchCriterion_ReturningFullTableSinglePage() {
		DonationsPage donationsPage = new DonationsPage(nav);		
		SearchMenu searchMenu = donationsPage.getSearchMenu().clear();

		server.waitForActionToComplete();
		
		ResultTable table = donationsPage.getResultTable().load().read();
		int elementListSize = table.getRowList().size(); 
		
		//array size vary according to browser size: 8 rows in full screen, 7 in resized ones 
	//	Assert.assertEquals(elementListSize, 8, "Error: Donations Page nunmber of results not matching.");
		Assert.assertEquals(elementListSize, 7, "Error: Donations Page nunmber of results not matching.");

	}

	@Test ( priority = 4 , enabled = false)
	public void verifyDonationsSearchCriterion_ReturningFullTableMultiplePages() {
		Assert.fail("Not impl yet");		
	}
	
	
	
	@Test(priority = 5) 
	public void verifyDonationsSearchCriterion_ReadingSingleRow() {
		String[] expectedResults = {"27-Apr-2017","17:25:06", "RawChocolate Store 1", "FC:4344","Friends of Ballacottier School","20:00 to 21:30","Collected"}; 
				
			//{"27-Apr-2017","17:27:06", "Waitrose Douglas", "WAITROSE:1234","Acorn","11:00 to 11:30","Offered"}; 
				
		DonationsPage donationsPage = new DonationsPage(nav);		
		SearchMenu searchMenu = donationsPage.getSearchMenu().clear();
		
		server.waitForActionToComplete();
		
		ResultTable table = donationsPage.getResultTable().load().read();	
		TableRow row = table.getRow(1);
		
		String date = row.getDate();
		String time = row.getTime();
		String donor = row.getDonor();
		String donorCode = row.getDonorCode();
		String charity = row.getCharity();
		String collection = row.getCollectionWindow();
		String status = row.getStatus();
		
		Assert.assertEquals(date,expectedResults[0], "Error: Donations Page no results not displayed as expected");
		Assert.assertEquals(time,expectedResults[1], "Error: Donations Page no results not displayed as expected");
		Assert.assertEquals(donor, expectedResults[2], "Error: Donations Page no results not displayed as expected");
		Assert.assertEquals(donorCode,expectedResults[3], "Error: Donations Page no results not displayed as expected");
		Assert.assertEquals(charity,expectedResults[4], "Error: Donations Page no results not displayed as expected");
		Assert.assertEquals(collection,expectedResults[5], "Error: Donations Page no results not displayed as expected");
		Assert.assertEquals(status,expectedResults[6], "Error: Donations Page no results not displayed as expected");

	}

	
	@Test(priority = 6 , enabled = false) 
	public void verifyDonationsSearchCriterion_OpeningTimeLine() {
		String[] expectedResults = {"06-Apr-2017","14:24:53", "Waitrose Douglas", "WAITROSE:1234","Acorn","11:00 to 11:30","Offered"}; 
				
		DonationsPage donationsPage = new DonationsPage(nav);		
		SearchMenu searchMenu = donationsPage.getSearchMenu().clear();	
		
		server.waitForActionToComplete();
		
		ResultTable table = donationsPage.getResultTable().load().read();	
		TableRow row = table.getRow(1);
		
		nav.getServer().click(row.getIconLocator());

	}
	
	@AfterClass	
	public void tearDown(){
		server.getDriver().close();
	}
	
}
