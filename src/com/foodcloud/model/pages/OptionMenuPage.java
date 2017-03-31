package com.foodcloud.model.pages;

import com.foodcloud.test.server.FCTestNavigator;

public class OptionMenuPage extends Page {
	
	protected OptionMenu menu;

	public OptionMenuPage(FCTestNavigator nav) {
		super(nav);
		this.menu = new OptionMenu(nav);
		// how can I know if the menu has all buttons or not? isElementPresent? 
	}

	public OptionMenu getMenu() {
		return menu;
	}

	// not used at the moment : need to find a way to deal with not all elements (menu items) displayed
	public void setMenu(OptionMenu menu) {
		this.menu = menu;
	}
	
	
	
	
}
