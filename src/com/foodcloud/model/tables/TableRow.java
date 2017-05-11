package com.foodcloud.model.tables;

public class TableRow {

	private String id;
	private String icon;
	private String date;
	private String time;
	private String info;
	private String donor;
	private String donor_codeId;
	private String charity;
	private String collectionWindow;
	private String status;
	private String dropDownButton; //other object 
	
	
	private String donorCodeIdLocator ="id=don.donorStoreId";
	private ResultTable baseTable;

	public TableRow(ResultTable table, String date2, String time, String infoLocator) {
		setBaseTable(table);
		setDate(date2);
		setTime(time);
		setInfo(infoLocator);
		
	}

	public TableRow(ResultTable table,  String timeLineLocator, String creationDate, String creationTime, String infoLocator, String donorName,
			String donorNumber, String charityName, String collectionTimeWindow, String currentStatus, String controlLocator) {
		
		setBaseTable(table);
		setIconLocator(timeLineLocator);
		setDate(creationDate);
		setTime(creationTime);
		setInfo(infoLocator);
		setDonor(donorName);
		setDonorCode(donorNumber); 
		setCharity(charityName);
		setCollection(collectionTimeWindow); 
		setStatus(currentStatus);
		setDropDownButton(controlLocator);

	}

	private void setDropDownButton(String controlLocator) {
		this.dropDownButton = controlLocator;		
	}

	private void setStatus(String currentStatus) {
		this.status = currentStatus;
		
	}

	private void setCollection(String collectionTimeWindow) {
		this.collectionWindow = collectionTimeWindow;			
	}

	private void setCharity(String charityName) {
		this.charity = charityName;	
	}

	private void setDonorCode(String donorNumber) {
		this.donor_codeId = donorNumber;			
	}

	private void setDonor(String donorName) {
		this.donor = donorName;	
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

	private void setIconLocator(String iconLocator) {
		this.icon = iconLocator;		
	}	
	
	public String getStatus() {
		return status;
	}

	public String getDropDownButton() {
		return dropDownButton;
	}
	
	
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

	public String getCollectionWindow() {
		return collectionWindow;
	}

	public ResultTable getBaseTable() {
		return baseTable;
	}

	public String getIconLocator() {
		return this.icon;		
	}	

}
