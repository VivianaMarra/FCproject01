package com.test;

import org.testng.Assert;
import org.testng.annotations.*;

import java.io.FileNotFoundException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.foodcloud.model.pages.DashboardPage;
import com.foodcloud.model.pages.LoginPage;
import com.foodcloud.model.pages.OptionMenu.MENU_ITEMS;
import com.foodcloud.model.pages.SearchMenu;
import com.foodcloud.model.pages.SupportTicketsPage;
import com.foodcloud.test.server.FCTestNavigator;
import com.foodcloud.test.server.FCTestServer;

/**
 * 
 * Trial page :) 
 * Need a way to extract data from file (custom file) 
 * Need a way to  :  data-driven testing (with @DataProvider and/or XML configuration).
 */
public class SupportTest {

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
	
	
	/**
	 * Jira: 
	 * User Story:
	 * Given correct username and password, When I login in Copia, (then) I want to land Dashboard page 
	 */
	@Test ( priority = 1 )
	public void verifySupportTicket_Search() {
		
		String titleLocator = "//h2/span[@translate='Issues']";
		
 		DashboardPage dashPage = new DashboardPage(nav);
		dashPage.getMenu().clickItem(MENU_ITEMS.SUPPORT_TICKETS);
		
		server.waitForSpinnerToDisappear();
		server.waitForElement(server.getLocatorType(titleLocator));
	
		SupportTicketsPage ticketsPage = new SupportTicketsPage(nav);
		SearchMenu searchMenu = ticketsPage.getSearchMenu().open();	
		
		searchMenu.setField(SupportTicketsPage.DONOR_FIELD_NAME,"Stephen DemoStore");
			
		server.waitForSpinnerToDisappear();
		server.waitForElement(server.getLocatorType(titleLocator));
	
		boolean isPagePresent =  server.isElementPresent(titleLocator);
		ticketsPage.getSearchMenu().close(); 	
		//what data should be asserted ?? 
		Assert.assertTrue(isPagePresent, "Error: Support Tickets Page not displayed as expected");

		} 

	
	@Test ( priority = 5)
	public void verifySupportTicket_SearchForNull() {
		
		SupportTicketsPage ticketsPage = new SupportTicketsPage(nav);
		SearchMenu searchMenu = ticketsPage.getSearchMenu().open();	
		
		//TODO: find an easy way to clean up fields before another search 
		searchMenu.cleanField(SupportTicketsPage.DONOR_FIELD_NAME);

		searchMenu.setField(SupportTicketsPage.STORE_FIELD_NAME,"2070");

		server.waitForSpinnerToDisappear();

		ticketsPage.getSearchMenu().close(); 	
		
	//	server.waitForElement(server.getLocatorType(titleLocator), server.getDriver());
		
	//	boolean isPagePresent =  server.isElementPresent(titleLocator);
		//what data should be asserted ?? 
	//	Assert.assertTrue(isPagePresent, "Error: Support Tickets Page not displayed as expected");
		} 
	
	@Test ( priority = 7 )
	public void verifySupportTicket_SearchForDate() {
		
		SupportTicketsPage ticketsPage = new SupportTicketsPage(nav);
		SearchMenu searchMenu = ticketsPage.getSearchMenu().open();	

		//TODO: find an easy way to clean up fields before another search 
		searchMenu.cleanField(SupportTicketsPage.STORE_FIELD_NAME);

		searchMenu.setField(SupportTicketsPage.TICKET_DATE_FIELD_NAME,"20170320");
		server.waitForSpinnerToDisappear();

		ticketsPage.getSearchMenu().close(); 	

	// ul.uib-datepicker-popup
	}

	@AfterClass	
	public void tearDown(){
		server.getDriver().close();
	}
	
}
