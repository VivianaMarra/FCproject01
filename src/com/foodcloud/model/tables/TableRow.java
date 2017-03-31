package com.foodcloud.model.tables;

import com.foodcloud.model.pages.Page;
import com.foodcloud.test.server.FCTestNavigator;

public class TableRow<R> {

	private String id;
	private String icon;
	private String date;
	private String time;
	private String info;
	private String donor;
	private String donor_codeId;
	private String charity;
	private String donationWindow;
	private String statusButton;
	private String interactionDropDown; //other object 
	
	
	private String donorCodeIdLocator ="id=don.donorStoreId";
	private ResultTable baseTable;
	//locators??

//	
//	public TableRow(FCTestNavigator nav_driver, Page page) {
//	}

//	public TableRow(FCTestNavigator nav, String date2, String time, String infoLocator) {
	
	public TableRow(ResultTable table, String date2, String time, String infoLocator) {
		setBaseTable(table);
		setDate(date2);
		setTime(time);
		setInfo(infoLocator);
		
	}

//	public String getId() {
//		return id;
//	}
//
//	public String getIcon() {
//		return icon;
//	}

	public String getDate() {
		return date;
	}

	public String getTime() {
		return time;
	}

	public String getInfo() {
		return info;
	}

	public String getDonor() {
		return donor;
	}

	public String getDonorCode() {
		return donor_codeId;
	}

	public String getCharity() {
		return charity;
	}

	public String getDonationWindow() {
		return donationWindow;
	}

//	public String getStatusButton() {
//		return statusButton;
//	}
//
//	public String getInteractionDropDown() {
//		return interactionDropDown;
//	}

	public ResultTable getBaseTable() {
		return baseTable;
	}

	private void setInfo(String infoLocator) {
		this.info = infoLocator;	
	}

	private void setTime(String time) {
		this.time = time;
	}

	private void setDate(String date2) {
		this.date = date2;		
	}

	private void setBaseTable(ResultTable tableOrigin) {
		this.baseTable= tableOrigin;
		tableOrigin.getServer();
	}

	// TableItem GetRow??
	
}
