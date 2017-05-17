package com.test.community;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.DataProvider;

import com.foodcloud.community.model.pages.DataTriplet;
import com.foodcloud.community.model.pages.SectionData;

public class readCharityData {

	@DataProvider(name = "foodTypeData")
	public static Object[][] foodTypeSection() {
		List<DataTriplet> list = new ArrayList();
		list.add(new DataTriplet("Monthly Spend" , DataTriplet.FieldType.TEXT, "css=input[name='food-spend']")); 
		list.add(new DataTriplet("Top Dairy" , DataTriplet.FieldType.TEXT, "css=textarea[name='food-top-dairy']")); 
		list.add(new DataTriplet("Top Meat" , DataTriplet.FieldType.TEXT, "css=textarea[name='food-top-meat']"));				
		list.add(new DataTriplet("Top Non-Perishable" , DataTriplet.FieldType.TEXT, "css=textarea[name='food-top-nonperish']"));				
		list.add(new DataTriplet("Top Other" , DataTriplet.FieldType.TEXT, "css=textarea[name='food-top-other']"));
		list.add(new DataTriplet("Top FruitVeg" , DataTriplet.FieldType.TEXT, "css=textarea[name='food-top-ffv']") );

//		String[] expectedStoredData = {"15000",  "Goats Milk", "Steak",  "Banana"} ;
		
		List<String> expectedResults = new ArrayList<String>(){{
			add("15000");
			add("Goats Milk");
			add("Steak");
			add(null);
			add(null);
			add("Banana");
		}};
		
		return new Object[][] { { new SectionData("Food Types", list), expectedResults } };
	}

	@DataProvider(name = "foodSafetyData")
	public static Object[][] FoodSafetySection() {
		List<DataTriplet> list = new ArrayList();
		list.add(new DataTriplet("Registered Food Organization" , DataTriplet.FieldType.CHECKBOX, "css=input[name='food-use-registration']")); 
		list.add(new DataTriplet("Food Safety Inspection" , DataTriplet.FieldType.CHECKBOX, "css=input[name='food-use-audit']")); 
		list.add(new DataTriplet("Food Safety Policy" , DataTriplet.FieldType.CHECKBOX, "css=input[name='food-safety-management-policy']"));				
		list.add(new DataTriplet("Donation Sorting" , DataTriplet.FieldType.CHECKBOX, "css=input[name='food-safety-management-sorting']"));				
		list.add(new DataTriplet("Food Supplier List" , DataTriplet.FieldType.CHECKBOX, "css=input[name='food-safety-management-supplier']"));
		list.add(new DataTriplet("Appropriate Transport" , DataTriplet.FieldType.CHECKBOX, "css=input[name='food-safety-management-transport']"));
		list.add(new DataTriplet("Storage Facilities" , DataTriplet.FieldType.CHECKBOX, "css=input[name='food-safety-management-storage']"));
		list.add(new DataTriplet("Hygiene Facilities" , DataTriplet.FieldType.CHECKBOX, "css=input[name='food-safety-management-hygiene']"));
		list.add(new DataTriplet("Food Preparation Facilities" , DataTriplet.FieldType.CHECKBOX, "css=input[name='food-safety-management-preparation']"));
		list.add(new DataTriplet("Waste Disposal Facilities" , DataTriplet.FieldType.CHECKBOX, "css=input[name='food-safety-management-waste']"));
		list.add(new DataTriplet("Pest Control" , DataTriplet.FieldType.CHECKBOX, "css=input[name='food-safety-management-pest']"));
		list.add(new DataTriplet("Temperature Checks" , DataTriplet.FieldType.CHECKBOX, "css=input[name='food-safety-management-temperature']"));
		list.add(new DataTriplet("Food Records" , DataTriplet.FieldType.CHECKBOX, "css=input[name='food-use-records']"));
		list.add(new DataTriplet("Food Safety Training" , DataTriplet.FieldType.TEXT, "css=textarea[name='food-safety-training']") );
		list.add(new DataTriplet("Safety Training System" , DataTriplet.FieldType.TEXT, "css=input[name='food-safety-training-system']") );

		return new Object[][] { { "Food Safety", list } };
	}


	
}
