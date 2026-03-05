package StepDefinitions;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;

import configuration.Base;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import utility.ExcelDataUtility;
import utility.JavaScriptUtility;
import utility.TestUtil;

public class Liskart_Login_StepDef extends Base{
	
	
	
     WebDriverWait wait = new WebDriverWait (driver, Duration.ofSeconds(20));
     
     @Given("click on Login Link")
     
     public void click_on_Login_Link() throws InterruptedException
     {
    	 driver.findElement(By.xpath(prop.getProperty("LoginLink"))).click();  // refer from global properties
    	 Thread.sleep(500);
    	 
     }
     
     @Then("click on Phone Text Box")
 	public void click_on_Phone_Text_Box() throws Exception
 	
 	{
    	 driver.findElement(By.xpath(prop.getProperty("PhoneNumber"))).click();
    	 logger.info("Clicked to enter phone number");
    	 
    	 Thread.sleep(500);
     
 	}
     
     @And("Enter Phone Number")
     public void Enter_Phone_Number() throws Exception
     {
    	 String path = System.getProperty("user.dir") + prop.getProperty("ExcelfilePath");  // refer from global properties
 		ArrayList<HashMap<String, String>> data = ExcelDataUtility.storeExcelDataToHashMap(path,prop.getProperty("sheet_Liskart_Registration_Test_Data"));  // refer from global properties
 		Iterator<HashMap<String, String>> i = data.iterator();
 		//while (i.hasNext())
 		
 		{
 			
 			HashMap<String, String> dataset = i.next();
 			
 			//if (dataset.get("Run_Status").equalsIgnoreCase("Yes")) 
 				
 		 TestUtil.Entertext(driver.findElement(By.xpath(prop.getProperty("PhoneNumber"))), dataset.get("Phone_Number"));
    	 logger.info("Phone number entered");
    	 driver.findElement(By.xpath(prop.getProperty("SendOtp"))).click();
    	 logger.info("Clicked on send otp");
    	 Thread.sleep(1000);
    	 TestUtil.Entertext(driver.findElement(By.xpath(prop.getProperty("OTPEntered"))), dataset.get("OTP"));
    	 logger.info("OTP Entered");
    	 Thread.sleep(500);
    	 driver.findElement(By.xpath(prop.getProperty("Login"))).click();
    	 logger.info("Login Button Clicked");
    	 
    	 
    	 Thread.sleep(1000);
			//continue;
		}
    	 
     }

     
}
   