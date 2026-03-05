package StepDefinitions;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import configuration.Base;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import utility.ExcelDataUtility;
import utility.JavaScriptUtility;
import utility.TestUtil;

public class Liskart_Seller_Registration_StepDef extends Base {

	JavaScriptUtility javaScriptUtility;

	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

	@Given("Click on seller register link")
	public void click_on_seller_register_link() throws InterruptedException {
		// Write code here that turns the phrase above into concrete actions
		driver.get(prop.getProperty("SellerLoginURL"));
		Thread.sleep(500);
		driver.findElement(By.xpath(prop.getProperty("RegisterLogin"))).click(); // refer from global properties
		Thread.sleep(500);
	}

	@Then("Enter the Basic details of seller")
	public void enter_the_basic_details_of_seller() throws Exception {
		// Write code here that turns the phrase above into concrete actions
		String path = System.getProperty("user.dir") + prop.getProperty("ExcelfilePathSeller"); // refer from global
																								// properties
		ArrayList<HashMap<String, String>> data = ExcelDataUtility.storeExcelDataToHashMap(path,
				prop.getProperty("sheet_Seller_Registration_Data")); // refer from global properties
		Iterator<HashMap<String, String>> i = data.iterator();
		while (i.hasNext())

		{

			HashMap<String, String> dataset = i.next();

			if (dataset.get("Run_Status").equalsIgnoreCase("Yes"))

			{

				driver.findElement(By.xpath(prop.getProperty("FullName"))).click();
				TestUtil.Entertext(driver.findElement(By.xpath(prop.getProperty("FullName"))),
						dataset.get("Full_Name")); // refer from global properties
				TestUtil.Entertext(driver.findElement(By.xpath(prop.getProperty("PhoneNumber"))),
						dataset.get("Phone_Number"));
				TestUtil.Entertext(driver.findElement(By.xpath(prop.getProperty("Email"))), dataset.get("Email"));
				TestUtil.Entertext(driver.findElement(By.xpath(prop.getProperty("Gst"))), dataset.get("GST"));
				logger.info("Enter basic details of LiskartRegistration");
				Thread.sleep(500);

				driver.findElement(By.xpath(prop.getProperty("TermAndCondition"))).click();
				driver.findElement(By.xpath(prop.getProperty("RegisterAsSeller"))).click();
				Thread.sleep(8000);

				
				  javaScriptUtility =new JavaScriptUtility(); 
				  javaScriptUtility.Scrollup();
				 
				  driver.findElement(By.xpath(prop.getProperty("FinalRegister"))).click();
				/*
				 * WebElement button=
				 * driver.findElement(By.xpath(prop.getProperty("FinalRegister"))); Actions
				 * actions = new Actions(driver); actions.moveToElement(button).perform(); //
				 * Optional: short wait so hover effect appears try { Thread.sleep(1000); }
				 * catch (InterruptedException e) { }
				 * 
				 * // Click after hover button.click();
				 */
				  
				  Thread.sleep(1000);		  
				  
				continue;
			}

		}
		
	}
	
	
	@Then("Enter the Seller OTP for Final Registratation")
	  public void Enter_the_Seller_OTP_for_Final_Registratation() throws Exception
	  {
		String path = System.getProperty("user.dir") + prop.getProperty("ExcelfilePathSeller"); // refer from global
		// properties
		ArrayList<HashMap<String, String>> data = ExcelDataUtility.storeExcelDataToHashMap(path,
		prop.getProperty("sheet_Seller_Registration_Data")); // refer from global properties
		Iterator<HashMap<String, String>> i = data.iterator();
		while (i.hasNext())

		{

			HashMap<String, String> dataset = i.next();

			if (dataset.get("Run_Status").equalsIgnoreCase("Yes"))

			{
		 driver.findElement(By.xpath(prop.getProperty("SellerOTP"))).click();
		TestUtil.Entertext(driver.findElement(By.xpath(prop.getProperty("SellerOTP"))),dataset.get("Seller_OTP"));
		driver.findElement(By.xpath(prop.getProperty("VerifyButton"))).click();
			}
			continue;
		}
		}
	
}
