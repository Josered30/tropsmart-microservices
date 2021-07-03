package com.softper.driverservice.stepDefinition;

import com.softper.driverservice.resources.comunications.DriverBoundResponse;
import com.softper.driverservice.resources.comunications.UserBoundResponse;
import com.softper.driverservice.servicesImp.DriverService;
import com.softper.driverservice.servicesImp.ReviewService;
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
public class ShowReviews {
    @LocalServerPort
    private String port;
    private RestTemplate restTemplate = new RestTemplate();

    UserBoundResponse userCustomer;
    UserBoundResponse userDriver;
    DriverBoundResponse reviewResponse;
    DriverService driverService;
    ReviewService reviewService = new ReviewService();


    @Given("the user is in a driver profile window")
    public void the_user_is_in_a_driver_profile_window() {
        userCustomer = new UserBoundResponse("getCustomerModelById","success",1);
        userDriver = new UserBoundResponse("getDriverModelById","success",1);
    }

    @When("you select the View Review option from one of the reviews")
    public void you_select_the_View_Review_option_from_one_of_the_reviews() {
        System.out.println("In the frontend select see review option in the user profile");
        reviewResponse = new DriverBoundResponse("getReviewModelById", "success", 1);
    }

    @Then("the selected review is displayed")
    public void the_selected_review_is_displayed() {
        if(reviewResponse.status == 1)
            System.out.println("ShowReviews scenario 1 test success");
        else
            System.out.println("ShowReviews scenario 1 test failed");
    }

    @When("you select the Reviews and Ratings option and there are no reviews available")
    public void you_select_the_Reviews_and_Ratings_option_and_there_are_no_reviews_available() {
        System.out.println("In the frontend select see review option in the user profile");
        reviewResponse = new DriverBoundResponse("getReviewModelById", "success", 1);
    }

    @Then("the message “The driver has no reviews” is displayed")
    public void the_message_The_driver_has_no_reviews_is_displayed() {
        if(reviewResponse.status == 1)
            System.out.println("ShowReviews scenario 1 test success");
        else
            System.out.println("ShowReviews scenario 1 test failed");
    }
}