package com.foodcloud.model.pages;

import org.openqa.selenium.WebDriver;

import com.foodcloud.test.server.FCTestNavigator;
import com.foodcloud.test.server.FCTestServer;

public class Page {

	private String id;
	protected WebDriver driver;
	protected FCTestNavigator nav;
	protected FCTestServer server;


	public Page(FCTestNavigator navigator) {
		super();
		this.nav = navigator;
		this.server = navigator.getServer();
		this.driver = navigator.getDriver(); 
	}

	public Page(String id, FCTestNavigator navigator) {
		super();
		this.id = id;
		this.nav = navigator;
		this.server = navigator.getServer();
		this.driver = navigator.getDriver(); 
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	} 
	
	//div[@class='header']/descendant::i[@ng-click='toggleSearch()']
	
}
