package testRunner;

import org.junit.jupiter.api.Disabled;
import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.IncludeTags;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

import io.cucumber.junit.platform.engine.Constants;

import static io.cucumber.junit.platform.engine.Constants.*;


@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("features")
@ConfigurationParameter(key = Constants.GLUE_PROPERTY_NAME, value = "StepDefinitions")
//@IncludeTags("SwagAddProductToCart")
@IncludeTags("SwagAddProductToCart|SwagLogin")
//@IncludeTags("LiskartRegistration|LiskartLogin|LiskartSellerRegistration|LiskartSellerLogin")
@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = "pretty, json:target/cucumber-reports/cucumber.json,html:target/HTMLReport,com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:target/cucumber-reports/report.html,io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm")
//@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = "pretty, json:target/allure-report/allure.json,io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm")

 

@Disabled

public class TestRunTest {
    // Empty class
	
 
}

