package com.test;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.*;
import org.testng.Assert;

import java.io.FileNotFoundException;

import com.foodcloud.model.pages.DashboardPage;
import com.foodcloud.model.pages.LoginPage;
import com.foodcloud.model.pages.OptionMenu.MENU_ITEMS;
import com.foodcloud.test.server.FCTestNavigator;
import com.foodcloud.test.server.FCTestServer;

/**
 * 
 * Trial page :) 
 * Need a way to extract data from file (custom file) 
 * Need a way to  :  data-driven testing (with @DataProvider and/or XML configuration).
 */
public class LoginMobileTest {

	FCTestServer server;
//	private String Url = "http://copiarms.herokuapp.com/";
	private String Url = "http://copia-c.herokuapp.com/";
	private FCTestNavigator nav; 

	@BeforeClass
	public void setupBeforeClass() throws FileNotFoundException {
		
	nav = new FCTestNavigator();
	
	nav.setup();		

	server = nav.getServer();

	server.openURL(Url);
	
	} 
	
	
	/**
	 * User Story:
	 * Given correct username and password, When I login in Copia, (then) I want to land Dashboard page 
	 */
	@Test ( priority = 1 )
	public void verifyLoginOK_DashboardPageDisplay() {
		
		String username = "im.admin@foodcloud.ie"; 
		String password = "copiate2015";

		LoginPage loginPage = new LoginPage(nav);
		loginPage.setLoginField(username);
		loginPage.setPasswordField(password);
		loginPage.clickSubmit();
		
		String titleLocator = "//div[@class='copia-top row']";
		server.waitForElement(server.getLocatorType(titleLocator));	

		boolean isPagePresent =  server.isElementPresent(titleLocator);
		
		Assert.assertTrue(isPagePresent, "Error: Dashboard Page not displayed as expected");

	}

	/**
	 * User Story:
	 * Given a user logged in the system, When I click into Support Tickets menu item, (then) I want to land Support Ticket page 
	 */
	@Test ( priority = 2)
	public void verifyLoginSuccessful() {
		
 		DashboardPage dashPage = new DashboardPage(nav);
		dashPage.getMenu().clickItem(MENU_ITEMS.SUPPORT_TICKETS);

		String titleLocator = "//h2/span[@translate='Issues']";
		server.waitForElement(server.getLocatorType(titleLocator));

		boolean isPagePresent =  server.isElementPresent(titleLocator);
		
		Assert.assertTrue(isPagePresent, "Error: Support Tickets Page not displayed as expected");
	}

	@AfterMethod
	@AfterClass	
	public void tearDown(){
		server.getDriver().close();
	}


/**	
	C:\Program Files (x86)\Appium\node_modules\appium\node_modules
	
	File uri = "C:/Program Files (x86)/Appium/node_modules/appium/node_modules/node-idevice/main.js";

	File uri = "/usr/local/lib/node_modules/appium/build/lib/main.js";
	
	AppiumServiceBuilder builder = new AppiumServiceBuilder()
            .withAppiumJS(new File("/usr/local/lib/node_modules/appium/build/lib/main.js"))
            .withArgument(GeneralServerFlag.APP, System.getProperty("user.dir") + "/build/wordpress.apk")
            .withArgument(GeneralServerFlag.LOG_LEVEL, "info")
            .withLogFile(new File(System.getProperty("user.dir") + "/target/logs/sample.txt"))
            .usingAnyFreePort()
    //;
    appiumDriverLocalService = builder.build();
    appiumDriverLocalService.start();
*/

}
