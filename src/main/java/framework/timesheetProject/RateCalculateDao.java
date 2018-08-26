package framework.timesheetProject;
import java.util.*;

public interface RateCalculateDao {
	public List<TimeSheets> getAllTimeSheets();
	public TimeSheets getTimeSheet(String employeeNo);
	public double getTotalHours(TimeSheets timeSheet);
	public double getTotalCost(TimeSheets timeSheet);
}
