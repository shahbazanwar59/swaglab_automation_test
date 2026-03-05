package StepDefinitions;

import java.time.Duration;


import org.openqa.selenium.By;

import org.openqa.selenium.support.ui.WebDriverWait;

import configuration.Base;
import io.cucumber.java.en.*;

public class SwagLogin_StepDef extends Base{

	WebDriverWait wait = new WebDriverWait (driver, Duration.ofSeconds(20));
	@Given("user enters valid username and password")
	public void user_enters_valid_username_and_password() throws InterruptedException {
		//wait.until(ExpectedConditions.textToBePresentInElementLocated(By.id("Username"), "Processing Complete"));
		Thread.sleep(500);
		driver.findElement(By.id(prop.getProperty("Username"))).sendKeys("standard_user");
		logger.info("Enter phone number");
		driver.findElement(By.id(prop.getProperty("Password"))).sendKeys("secret_sauce");
   	 	logger.info("Enter password");
   	 	Thread.sleep(500);
	}

	@When("clicks on the login button")
	public void clicks_on_the_login_button() {
	    // Write code here that turns the phrase above into concrete actions
		 driver.findElement(By.id(prop.getProperty("Submit"))).click();
	}

	@Then("user should be navigated to the home page")
	public void user_should_be_navigated_to_the_home_page() throws InterruptedException {
	   System.out.println("Home Page Opened");
	   
	}



}
