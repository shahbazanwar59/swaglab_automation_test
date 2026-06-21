package StepDefinitions;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import configuration.Base;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.qameta.allure.Allure;

public class Hooks extends Base{
	
	@Before
	public void beforeScenarioStart() throws IOException, InterruptedException
	{
		
		
		
		driver =Base.getDriver();
		driver.get(prop.getProperty("URL"));
		Thread.sleep(1000);
		
		
	    
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
	
	public void afterScenariosFinish()
	{
		
		driver.quit();
	}

}
