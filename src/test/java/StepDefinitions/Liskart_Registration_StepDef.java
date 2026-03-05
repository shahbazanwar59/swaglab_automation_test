package StepDefinitions;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.WebDriverWait;

import configuration.Base;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import utility.ExcelDataUtility;
import utility.JavaScriptUtility;
import utility.TestUtil;

public class Liskart_Registration_StepDef extends Base{
	
	JavaScriptUtility javaScriptUtility;
	
	 WebDriverWait wait = new WebDriverWait (driver, Duration.ofSeconds(20));
	
 
 @Given("Click on register link")
     
     public void Click_on_register_link() throws InterruptedException
     {
	 	 driver.get(prop.getProperty("LoginURL"));
	 	 Thread.sleep(500);
    	 driver.findElement(By.xpath(prop.getProperty("RegisterLogin"))).click();  // refer from global properties
    	 Thread.sleep(500);
    	 
     }  
 @Then("Enter the Basic details of User")
 
 	public void Enter_the_Basic_details_of_User() throws Exception
 	{
	 String path = System.getProperty("user.dir") + prop.getProperty("ExcelfilePath");  // refer from global properties
		ArrayList<HashMap<String, String>> data = ExcelDataUtility.storeExcelDataToHashMap(path,prop.getProperty("sheet_Liskart_Registration_Test_Data"));  // refer from global properties
		Iterator<HashMap<String, String>> i = data.iterator();
		while (i.hasNext())
		
		{
			
			HashMap<String, String> dataset = i.next();
			
			if (dataset.get("Run_Status").equalsIgnoreCase("Yes")) 
				
			{
				driver.findElement(By.xpath(prop.getProperty("FirstName"))).click();
				TestUtil.Entertext(driver.findElement(By.xpath(prop.getProperty("FirstName"))), dataset.get("First_Name"));  //refer from global properties
				TestUtil.Entertext(driver.findElement(By.xpath(prop.getProperty("LastName"))), dataset.get("Last_Name"));
				TestUtil.Entertext(driver.findElement(By.xpath(prop.getProperty("PhoneNumber"))), dataset.get("Phone_Number"));
				driver.findElement(By.xpath(prop.getProperty("CustomerProfile"))).click();
				Thread.sleep(500);
				driver.findElement(By.xpath(prop.getProperty("MechanicGarageUser"))).click();
				//TestUtil.Entertext(driver.findElement(By.xpath(prop.getProperty("CustomerProfile"))), dataset.get("Customer_Profile"));
				TestUtil.Entertext(driver.findElement(By.xpath(prop.getProperty("CompanyName"))), dataset.get("Company_Name"));
				TestUtil.Entertext(driver.findElement(By.xpath(prop.getProperty("Email"))), dataset.get("Email"));
				//TestUtil.Entertext(driver.findElement(By.xpath(prop.getProperty("Gst"))), dataset.get("Gst"));
				logger.info("Enter basic details of LiskartRegistration");
				Thread.sleep(500);
				
				javaScriptUtility =new JavaScriptUtility();
				javaScriptUtility.Scrollup();
				/*
				 * JavascriptExecutor js = (JavascriptExecutor) driver;
				 * js.executeScript("window.scrollBy(0, 300);"); Thread.sleep(500);
				 */
				
				driver.findElement(By.xpath(prop.getProperty("TermAndCondition"))).click();
				driver.findElement(By.xpath(prop.getProperty("ContinueButton"))).click();
				
				
				
				 Thread.sleep(1000);
				 continue; 
			}
			}
		}
		 @Then("Enter the OTP for Final Registratation")
		 public void Enter_the_OTP_for_Final_Registratation() throws Exception
		 {
			String path = System.getProperty("user.dir") + prop.getProperty("ExcelfilePath"); // refer from global
			// properties
			ArrayList<HashMap<String, String>> data = ExcelDataUtility.storeExcelDataToHashMap(path,
			prop.getProperty("sheet_Liskart_Registration_Test_Data")); // refer from global properties
			Iterator<HashMap<String, String>> i = data.iterator();
			while (i.hasNext())

	{

		HashMap<String, String> dataset = i.next();

		if (dataset.get("Run_Status").equalsIgnoreCase("Yes"))

		{
				 
				 javaScriptUtility.ScrollDown();			
				 Thread.sleep(500);
				 driver.findElement(By.xpath(prop.getProperty("RegisterOTP"))).click();
				TestUtil.Entertext(driver.findElement(By.xpath(prop.getProperty("RegisterOTP"))),dataset.get("OTP"));
				driver.findElement(By.xpath(prop.getProperty("VerifyButton"))).click();
				
				
				Thread.sleep(1000);
				 continue; 
		}
				
		}
 	}

}
