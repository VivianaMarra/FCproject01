package com.foodcloud.community.model.pages;

public class DataTriplet {

	public enum FieldType { TEXT, SELECT, CHECKBOX };
	private String locator;
	private FieldType type;
	private String name;
	
	public DataTriplet(String fieldName, FieldType fieldType, String fieldLocator ) {
		this.name = fieldName;
		this.type = fieldType;
		this.locator = fieldLocator;		
	}
	
		
	public String getName() {
		return name;
	}


	public String getLocator() {
		return locator;
	}


	public FieldType getType() {
		return type;
	}

}
