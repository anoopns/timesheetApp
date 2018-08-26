package framework.timesheetProject;
import java.util.*;

public class TimeSheets {
	private String employeeNo;
	private Map<String, Double> timeSheetEntry;
	private Double hourlyRate;
	
	public String getEmployeeNo() {
		return employeeNo;
	}
	public void setEmployeeNo(String employeeNo) {
		this.employeeNo = employeeNo;
	}
	public Map<String, Double> getTimeSheet() {
		return timeSheetEntry;
	}
	public void setTimeSheet(Map<String, Double> timeSheet) {
		this.timeSheetEntry = timeSheet;
	}
	
	public Double getHourlyRate(){
		return hourlyRate;
	}
	public TimeSheets(String employeeNo, Double hourlyRate, Map<String, Double> timeSheet) {
		super();
		this.employeeNo = employeeNo;
		this.timeSheetEntry = timeSheet;
		this.hourlyRate = hourlyRate;
	}
	
}
