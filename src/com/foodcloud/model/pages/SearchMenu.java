package com.foodcloud.model.pages;

import java.util.HashMap;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.foodcloud.model.dialogs.DialogField;
import com.foodcloud.test.server.FCTestNavigator;
import com.foodcloud.test.server.FCTestServer;

public class SearchMenu {

	private String magnifierLocator = "//div[@class='header']/descendant::i[contains(@class, 'fa-search')][not(contains(@class, 'ng-hide'))]";
	private String magnifierMinusLocator = "//div[@class='header']/descendant::i[contains(@class, 'fa-search-minus')][not(contains(@class, 'ng-hide'))]";

	private String hiddenSearchMenuLocator = "//div[div[@class='header']]/table/thead[@class='ng-hide']";
	private String visibleSearchMenuLocator = "//table[@id]/thead[not(@class='ng-hide')]";
			//"//div[div[@class='header']]/table/thead[not(@class='ng-hide')]";
	
	private WebDriver driver;
	private HashMap<String, DialogField> fields;
	private FCTestNavigator nav;
	private FCTestServer server;
	
	public SearchMenu(FCTestNavigator navigator) {
		this.nav = navigator;
		this.server = navigator.getServer();
		this.driver = navigator.getDriver();
		fields = new HashMap<String, DialogField>();
	}

	
	public SearchMenu open() {

		if(server.isElementPresent(magnifierLocator)) {
		
			server.click(magnifierLocator);
		
	//	driver.findElement(By.xpath(magnifierLocator)).click();
		
			server.waitForElement(By.xpath(visibleSearchMenuLocator));
		}
		return this;
	}


	public SearchMenu close() {
		if(server.isElementPresent(magnifierMinusLocator)){ 
			server.click(magnifierMinusLocator);
			//driver.findElement(By.xpath(magnifierMinusLocator)).click();
		
			server.waitForElement(By.xpath(hiddenSearchMenuLocator));	
		}
		return this;
	}


	public void setField(String fieldName, String value) {
		fields.get(fieldName).setField(value);
	}
	


	public void addField(String fieldName, DialogField genericField) {
		fields.put(fieldName, genericField);
		
	}
	
	public void cleanField(String fieldName) {
		fields.get(fieldName).setField("");
	}

}
