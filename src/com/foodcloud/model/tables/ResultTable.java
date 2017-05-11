package com.foodcloud.model.tables;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.foodcloud.model.pages.Page;
import com.foodcloud.test.server.FCTestNavigator;
import com.foodcloud.test.server.FCTestServer;

public class ResultTable {

	private String tableLocator;
	private FCTestNavigator nav;
	private FCTestServer server;
	private List<TableRow> rowElements;
	private Page currentPage;
	private String tableBodyLocator = "./descendant::tbody[@id='don-data']";
	private String tableRowLocator =	tableBodyLocator + "/tr";
	private String genericRowLocator = "./descendant::tbody/tr";
	private WebDriver driver;
	
	/**
	 * creates a new resultTable object. Expecting Xpath expression as table locator 
	 * @param navigator FoodCloudTestNavigator instance
	 * @param locator String representing Xpath locator
	 * @param page current page (should be Result page but also some read only page have ResultTable components)
	 */
	public ResultTable(FCTestNavigator navigator, String locator, Page page) {
		this.tableLocator = locator;
		this.nav = navigator;	
		this.server = navigator.getServer();	
		this.rowElements= new ArrayList<TableRow>();
		this.currentPage = page; 
		this.driver = navigator.getDriver();	

	}
	
	/**
	 * checks if the results table is empty or not.
	 * @return true if empty, false otherwise
	 */
	public boolean isEmpty() {
		//checking 
		if (server.isElementPresent(tableLocator,tableRowLocator)) {
			return false;
		} else {
			return true;
		}
	}

//	
//	public ResultTable load() {
//		String genericTableLocator = genericRowLocator + " | " + tableBodyLocator;
//				
//		server.waitForElement(genericTableLocator);
//		WebElement el = nav.getDriver().findElement(server.getLocatorType(tableLocator));
//		el.findElement(server.getLocatorType(genericTableLocator));
//		
//	//	server.isElementPresent(tableLocator, tableBodyLocator);
//		
//		return this;
//	}

	public ResultTable load() {
		server.waitForElement(tableLocator, tableBodyLocator);

		server.isElementPresent(tableLocator,tableRowLocator);
		
		return this;
	}
	
	
	public ResultTable read() {			

		if(!isEmpty()) {

			WebElement parent = driver.findElement(server.getLocatorType(tableLocator));
			
			List<WebElement> elements = parent.findElements(server.getLocatorType(tableRowLocator)); //(genericRowLocator));
			int index=1;
			for (Iterator<WebElement> iterator = elements.iterator(); iterator.hasNext();) {
				WebElement webEl = (WebElement) iterator.next();
				
				String baseLocator = tableRowLocator.replace('.', '/') + "[" + index + "]";
		
				String date = driver.findElement(By.xpath(baseLocator +"/td[@id='don.date']")).getText();
				String time = driver.findElement(By.xpath(baseLocator +"/td[@id='don.posted']")).getText();
				String donorName = driver.findElement(By.xpath(baseLocator +"/td[@id='don.donor']")).getText();
			 	String donorNumber = driver.findElement(By.xpath(baseLocator +"/td[@id='don.donorStoreId']")).getText();
			 	String charity = driver.findElement(By.xpath(baseLocator +"/td/a[i[@ng-if='row.charitySupport']]")).getText().trim();
			 	String collectionWindow = driver.findElement(By.xpath(baseLocator +"/td[@id='don.window']")).getText();
			 	String status = driver.findElement(By.xpath(baseLocator +"/td[@id='don.status']/span")).getText();

			 	String controlLocator = baseLocator +"/td[div[button[@id='actions']]]";
			 	String timeLineLocator = baseLocator +"/td/div/span[@class='fa fa-clock-o']";
				String infoLocator = baseLocator +"/td[@id='don.details']";


				rowElements.add(new TableRow(this, timeLineLocator , date, time, infoLocator, donorName, donorNumber, charity, collectionWindow, status, controlLocator ));				
				index = index+1;
				
			}
		}
		
		return this;		
	}

	public FCTestServer getServer() {
		return this.server;
		
	}

	public TableRow getRow(int i) {
		if(!rowElements.isEmpty()) {
			return this.rowElements.get(i-1);
		} else
			return null;		
	}
	
	public List<TableRow> getRowList() {
		return this.rowElements;
	}
	
	//wip
//	public ResultTable read() {			
//
//		if(!isEmpty()) {
//
//			WebElement parent = nav.getDriver().findElement(server.getLocatorType(tableLocator));
//			
//			List<WebElement> elements = parent.findElements(server.getLocatorType(tableRowLocator)); //(genericRowLocator));
//			int index=1;
//			for (Iterator<WebElement> iterator = elements.iterator(); iterator.hasNext();) {
//				WebElement webEl = (WebElement) iterator.next();
//				
//				String baseLocator = tableRowLocator.replace('.', '/') + "[" + index + "]";
//		
//				String date = nav.getDriver().findElement(By.xpath(baseLocator +"/td[@id='don.date']")).getText();
//				String time = nav.getDriver().findElement(By.xpath(baseLocator +"/td[@id='don.posted']")).getText();
//				String infoLocator = tableRowLocator + "[" + index + "]/td[@id='don.details']";
//			//	donorLocator = don.donor
//			//	//String donorName = webEl.findElement(By.xpath("./")).getText();
//			//	String donorNumber = webEl.findElement(By.xpath("./")).getText();
//				
//				rowElements.add(new TableRow(this, date, time, infoLocator));
//				
//				//new TableRow(nav, date, time, infoLocator)
//				//, donorName, donorNumber, charity, collectionWindow, status, controlLocator );
//				
//				index = index+1;
//				
//			}
//		}
//		
//		return this;		
//	}

	
}
