package com.foodcloud.community.model.pages;

import java.util.List;

/**
 * Object that maps a Section , used mostly for Data test 
 * @author Viviana
 *
 */
public class SectionData {

	private List<DataTriplet> fields;
	private String name;
	
	public SectionData(String sectionName, List<DataTriplet> fieldList  ) {
		this.name = sectionName;
		this.fields = fieldList;		
	}
	
		
	public String getName() {
		return name;
	}


	public List<DataTriplet> getFields() {
		return fields;
	}

}
