package com.softper.customerservice.controllers;

import com.softper.customerservice.resources.comunications.CustomerBoundResponse;
import com.softper.customerservice.services.IBalanceService;

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
    public ResponseEntity<CustomerBoundResponse> findAllBalances()
    {
        CustomerBoundResponse result = balanceService.findAllBalances();

        //if(!result.success)
        //    return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(result,HttpStatus.OK);
    }

    @GetMapping("/{balanceId}")
    public ResponseEntity<CustomerBoundResponse> findBalanceById(@PathVariable(value = "balanceId")int balanceId)
    {
        CustomerBoundResponse result =  balanceService.findBalanceById(balanceId);

        //if(!result.success)
        //    return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(result,HttpStatus.OK);
    }

    /*

    @GetMapping("/by-userid/{userId}")
    public ResponseEntity<CustomerBoundResponse> findBalanceByUserId(@PathVariable(value = "userId")int userId)
    {
        CustomerBoundResponse result = balanceService.findBalanceByUserId(userId);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/by-personid/{personId}")
    public ResponseEntity<CustomerBoundResponse> findBalanceByPersonId(@PathVariable(value = "personId")int personId)
    {
        CustomerBoundResponse result = balanceService.findBalanceByPersonId(personId);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/by-userid/{userId}/recharge/{money}")
    public ResponseEntity<CustomerBoundResponse> rechargeMoneyByUserId(@PathVariable(value = "userId")int userId, @PathVariable(value = "money")double money)
    {
        CustomerBoundResponse result = balanceService.rechargeCreditsByUserId(userId, money);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    */
    
}
