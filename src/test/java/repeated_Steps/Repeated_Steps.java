package repeated_Steps;

import org.openqa.selenium.By;

import configuration.Base;
import utility.JavaUtility;
import utility.TestUtil;


public class Repeated_Steps extends Base{
	
	public static void SelectDate(String Select_Date, String xpathOfFiled) throws Exception {
	/*	
		if (!((Select_Date.equalsIgnoreCase("NA")) || (Select_Date.equalsIgnoreCase("N/A")))) 
		{
			logger.info("Select the date from the calendar is "+Select_Date);
			TestUtil.click(driver.findElement(By.id(prop.getProperty(xpathOfFiled))));
			Thread.sleep(1000);
			TestUtil.SelectByText(driver.findElement(By.xpath(prop.getProperty("StartYear"))),
					JavaUtility.returnYearfromDate(Select_Date));
			TestUtil.SelectByText(driver.findElement(By.xpath(prop.getProperty("StartMonth"))),
					JavaUtility.returnMonthfromDate(Select_Date));

			driver.findElement(By.xpath(prop.getProperty("StartDateFirstPart")
					+ JavaUtility.returnDayhfromDate(Select_Date) + prop.getProperty("StartDateSecondPart")))
					.click();
			Thread.sleep(2000);
		
	}
*/
	//TestUtil.Entertext(driver.findElement(By.xpath(prop.getProperty(xpathOfFiled))), Select_Date);
	
		driver.findElement(By.xpath(prop.getProperty(xpathOfFiled))).click();
		Thread.sleep(500);
		driver.findElement(By.xpath(prop.getProperty(xpathOfFiled))).sendKeys(Select_Date);
		
	/*	
		driver.findElement(By.xpath("//input[@id='memberDetails.0.dob']")).click();
		Thread.sleep(500);
		driver.findElement(By.xpath("//input[@id='memberDetails.0.dob']")).sendKeys(Select_Date);
		*/
 }
	
}
