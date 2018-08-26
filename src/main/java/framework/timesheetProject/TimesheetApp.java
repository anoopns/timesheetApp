package framework.timesheetProject;

import org.json.simple.JSONObject;

import java.util.*;

/**
 * Hello world!
 *
 */
public class TimesheetApp 
{
    public static void main( String[] args )
    {
        List<JSONObject> data = new ArrayList<>();
    	Scanner readData = new Scanner(System.in);
    	boolean exit = true;
    	while(exit){
    		JSONObject object = new JSONObject();
        	System.out.println("Add timesheet for an employee:");
        	System.out.println("Enter Employee number:");
        	String employeeNo = readData.next();
        	while(!validateEmployeeNumber(employeeNo) || isEmplyeeExists(employeeNo, data)){
        		System.out.println("You either entered invalid number or employee number already exists.");
        		System.out.println("Enter Employee number:");
        		employeeNo = readData.next();
        	}
        	object.put("employeeNo",employeeNo);
        	System.out.println("Enter hourly rate:");
        	Double rate = readData.nextDouble();
        	while(rate < 1 || rate > 50){
        		System.out.println("Enter a valid rate(Can not exceed 50):");
        		rate = readData.nextDouble();
        	}
        	object.put("dayRate", rate.toString());
        	JSONObject innerObject = new JSONObject();
        	for(Days day : Days.values()){
        		System.out.printf(String.format("Enter hours for %s:", day.toString()));
        		String hours = readData.next();
        		innerObject.put(day.toString(), hours);
        	}
        	object.put("hours", innerObject);
        	data.add(object);
        	System.out.println("Do you want to continue(Y/N)?");
        	exit = (readData.next().toUpperCase().equals("Y"))? true:false;
    	}
        
        RateCalculateDao timeSheetApp = new RateCalculator(data);
        for(TimeSheets timeSheet:timeSheetApp.getAllTimeSheets()){
            double totalCost = timeSheetApp.getTotalCost(timeSheet);
            double totalHours = timeSheetApp.getTotalHours(timeSheet);
            System.out.println(String.format("EmployeeId: %s \n Total hours: %f, Total cost: %f", 
            		timeSheet.getEmployeeNo(),totalHours,totalCost));        	
        }
 
    }

	private static Boolean isEmplyeeExists(String employeeNo, List<JSONObject> data) {
		// TODO Auto-generated method stub
		Boolean exists = false;
			for(JSONObject entry : data){
				exists = (entry.get("employeeNo").equals(employeeNo))?true:false;
			}
			return exists;
	}

	private static Boolean validateEmployeeNumber(String employeeNo) {
		// TODO Auto-generated method stub
		return (employeeNo.trim().length() == 0)?false:true;
	}
}
