package com.foodcloud.community.model.pages;

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
	 * 
	 * @param navigator
	 * @param name
	 * @param data
	 */
	public Section(FCTestNavigator navigator, String name, DataTriplet data) {
		super(navigator);
		this.sectionName = name;
		addFieldList(data);
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
	 * 
	 * @param data
	 * @return
	 */
	public Section addFieldList(DataTriplet data) {
		// List<DataTriplet> data){

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
}
