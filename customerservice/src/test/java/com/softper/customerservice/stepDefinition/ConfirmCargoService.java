package com.softper.customerservice.stepDefinition;

import com.softper.customerservice.client.DriverClient;
import com.softper.customerservice.resources.comunications.CargoBoundResponse;
import com.softper.customerservice.resources.comunications.DriverBoundResponse;
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
public class ConfirmCargoService {
    //@LocalServerPort
    //private String port;
    //private RestTemplate restTemplate = new RestTemplate();

    DriverBoundResponse driver;
    CargoBoundResponse cargo;
    DriverClient driverClient;

    @Given("that the driver is in the location when needs to transport some cargo")
    public void that_the_driver_is_in_the_location_when_needs_to_transport_some_cargo() {
        driver = new DriverBoundResponse("getDriverModelById","success",1);
    }

    @When("you select the Confirm service option")
    public void you_select_the_Confirm_service_option() {
        System.out.println("In the frontend select Confirms action in the cargo profile");
    }

    @Then("a notification is sent to the customer with the message {string}")
    public void a_notification_is_sent_to_the_customer_with_the_message(String string) {
        driver = new DriverBoundResponse("getDriverModelById","success",1);
        System.out.println("A notification will vbe sent to the customer asociated");
    }

    @Given("that the driver is not in the location when needs to transport some cargo")
    public void that_the_driver_is_not_in_the_location_when_needs_to_transport_some_cargo() {
        System.out.println("In the frontend select Confirms action in the cargo profile");
    }

    @When("you you select the Confirm service option")
    public void you_you_select_the_Confirm_service_option() {
        System.out.println("In the frontend select Confirms action in the cargo profile");
    }

    @Then("the application shows a message Indicating that you have not yet arrived")
    public void the_application_shows_a_message_Indicating_that_you_have_not_yet_arrived() {
        cargo = new CargoBoundResponse("getCargoModelById","success",1);
        System.out.println("You have not yet arrived");
    }

}