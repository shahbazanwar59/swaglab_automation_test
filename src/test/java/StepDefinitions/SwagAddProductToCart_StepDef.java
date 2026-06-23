package StepDefinitions;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.support.ui.WebDriverWait;

import configuration.Base;
import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.*;
import io.qameta.allure.Allure;
import utility.ExcelDataUtility;
import utility.TestUtil;


public class SwagAddProductToCart_StepDef extends Base{
	
	Login login;
	WebDriverWait wait = new WebDriverWait (driver, Duration.ofSeconds(20));
	
	@Given("user select the prodcut")
	public void user_select_the_prodcut() throws InterruptedException {
		login=new Login();
		login.swaglogin();
		Thread.sleep(2000);
		
		driver.findElement(By.xpath(prop.getProperty("SelectBagPack"))).click();
		Thread.sleep(500);
		
	}

	@When("product available add the product to cart")
	public void product_available_add_the_product_to_cart() throws InterruptedException {
	    // Write code here that turns the phrase above into concrete actions
		driver.findElement(By.id(prop.getProperty("AddToCart"))).click();
		Thread.sleep(500);
	}

	@When("click on add to cart button")
	public void click_on_add_to_cart_button() {
	    // Write code here that turns the phrase above into concrete actions
		driver.findElement(By.id(prop.getProperty("Cart"))).click();
	}
	
	@Then("Checkout the Product")
	public void Checkout_the_Product() {
	    // Write code here that turns the phrase above into concrete actions
		driver.findElement(By.id(prop.getProperty("CheckOut"))).click();
	}
	
	
	@Then("Enter Your Checkout Information")
	public void Enter_Your_Checkout_Information() throws Exception {
	    // Write code here that turns the phrase above into concrete actions
		Thread.sleep(500);
		 String path = System.getProperty("user.dir") + prop.getProperty("Swag_Excel_Data");  // refer from global properties
			ArrayList<HashMap<String, String>> data = ExcelDataUtility.storeExcelDataToHashMap(path,prop.getProperty("sheet_Swag_data"));  // refer from global properties
			Iterator<HashMap<String, String>> i = data.iterator();
			while (i.hasNext())
			
			{
				
				HashMap<String, String> dataset = i.next();
				
				if (dataset.get("Run_Status").equalsIgnoreCase("Yes")) 
					
				{
					driver.findElement(By.id(prop.getProperty("FirstName"))).click();
		TestUtil.Entertext(driver.findElement(By.id(prop.getProperty("FirstName"))), dataset.get("First_Name"));  //refer from global properties
		TestUtil.Entertext(driver.findElement(By.id(prop.getProperty("LastName"))), dataset.get("Last_Name"));
		TestUtil.Entertext(driver.findElement(By.id(prop.getProperty("PostalCode"))), dataset.get("Postal_Code"));
				}
	
			}
			Thread.sleep(500);

	}
	
	@Then("Continue to the Checkout")
	public void Continue_to_the_Checkout() throws InterruptedException {
	    // Write code here that turns the phrase above into concrete actions
		driver.findElement(By.id(prop.getProperty("ContinueBtn"))).click();
		Thread.sleep(500);
	}
	
	@Then("Finish the Checkout Process")
	public void Finish_the_Checkout_Process() throws InterruptedException {
	    // Write code here that turns the phrase above into concrete actions
		driver.findElement(By.id(prop.getProperty("FinishTheCashout"))).click();
		Thread.sleep(500);
	}
	
	@Then("Back to Home")
	public void Back_to_Home() {
	    // Write code here that turns the phrase above into concrete actions
		driver.findElement(By.id(prop.getProperty("BackToHome"))).click();
	}

	
	@After
	public void afterScenariosFinish(Scenario scenario)
	{
		if(scenario.isFailed())
		{
			
			byte[] screenshot=((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
			Allure.addAttachment("Failed Screenshot", new ByteArrayInputStream(screenshot));
		//driver.quit();
		}
	}
	
}
