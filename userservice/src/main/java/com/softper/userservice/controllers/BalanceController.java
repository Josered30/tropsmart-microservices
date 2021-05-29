package com.softper.userservice.controllers;

import com.softper.userservice.resources.comunications.UserBoundResponse;
import com.softper.userservice.services.IBalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("api/userservice/balances")
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
}