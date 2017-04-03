package com.test;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.FileNotFoundException;
import com.foodcloud.model.pages.DashboardPage;
import com.foodcloud.model.pages.DonationsPage;
import com.foodcloud.model.pages.LoginPage;
import com.foodcloud.model.pages.OptionMenu.MENU_ITEMS;
import com.foodcloud.model.tables.ResultTable;
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
	private FCTestNavigator nav; 
	
	@BeforeClass
	public void setupBeforeClass() throws FileNotFoundException {
		
	nav = new FCTestNavigator();
	nav.setup();	
	server = nav.getServer();
		
	server.openURL(URL);

	String username = "im.admin@foodcloud.ie"; 
	String password = "copiate2015";

	LoginPage loginPage = new LoginPage(nav);
	loginPage.setLoginField(username);
	loginPage.setPasswordField(password);
	loginPage.clickSubmit();
	
	String titleLocator = "//div[@class='copia-top row']";
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
		//normal user?? ng-show="showDonations"
		
		String titleLocator = "//h2/span[@translate='Donations']";
		
 		DashboardPage dashPage = new DashboardPage(nav);
		dashPage.getMenu().clickItem(MENU_ITEMS.DONATIONS);
		
		server.waitForSpinnerToDisappear();
		server.waitForElement(server.getLocatorType(titleLocator));
	
		DonationsPage donationsPage = new DonationsPage(nav);
		boolean isEmpty = donationsPage.getResultTable().isEmpty();	
		
		Assert.assertFalse(isEmpty, "Error: Donations Page with data not displayed as expected");

		} 
	
	//string search criteria  without result, returning empty table 
	@Test ( priority = 2 )
	public void verifyDonationsSearchCriterion_ReturningEmptyTable() {
		DonationsPage donationsPage = new DonationsPage(nav);
		
	//	SearchMenu searchMenu = donationsPage.getSearchMenu().open();	//not working
	//	searchMenu.setField(DonationsPage .DONOR_FIELD_NAME,"Thursoup");
		server.waitForSpinnerToDisappear();
		
		boolean isEmpty = donationsPage.getResultTable().isEmpty();	
		
		Assert.assertTrue(isEmpty, "Error: Donations Page no results not displayed as expected");

	}

	@Test ( priority = 3 )
	public void verifyDonationsSearchCriterion_ReturningFullTableSinglePage() {
		DonationsPage donationsPage = new DonationsPage(nav);		
		server.waitForSpinnerToDisappear();
		
		ResultTable table = donationsPage.getResultTable().read();	
		String date = table.getRow(1).getDate();
		
	//	Assert.assertEquals(date,"2017-03-30", "Error: Donations Page no results not displayed as expected");
		Assert.assertEquals(date,"31-Mar-2017", "Error: Donations Page no results not displayed as expected");

	}

	@Test ( priority = 4 )
	public void verifyDonationsSearchCriterion_ReturningFullTableMultiplePages() {
		Assert.fail("Not impl yet");		
	}
	
	
	@AfterClass	
	public void tearDown(){
		server.getDriver().close();
	}

		
	
	
	
}
