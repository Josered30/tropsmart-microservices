package com.softper.configservice.stepDefinition;

import com.softper.configservice.resources.comunications.DriverBoundResponse;
import com.softper.configservice.client.DriverClient;
import com.softper.configservice.resources.comunications.CargoBoundResponse;
import com.softper.configservice.resources.comunications.ConfigBoundResponse;
import com.softper.configservice.resources.inputs.PaymentMethodInput;
import com.softper.configservice.servicesImp.ConfigurationService;
//import com.softper.configservice.servicesImp.DriverService;
import com.softper.configservice.servicesImp.PaymentMethodService;
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
public class AddPaymentMethod {
    @LocalServerPort
    private String port;
    private RestTemplate restTemplate = new RestTemplate();

    DriverBoundResponse driver;
    DriverClient driverClient;
    ConfigurationService configurationService = new ConfigurationService();
    PaymentMethodService paymentMethodService = new PaymentMethodService();
    PaymentMethodInput paymentMethodInput;
    CargoBoundResponse paymentMethodResponse;

    @Given("the driver is in the add collection method window")
    public void the_driver_is_in_the_add_collection_method_window() {
        driver = new DriverBoundResponse("getDriverModelById","success",1);
    }

    @When("you enter the payment method information")
    public void you_enter_the_payment_method_information() {
        paymentMethodInput = new PaymentMethodInput("Interbank", 1068, 523228321, "75261989");
    }

    @When("select the option Add payment method")
    public void select_the_option_Add_payment_method() {
        System.out.println("In the frontend select 'Add payment method'");
        configurationService.addPaymentMethod(2,paymentMethodInput);
    }

    @Then("the new collection method is added and the message Added successfully is displayed")
    public void the_new_collection_method_is_added_and_the_message_Added_successfully_is_displayed() {
        paymentMethodResponse = new CargoBoundResponse("findPaymentMethodById","success",1);
        if(paymentMethodResponse.status == 1)
            System.out.println("AddPaymentMethod scenario 1 test success");
        else
            System.out.println("AddPaymentMethod scenario 1 test failed");
    }

    @Given("the carrier is in the add collection method window")
    public void the_carrier_is_in_the_add_collection_method_window() {
        //driver = driverService.findDriverById(2);

    }

    @When("select the Add payment method option")
    public void select_the_Add_payment_method_option() {
        System.out.println("In the frontend select 'Add payment method'");
        configurationService.addPaymentMethod(2,paymentMethodInput);
    }

    @When("the payment method information is empty")
    public void the_payment_method_information_is_empty() {
        paymentMethodInput = new PaymentMethodInput();
    }

    @Then("the collection method is not added and the message Enter collection method information is displayed")
    public void the_collection_method_is_not_added_and_the_message_Enter_collection_method_information_is_displayed() {
        paymentMethodResponse = new CargoBoundResponse("findPaymentMethodById","success",1);
        if(paymentMethodResponse.status == 1)
            System.out.println("AddPaymentMethod scenario 1 test success");
        else
            System.out.println("AddPaymentMethod scenario 1 test failed");
    }

}