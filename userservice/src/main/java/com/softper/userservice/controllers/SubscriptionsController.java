package com.softper.userservice.controllers;

import com.softper.userservice.resources.comunications.UserBoundResponse;
import com.softper.userservice.servicesImp.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/userservice/subscriptions")
public class SubscriptionsController {
    @Autowired
    private SubscriptionService subscriptionService;

    @GetMapping
    public ResponseEntity<UserBoundResponse> findAllSubscriptions()
    {
        UserBoundResponse result = subscriptionService.findAllSubscriptions();

        //if(!result.success)
        //    return new ResponseEntity<>(result, HttpStatus.EXPECTATION_FAILED);

        return new ResponseEntity<>(result,HttpStatus.OK);
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<UserBoundResponse> findSubscriptionsByUserId(@PathVariable(value = "userId")int userId)
    {
        UserBoundResponse result = subscriptionService.findSubscriptionsByUserId(userId);

        //if(!result.success)
        //    return new ResponseEntity<>(result, HttpStatus.EXPECTATION_FAILED);

        return new ResponseEntity<>(result,HttpStatus.OK);
    }


    @PostMapping("/users/{userId}/plans/{planId}")
    public ResponseEntity<UserBoundResponse> subscribeDriver(@PathVariable(value = "userId")int userId, @PathVariable(value = "planId")int planId)
    {
        UserBoundResponse result = subscriptionService.subscribe(userId, planId);

        //if(!result.success)
        //    return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(result,HttpStatus.OK);
    }

    @PutMapping("{subscriptionId}/disable")
    public ResponseEntity<UserBoundResponse> cancelSubscription(@PathVariable(value = "subscriptionId")int subscriptionId)
    {
        UserBoundResponse result = subscriptionService.cancelSubscription(subscriptionId);

        //if(!result.success)
        //    return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PutMapping("{subscriptionId}/enable")
    public ResponseEntity<UserBoundResponse> enableSubscription(@PathVariable(value = "subscriptionId")int subscriptionId)
    {
        UserBoundResponse result = subscriptionService.enableSubscriptionById(subscriptionId);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping("{subscriptionId}")
    public ResponseEntity<UserBoundResponse> deleteSubscriptionBySubscriptionId(@PathVariable(value = "subscriptionId")int subscriptionId)
    {
        UserBoundResponse result = subscriptionService.deleteSubscriptionBySubscriptionId(subscriptionId);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}