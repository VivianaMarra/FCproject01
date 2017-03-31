package com.foodcloud.model.tables;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.foodcloud.model.pages.Page;
import com.foodcloud.test.server.FCTestNavigator;
import com.foodcloud.test.server.FCTestServer;

public class ResultTable {

	private String tableLocator;
	private FCTestNavigator nav;
	private List<TableRow> rowElements;
	private Page currentPage;
	private String genericRowLocator = "//tbody[@id='don-data']/tr";
	
	public ResultTable(FCTestNavigator navigator, String locator, Page page) {
		this.tableLocator = locator;
		this.nav = navigator;	
		this.rowElements= new ArrayList<TableRow>();
		this.currentPage = page; 
	}
	
	//is table Empty
	// empty = false 
	public boolean isEmpty() {
		// table locator??
		if (nav.getServer().isElementPresent(genericRowLocator)) {
			return false;
		} else {
			return true;
		}
	}

	public ResultTable read() {
			
		if(!isEmpty()) {

			List<WebElement> elements = nav.getDriver().findElements(nav.getServer().getLocatorType(genericRowLocator));
			int index=1;
			for (Iterator iterator = elements.iterator(); iterator.hasNext();) {
				WebElement webEl = (WebElement) iterator.next();
				
				String baseLocator = genericRowLocator + "[" + index + "]";
				
				String date = webEl.findElement(By.xpath(baseLocator +"/td[@id='don.date']")).getText();
				String time = webEl.findElement(By.xpath(baseLocator +"/td[@id='don.posted']")).getText();
				String infoLocator = genericRowLocator + "[" + index + "]/td[@id='don.details']";
			//	donorLocator = don.donor
			//	//String donorName = webEl.findElement(By.xpath("./")).getText();
			//	String donorNumber = webEl.findElement(By.xpath("./")).getText();
				
				rowElements.add(new TableRow(this, date, time, infoLocator));
				
				//new TableRow(nav, date, time, infoLocator)
				//, donorName, donorNumber, charity, collectionWindow, status, controlLocator );
				
				index = index+1;
				
			}
		}
		
		return this;		
	}

	public FCTestServer getServer() {
		return this.nav.getServer();
		
	}

	public TableRow getRow(int i) {
		if(!rowElements.isEmpty()) {
			return this.rowElements.get(i-1);
		} else
			return null;		
	}
}
