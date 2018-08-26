package TestData;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;
import org.testng.annotations.DataProvider;

public class TestData {
	
	@DataProvider
	public static Object[][] testData(){
		List<JSONObject> data = new ArrayList<>();
		JSONObject json = new JSONObject();
	    json.put("employeeNo", "123");
	    json.put("dayRate","10");
	    JSONObject innerObject = new JSONObject();
	    innerObject.put("SUNDAY", "8");
	    innerObject.put("MONDAY", "8");
	    innerObject.put("TUESDAY", "8");
	    innerObject.put("WEDNESDAY", "8");
	    innerObject.put("THURSDAY", "8");
	    innerObject.put("FRIDAY", "8");
	    innerObject.put("SATURDAY", "8");
	    json.put("hours", innerObject);
	    json.put("expectedTotalHours", "56.0");
	    json.put("expectedCost", "680.0");
	    data.add(json);
		return new Object[][] {{data}};
	}

}
