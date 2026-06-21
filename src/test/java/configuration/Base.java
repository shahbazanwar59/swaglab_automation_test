package configuration;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Base {
	
	public static WebDriver driver;
	public static Properties prop;
	public static Logger logger;
	
	public static WebDriver getDriver() throws IOException
	{
		logger= LogManager.getLogger(Base.class);
		logger= LogManager.getLogger("getDriver");
		logger= LogManager.getLogger();
		
		prop = new Properties();
		File f = new File(System.getProperty("user.dir")+"/src/test/java/configuration/global.properties");
		FileInputStream fis = new FileInputStream(f);
		prop.load(fis);
		
		
		 // 1. Create a Map to hold the preference settings
        Map<String, Object> prefs = new HashMap<>();
        
        // Settings to suppress ALL password-related pop-ups:
        // A. Disable the "Offer to save password" bubble
        prefs.put("credentials_enable_service", false);
        
        // B. Disable the built-in password manager features
        prefs.put("profile.password_manager_enabled", false);
        
        // C. THIS IS THE KEY: Disable the "password found in data breach" check
        prefs.put("profile.password_manager_leak_detection", false);
        
        // 2. Create the ChromeOptions object
        ChromeOptions chromeOptions = new ChromeOptions();
        
        // 3. Apply the preferences map using experimental options
        chromeOptions.setExperimentalOption("prefs", prefs);

        // Optional: Add arguments for cleaner testing environment
        // Hides the "Chrome is being controlled by automated test software" info bar
        chromeOptions.addArguments("--disable-infobars");
        
        // 4. Initialize the driver with the configured options
        driver = new ChromeDriver(chromeOptions);
	    
	    driver.manage().window().maximize();
	    return driver;
	}
}