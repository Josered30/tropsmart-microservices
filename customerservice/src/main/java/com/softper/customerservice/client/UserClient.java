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
    @GetMapping("/users/by-personid/{personId}")
    public ResponseEntity<UserBoundResponse> findUserByPersonId(@PathVariable(value = "personId")int personId);

    //Balance
    
    @GetMapping("/balances/by-userid/{userId}")
    public ResponseEntity<UserBoundResponse> findBalanceByUserId(@PathVariable(value = "userId")int userId);

    @GetMapping("/balances/by-userid/{userId}/recharge/{money}")
    public ResponseEntity<UserBoundResponse> rechargeMoneyByUserId(@PathVariable(value = "userId")int userId, @PathVariable(value = "money")double money);
    
    @GetMapping("/balances/by-customerid/{customerId}")
    public ResponseEntity<UserBoundResponse> findBalanceByCustomerId(@PathVariable(value = "customerId")int customerId);
    

    //People

    @GetMapping("/people")
    public ResponseEntity<UserBoundResponse> findAllPersons();
    
    @GetMapping("/people/{personId}")
    public ResponseEntity<UserBoundResponse> findPersonById(@PathVariable(value = "personId")int personId);

  
}
