package utility;

public class JavaUtility {
	
	public static String returnYearfromDate(String d) 
	{
		String date[] = d.split("/");
		String year = date[2];
		return year;
	}
	
	public static String returnMonthfromDate(String d) 
	{
		String date[] = d.split("/");
		String month = date[1];
		return month;
	}
	
	public static String returnDayhfromDate(String d) 
	{
		String date[] = d.split("/");
		String day = date[0];
		return day;
	}

}
