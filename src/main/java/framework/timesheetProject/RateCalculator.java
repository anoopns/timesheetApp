package framework.timesheetProject;

import java.util.*;

import org.json.simple.JSONObject;

public class RateCalculator implements RateCalculateDao{

	List<TimeSheets> timeSheetDb = new ArrayList<TimeSheets>();//act as a database 
	
	public <T> RateCalculator(List<JSONObject> timeSheetData) {
		super();
		for(JSONObject entry: timeSheetData){
			String empNo = entry.get("employeeNo").toString();
			Double dayRate = Double.parseDouble(entry.get("dayRate").toString());
			Map<String, Double> hours = new HashMap<>(); 
			JSONObject hoursEntry = (JSONObject) entry.get("hours");
			for(Object key : hoursEntry.keySet()){
				hours.put( key.toString(), Double.parseDouble(hoursEntry.get(key).toString()));
			}
			//List<T> values = parseJson(entry);
			timeSheetDb.add(new TimeSheets(empNo,dayRate,hours));
		}
	}
	
/*	private <T> List<T> parseJson(JSONObject entry){
		//used generics because return list contains string, double and map
		List<T> returnList = new ArrayList<T>();
		String empNo = entry.get("employeeNo").toString();
		Double dayRate = (double)entry.get("dayRate");
		Map<String, Double> hours = null; 
		JSONObject hoursEntry = (JSONObject) entry.get("hours");
		for(Object key : hoursEntry.keySet()){
			hours.put((String) key, (double)hoursEntry.get(key));
		}	
		returnList.add((T) empNo);
		returnList.add((T) dayRate);
		returnList.add((T) hours);
		return returnList;
	}*/

	@Override
	public List<TimeSheets> getAllTimeSheets() {
		return timeSheetDb;
	}

	@Override
	public TimeSheets getTimeSheet(String employeeNo) {
		for(TimeSheets entry : timeSheetDb){
		 return (entry.getEmployeeNo().contains(employeeNo)) ?  entry: null;
		}
		return null;
	}

	@Override
	public double getTotalHours(TimeSheets timeSheet) {
		double totalHours = 0;
		for(Double hour : timeSheet.getTimeSheet().values()){
			totalHours += hour;
		}
		return totalHours;
	}

	@Override
	public double getTotalCost(TimeSheets timeSheet) 
	{
		double totalCost = 0;
		for(String day : timeSheet.getTimeSheet().keySet())
		{
			if(day == Days.SUNDAY.toString())
				totalCost += timeSheet.getTimeSheet().get(day) * timeSheet.getHourlyRate() * Constants.sundyRate;
			else if(day == Days.SATURDAY.toString())
				totalCost += timeSheet.getTimeSheet().get(day) * timeSheet.getHourlyRate() * Constants.saturdayRate;
			else
				totalCost += timeSheet.getTimeSheet().get(day) * timeSheet.getHourlyRate();
		}
		return totalCost;
	}

}


