package com.softper.userservice;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

import com.softper.userservice.repositories.IUserRepository;
import com.softper.userservice.servicesImp.UserService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

@RunWith(Cucumber.class)
@CucumberOptions(
	plugin = {"pretty", "json:target/cucumber.json"},
	features = "src/test/java/featureFiles"
	//glue = {"src/test/java/stepDefinition"}
)
class UserserviceApplicationTests {

}
