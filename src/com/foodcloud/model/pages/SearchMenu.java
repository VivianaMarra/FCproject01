package com.foodcloud.model.pages;

import org.openqa.selenium.By;
import com.foodcloud.test.server.FCTestNavigator;
import com.foodcloud.test.server.FCTestServer;

public class SearchMenu extends Menu {

	private String magnifierLocator = "//div[@class='header']/descendant::i[contains(@class, 'fa-search')][not(contains(@class, 'ng-hide'))]";
	private String magnifierMinusLocator = "//div[@class='header']/descendant::i[contains(@class, 'fa-search-minus')][not(contains(@class, 'ng-hide'))]";

	private String hiddenSearchMenuLocator = "//div[div[@class='header']]/table/thead[@class='ng-hide']";
	private String visibleSearchMenuLocator = "//table[@id]/thead[not(@class='ng-hide')]";
	private String clearFilterLocator = "//thead[not(@class='ng-hide')]/descendant::div[@title='Clear filters']/descendant::i[contains(@class, 'fa-filter')]";

	
	private FCTestServer server;
	
	public SearchMenu(FCTestNavigator navigator) {
		super(navigator);
	}

	
	public SearchMenu open() {

		if(server.isElementPresent(magnifierLocator)) {
		
			server.click(magnifierLocator);
		
			server.waitForElement(By.xpath(visibleSearchMenuLocator));
		}
		return this;
	}


	public SearchMenu close() {
		if(server.isElementPresent(magnifierMinusLocator)){ 
			server.click(magnifierMinusLocator);
			server.waitForElement(By.xpath(hiddenSearchMenuLocator));	
		}
		return this;
	}


	/**
	 * clicks on the search filter clear button.
	 * @return this object
	 */
	public SearchMenu clear() {
		if(server.isElementPresent(clearFilterLocator )) {
			
			server.click(clearFilterLocator);
				
			server.waitForElement(By.xpath(visibleSearchMenuLocator));
		}
		return this;
	}

}
