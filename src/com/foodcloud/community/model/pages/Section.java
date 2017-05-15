package com.foodcloud.community.model.pages;

import java.util.Iterator;
import java.util.List;

import com.foodcloud.model.dialogs.InputTextField;
import com.foodcloud.model.dialogs.SelectField;
import com.foodcloud.model.pages.Menu;
import com.foodcloud.test.server.FCTestNavigator;
import com.foodcloud.test.server.FCTestServer;

/**
 * This class is mapping in the Auto test project a Section in the organization page for Community model.
 *  
 * @author Viviana
 *
 */
public class Section extends Menu {

	// inheriting fields map from superclass
	private String sectionName;

	//not used
//	public Section(FCTestNavigator navigator, String name) {
//		super(navigator);
//		this.sectionName = name;
//	}

	/**
	 * Creates a new section with a name and a list of fields
	 * (Constructor)
	 * @param navigator
	 * @param name
	 * @param list
	 */
	public Section(FCTestNavigator navigator, String name, List<DataTriplet> fieldList) {
		super(navigator);
		this.sectionName = name;
		//addFieldData(data);
		addFieldList(fieldList);
	}

	/**
	 * Returns the Section name as provided in the second constructor. 
	 * It should match the UI when creating. 
	 * @return String representing section Name
	 */
	public String getName() {
		return sectionName;
	}

	/**
	 * Add one field to the fields list.
	 * Field has to be defined as: FieldName, Type and fieldLocator
	 * @param data - DataTriplet object  
	 * @return Section object (Updated) 
	 */
	public Section addFieldData(DataTriplet data) {
		String fieldName = data.getName();

		if (!fields.containsKey(fieldName)) {

			switch (data.getType()) {
			case TEXT:
				// getFieldList().put(key, value)
				addField(fieldName, new InputTextField(nav, data.getLocator()));
				break;
			case SELECT:
				addField(fieldName, new SelectField(nav, data.getLocator()));
				break;
			case CHECKBOX:
				addField(fieldName, new SelectField(nav, data.getLocator()));
				break;

			default:
				break;
			}
		}
		return this;
	}

	/**
	 * Adds a list of fields to the Section 
	 * @param dataList - List of DataTriplet objects representing fields
	 * @return Section object (updated)
	 */
	public Section addFieldList(List<DataTriplet> dataList) {
		for (Iterator iterator = dataList.iterator(); iterator.hasNext();) {
			DataTriplet currentData = (DataTriplet) iterator.next();
	
			addFieldData(currentData);
		}
		
		return this;		
	}

}
