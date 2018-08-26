package framework.timesheetProject;

import java.util.List;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UnitTests {

	@Test(dataProvider = "testData", dataProviderClass = TestData.TestData.class)
	public void validateTimeSheetCalculations(List<JSONObject> data) {
		RateCalculateDao timeSheetApp = new RateCalculator(data);
		for(JSONObject dt: data){
			TimeSheets timeSheet = timeSheetApp.getTimeSheet(dt.get("employeeNo").toString());
			double totalCost = timeSheetApp.getTotalCost(timeSheet);
			double totalHours = timeSheetApp.getTotalHours(timeSheet);
			System.out.println(String.format(
					"EmployeeId: %s \n Total hours: %f, Total cost: %f",
					timeSheet.getEmployeeNo(), totalHours, totalCost));
			Assert.assertEquals(Double.toString(totalHours) , data.get(0).get("expectedTotalHours"));
			Assert.assertEquals(Double.toString(totalCost), data.get(0).get("expectedCost"));			
		}
	}

}
