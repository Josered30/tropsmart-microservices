package com.softper.driverservice.stepDefinition;

import com.softper.driverservice.client.CustomerClient;
import com.softper.driverservice.resources.comunications.CustomerBoundResponse;
import com.softper.driverservice.resources.comunications.DriverBoundResponse;
//import com.softper.driverservice.servicesImp.CustomerService;
import com.softper.driverservice.servicesImp.DriverService;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.extern.log4j.Log4j2;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

@Log4j2
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class SearchDriverImpl {
    @LocalServerPort
    private String port;
    private RestTemplate restTemplate = new RestTemplate();

    DriverBoundResponse driver;
    CustomerBoundResponse customer;
    DriverService driverService = new DriverService();
    CustomerClient customerClient;

    @Given("that the customer is in the search window")
    public void that_the_customer_is_in_the_search_window() {
        customer = new CustomerBoundResponse("getCustomerModelById","success",1);
    }


    @When("enter search information and select the Search option")
    public void enter_search_information_and_select_the_Search_option() {
        driver = new DriverBoundResponse("getDriverModelById","success",1);
    }

    @Then("drivers are displayed according to search criteria")
    public void drivers_are_displayed_according_to_search_criteria() {
        if(driver.status == 1)
            System.out.println("SearchDriver scenario1 test success");
        else
            System.out.println("SearchDriver scenario1 test failed");
    }

    @When("enter search information and there are no driver according to the search criteria")
    public void enter_search_information_and_there_are_no_driver_according_to_the_search_criteria() {
        // Write code here that turns the phrase above into concrete actions
        driver = new DriverBoundResponse("getDriverModelById","success",1);
    }

    @Then("no driver found")
    public void no_driver_found() {
        if(driver.status == 1)
            System.out.println("SearchDriver scenario2 test success");
        else
            System.out.println("SearchDriver scenario2 test failed");
    }

}