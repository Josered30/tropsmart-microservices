package com.softper.customerservice.client;

import com.softper.customerservice.models.Person;
import com.softper.customerservice.resources.comunications.UserBoundResponse;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("user-service")
@RequestMapping("/userservice")
public interface UserClient {

    //User
    @GetMapping("/by-userid/{userId}")
    public ResponseEntity<UserBoundResponse> getUserModelById(@PathVariable(value = "userId")int userId);
    
    //Balance
    
    @GetMapping("/by-balanceid/{balanceId}")
    public ResponseEntity<UserBoundResponse> getBalanceModelById(@PathVariable(value = "balanceId")int balanceId);
    
    @GetMapping("/balances/by-personid/{personId}/recharge/{credits}")
    public ResponseEntity<UserBoundResponse> rechargeMoneyByPersonId(@PathVariable(value = "personId")int personId, @PathVariable(value = "credits")double credits);
    
    //@GetMapping("/balances/by-customerid/{customerId}")
    //public ResponseEntity<UserBoundResponse> findBalanceByCustomerId(@PathVariable(value = "customerId")int customerId);
    

    //People

    @GetMapping("/people")
    public ResponseEntity<UserBoundResponse> findAllPersons();
    
    @GetMapping("/by-personid/{personId}")
    public ResponseEntity<UserBoundResponse> getPersonModelById(@PathVariable(value = "personId")int personId);
    
}
