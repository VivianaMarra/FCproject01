package com.foodcloud.test.server;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * FoodCloud Test Server 
 * This class is wrapping Selenium webdriver object and contains all utilities methods 
 *
 * @author Viviana
 */
public class FCTestServer {

	private WebDriver driver;
	public static int WAIT_TIMEOUT = 120;
	private static String HIDDEN_SPINNER_LOCATOR = "css=th[class='ng-scope'] i.fa-spinner.ng-hide , th[class='ng-scope'] i.fa-refresh";
//	private static String VISIBLE_SPINNER_LOCATOR = "css=i.fa-refresh";
	
	public FCTestServer(WebDriver webDriver) {
		this.driver = webDriver;
	}

	/**
	 * waits for an object or element on a page until it displays (or generates a timeout exception)  
	 * implements Explicit wait
	 * @param  By object representing the element locator 
	 * @return expectedWebElement on page 
	 */
	public WebElement waitForElement(By elementLocator) {

		System.out.println("Waiting for webelement with locator:" + elementLocator);
		
		WebElement expectedWebElement = 
		new WebDriverWait(driver, WAIT_TIMEOUT).until(ExpectedConditions.presenceOfElementLocated(elementLocator));
	  

		
		return expectedWebElement;
	}

	/**
	 * waits for an object or element on a page, identified with a String locator, until it displays (or generates a timeout exception)  
	 * implements Explicit wait
	 * @param  String object representing the element locator 
	 * @return expectedWebElement on page 
	 */
	public WebElement waitForElement(String elementLocator) {
		WebElement expectedWebElement = 
		new WebDriverWait(driver, WAIT_TIMEOUT).until(
				ExpectedConditions.presenceOfElementLocated(this.getLocatorType(elementLocator)));
	  
		return expectedWebElement;
	}

	
	
	public WebElement waitForElement(String parentLocator, String elementLocator) {
		WebElement expectedWebElement = null;
		
		WebElement parentElement = 
		new WebDriverWait(driver, WAIT_TIMEOUT).until(
				ExpectedConditions.presenceOfElementLocated(this.getLocatorType(parentLocator)));

		System.out.println("Parent element loaded");
		
		List<WebElement> childList = 
				parentElement.findElements(this.getLocatorType(elementLocator));
		
		if(!childList.isEmpty()){
			expectedWebElement = childList.get(0);
			
			System.out.println("child element loaded");
		}
		
		return expectedWebElement;
	}

	
	/**
	 * Types a text string in a field given the locator 
	 * @param fieldLocator
	 * @param value
	 * @param driver
	 */
	public void type(String fieldLocator, String value) {
		WebElement we = driver.findElement(getLocatorType(fieldLocator));		
		we.clear();
		we.sendKeys(value);		
	} 
	
	
	public By getLocatorType(String locator){
		By by = null;
		
		if(locator.contains("//") || locator.contains("./")) {
			by = By.xpath(locator);
		}
		else if(locator.contains("css=")) {
			by = By.cssSelector(locator.substring(4));
		}
		else if(locator.contains("id=")) {
			by = By.id(locator.substring(3));
		}
		else if(locator.contains("link=")) {
			by = By.linkText(locator.substring(5));
		}
		else if(locator.contains("name=")) {
			by = By.name(locator.substring(5));
		}
				
		return by; 
	}

	public WebDriver getDriver() {
		return driver;
	}

	/**
	 * checks for the presence of an object or element on a page.   
	 * @param elementLocator String representing the object locator
	 * @return true if element present, false otherwise
	 */
	public boolean isElementPresent(String elementLocator) {
		WebElement we = null;
		try {
		
			we = driver.findElement(getLocatorType(elementLocator));		
		
		}
		catch (NoSuchElementException e) {
			//TODO: add  CAT -  Log exception e
			return false;
		} 
		
		if(we != null){
			return true;
		}
		else {		
			return false;
		}
	}

	
	public boolean isElementPresent(String parentLocator, String childLocator) {
		WebElement we = null;
		try {
		
			WebElement parent = driver.findElement(getLocatorType(parentLocator));		
		
			we = parent.findElement(getLocatorType(childLocator));
		}
		catch (NoSuchElementException e) {
			//TODO: add  CAT -  Log exception e
			return false;
		} 
		
		if(we != null){
			return true;
		}
		else {		
			return false;
		}
	}

	
	/**
	 * opens a web page using the given URL.
	 * @param targetUrl String representing URL
	 */
	public void openURL(String targetUrl) {
		driver.get(targetUrl);
		
	}

	/**
	 * clicks on a specific object given an existing string locator that uniquely identifies the object
	 * @param fieldLocator
	 */
	public void click(String fieldLocator) {
	
		WebElement we = driver.findElement(getLocatorType(fieldLocator));		
		we.click(); 
	}
	
	
	public WebElement waitForSpinnerToDisappear() {
					
		//debugging
		String locator = "css=i[ng-show='isLoading']";		
		String locator2 = "css=i[ng-show='hideOrgName']";		
//		
//		String object1Class = driver.findElement(getLocatorType(locator)).getAttribute("class");
//		String object2Class = driver.findElement(getLocatorType(locator2)).getAttribute("class");
//		System.out.println("before waiting Visible");
//		System.out.println("object 1 class" + object1Class );
//		System.out.println("object 2 class" + object2Class );
//
//		
//		System.out.println("waiting for the spinner to be visible");
//
//		WebElement expectedWebElement = 
//		new WebDriverWait(driver, WAIT_TIMEOUT).until(
//				
//				ExpectedConditions.presenceOfElementLocated(this.getLocatorType(VISIBLE_SPINNER_LOCATOR))
//				);
//
		
		System.out.println("waiting for the spinner to be invisible");
		//debugging
		String object3Class = driver.findElement(getLocatorType(locator)).getAttribute("class");
		String object4Class = driver.findElement(getLocatorType(locator2)).getAttribute("class");
		System.out.println("after waiting Visible");
		System.out.println("object 1 class" + object3Class );
		System.out.println("object 2 class" + object4Class );
		
		System.out.println("waiting for the spinner to be invisible");

		WebElement expectedWebElement = new WebDriverWait(driver, WAIT_TIMEOUT).until(
				
				ExpectedConditions.presenceOfElementLocated(this.getLocatorType(HIDDEN_SPINNER_LOCATOR))
				);
		
		
		//debugging
		String object5Class = driver.findElement(getLocatorType(locator)).getAttribute("class");
		String object6Class = driver.findElement(getLocatorType(locator2)).getAttribute("class");
		System.out.println("after waiting invisible");
		System.out.println("object 1 class" + object5Class );
		System.out.println("object 2 class" + object6Class );


		return expectedWebElement;

	}

	/**
	 * waits for an action to be completed.
	 * (work in progress: currently implemented as thread sleep)
	 */
	public void waitForActionToComplete() {
		try {
			System.out.println("waiting to complete");
			
			Thread.sleep( WAIT_TIMEOUT * 100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	
	public void select(String path, String value) {

		WebElement we = getDriver().findElement(this.getLocatorType(path));
				
		we.sendKeys(value);
	
	}

	public void checkbox(String currentLocator, boolean setCheckbox) {

		if(setCheckbox) {
			this.click(currentLocator);
		} 
		// add logic when 
	}
	
	
}
