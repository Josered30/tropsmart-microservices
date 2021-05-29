package com.softper.configservice.controllers;

import com.softper.configservice.resources.comunications.ConfigBoundResponse;
import com.softper.configservice.resources.inputs.PaymentMethodInput;
import com.softper.configservice.servicesImp.PaymentMethodService;
import com.softper.configservice.models.PaymentMethod;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("configservice/payment-methods")
public class PaymentMethodController {
    @Autowired
    private PaymentMethodService paymentMethodService;

    @GetMapping
    public ResponseEntity<?> findAllPaymentMethods()
    {
        ConfigBoundResponse result = paymentMethodService.findAllPaymentMethod();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("users/{userId}")
    public ResponseEntity<?> findPaymentMethodsByUser(@PathVariable(value = "userId")int userId)
    {
        ConfigBoundResponse result = paymentMethodService.findPaymentMethodByUserId(userId);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("users/{userId}")
    public ResponseEntity<?> addPaymentMethod(@PathVariable(value = "userId")int userId, @Valid @RequestBody PaymentMethodInput paymentMethodInput)
    {
        ConfigBoundResponse result = paymentMethodService.addPaymentMethodByUserId(userId, paymentMethodInput);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
