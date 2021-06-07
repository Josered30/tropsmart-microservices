package com.softper.driverservice;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
	plugin = {"pretty", "json:target/cucumber.json"},
	features = "src/test/java/featureFiles"
	//glue = {"src/test/java/stepDefinition"}
)
class DriverserviceApplicationTests {


}
