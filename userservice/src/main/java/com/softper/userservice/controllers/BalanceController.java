package com.softper.userservice.controllers;

import com.softper.userservice.resources.comunications.UserBoundResponse;
import com.softper.userservice.services.IBalanceService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/userservice/balances")
public class BalanceController {
    
    @Autowired
    private IBalanceService balanceService;

    @GetMapping()
    public ResponseEntity<UserBoundResponse> findAllBalances()
    {
        UserBoundResponse result = balanceService.findAllBalances();

        //if(!result.success)
        //    return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(result,HttpStatus.OK);
    }

    @GetMapping("/{balanceId}")
    public ResponseEntity<UserBoundResponse> findBalanceById(@PathVariable(value = "balanceId")int balanceId)
    {
        UserBoundResponse result =  balanceService.findBalanceById(balanceId);

        //if(!result.success)
        //    return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(result,HttpStatus.OK);
    }

    @GetMapping("/by-balanceid/{balanceId}")
    public ResponseEntity<UserBoundResponse> getBalanceModelById(@PathVariable(value = "balanceId")int balanceId)
    {
        UserBoundResponse result = balanceService.getBalanceModelById(balanceId);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    /*

    @GetMapping("/by-userid/{userId}")
    public ResponseEntity<UserBoundResponse> findBalanceByUserId(@PathVariable(value = "userId")int userId)
    {
        CustomerBoundResponse result = balanceService.findBalanceByUserId(userId);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/by-personid/{personId}")
    public ResponseEntity<UserBoundResponse> findBalanceByPersonId(@PathVariable(value = "personId")int personId)
    {
        CustomerBoundResponse result = balanceService.findBalanceByPersonId(personId);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    */

    @GetMapping("/balances/by-personid/{personId}/recharge/{credits}")
    public ResponseEntity<UserBoundResponse> rechargeMoneyByPersonId(@PathVariable(value = "personId")int personId, @PathVariable(value = "credits")double credits)
    {
        UserBoundResponse result = balanceService.rechargeCreditsByPersonId(personId, credits);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    
    
}
