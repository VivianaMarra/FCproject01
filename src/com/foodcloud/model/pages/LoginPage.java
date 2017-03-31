package com.foodcloud.model.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.foodcloud.test.server.FCTestNavigator;

public class LoginPage extends Page {

	protected String emailLocator = "//input[@id='login_user']";
	protected String passwordLocator = "//input[@id='login_pass']";
	protected String buttonLocator = "//button[@type='submit']";


	public LoginPage( FCTestNavigator nav_driver) {
		super(nav_driver);
	}

	public LoginPage setLoginField(String loginName) {

		WebElement we = driver.findElement(By.xpath(emailLocator));
		we.sendKeys(loginName);
		return this;
	}

	public LoginPage setPasswordField(String pwd) {
		WebElement we = driver.findElement(By.xpath(passwordLocator));
		we.sendKeys(pwd);	
		return this;
	}
	
	
	/**
	 * clicks on Submit button
	 * NOTE: No error handling at the moment 
	 */
	public void clickSubmit(){
		WebElement we_button = driver.findElement(By.xpath(buttonLocator));
		we_button.click();

	}
	

}
