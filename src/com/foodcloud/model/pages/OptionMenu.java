package com.foodcloud.model.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.foodcloud.test.server.FCTestNavigator;
import com.foodcloud.test.server.FCTestServer;

public class OptionMenu {

	private static String dashboardLocator = "//a[@translate='Dashboard']";
	private static String ticketsLocator = "//a[@translate='Issues']";
	private static String donationsLocator = "//a[@translate='Donations']";
	private static String collectionsLocator = "//a[@translate='Collections']";
	private static String charitiesLocator = "//a[@translate='Charities']";
	private static String donorsLocator = "//a[@translate='Donors']";
	private static String schedulesLocator = "//a[@translate='Schedules']";
	private static String usersLocator = "//a[@translate='Users']";
	private static String actionsLocator = "//a[@translate='Actions']";
	private WebDriver driver;
	private FCTestNavigator nav;
	private FCTestServer server;

	public static enum MENU_ITEMS { DASHBOARD, SUPPORT_TICKETS, DONATIONS, COLLECTIONS, DONORS, CHARITIES, SCHEDULES,  USERS, ACTIONS  };

	public OptionMenu(FCTestNavigator navigator) {
		this.nav = navigator;
		this.driver = navigator.getDriver();		
		this.server = navigator.getServer();
	}

	public OptionMenu clickItem(MENU_ITEMS item) {
		switch (item) {
		case DASHBOARD:
			driver.findElement(By.xpath(dashboardLocator)).click();
			break;

		case SUPPORT_TICKETS:
			driver.findElement(By.xpath(ticketsLocator)).click();
			break;

		case DONATIONS:
			driver.findElement(By.xpath(donationsLocator)).click();
			break;

		case COLLECTIONS:
			driver.findElement(By.xpath(collectionsLocator)).click();
			break;

		case SCHEDULES:
			driver.findElement(By.xpath(schedulesLocator)).click();
			break;

		case CHARITIES:
			driver.findElement(By.xpath(charitiesLocator)).click();
			break;
			
		case DONORS:
			driver.findElement(By.xpath(donorsLocator)).click();
			break;

		case USERS:
			driver.findElement(By.xpath(usersLocator)).click();
			break;

		case ACTIONS:
			driver.findElement(By.xpath(actionsLocator)).click();
			break;
	
		default:
			break;
		}
		
		return this;
	}
	

	
	
}
