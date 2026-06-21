package StepDefinitions;

import java.io.IOException;
import configuration.Base;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks extends Base{
	
	@Before
	public void beforeScenarioStart() throws IOException, InterruptedException
	{
		
		
		
		driver =Base.getDriver();
		driver.get(prop.getProperty("URL"));
		Thread.sleep(1000);
		
		
	    
	}
	
	
	
	@After
	public void afterScenariosFinish()
	{
		driver.quit();
	}

}
