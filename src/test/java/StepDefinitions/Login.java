package StepDefinitions;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;

import configuration.Base;
public class Login extends Base{
	
	
	WebDriverWait wait = new WebDriverWait (driver, Duration.ofSeconds(20));
	
	public void swaglogin() throws InterruptedException {
		//wait.until(ExpectedConditions.textToBePresentInElementLocated(By.id("Username"), "Processing Complete"));
		Thread.sleep(500);
		driver.findElement(By.id(prop.getProperty("Username"))).sendKeys("standard_user");
		logger.info("Enter phone number");
		driver.findElement(By.id(prop.getProperty("Password"))).sendKeys("secret_sauce");
   	 	logger.info("Enter password");
   	 	Thread.sleep(500);
	    // Write code here that turns the phrase above into concrete actions
		driver.findElement(By.id(prop.getProperty("Submit"))).click();
	   System.out.println("Home Page Opened");
	   
	}
	

}
