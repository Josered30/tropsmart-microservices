
package com.softper.userservice.stepDefinition;

import com.softper.userservice.resources.comunications.DriverBoundResponse;
import com.softper.userservice.resources.comunications.UserBoundResponse;
import com.softper.userservice.resources.outputs.DriverOutput;
import com.softper.userservice.resources.outputs.SubscriptionOutput;
import com.softper.userservice.client.DriverClient;
import com.softper.userservice.servicesImp.SubscriptionService;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.extern.log4j.Log4j2;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

@Log4j2
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class CancelSubscription {
    @LocalServerPort
    private String port;
    private RestTemplate restTemplate = new RestTemplate();

    DriverOutput driverResponse;
    UserBoundResponse subscription;

    DriverClient driverClient;

    SubscriptionService subscriptionService = new SubscriptionService();

    @Given("that the driver is in the confirmation window")
    public void that_the_driver_is_in_the_confirmation_window() {
        driverResponse = new DriverOutput();

        driverResponse.setEmail("carlos18mz@gmail.com");
        driverResponse.setFirstName("Carlos");
        driverResponse.setLastName("MZ");
        driverResponse.setLicense("923-3421");
        driverResponse.setRole(2);
        driverResponse.setRoleId(17);
        subscription = subscriptionService.findSubscriptionById(2);
    }

    @When("you select the Confirm option")
    public void you_select_the_Confirm_option() {
        System.out.println("In the frontend driver select confirm option");
        subscriptionService.cancelSubscription(2);
    }

    @Then("the driver's subscription is canceled and the message Subscription canceled is displayed")
    public void the_driver_s_subscription_is_canceled_and_the_message_Subscription_canceled_is_displayed() {
        subscription = subscriptionService.findSubscriptionById(2);
        if(subscription.status == 1)
            System.out.println("CancelSubscription scenario 1 test success");
        else
            System.out.println("CalcelSubscription scenario 1 test failed");
    }

    @When("the user selects the Back option")
    public void the_user_selects_the_Back_option() {
        System.out.println("In the frontend driver select back option");
    }

    @Then("the driver's subscription is not canceled and the confirmation window closes")
    public void the_driver_s_subscription_is_not_canceled_and_the_confirmation_window_closes() {

        UserBoundResponse response = new UserBoundResponse("Subscription cancel", "success",1);
        System.out.println("In the frontend returning to the main menu");
    }

}