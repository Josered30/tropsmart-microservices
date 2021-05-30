package com.softper.userservice.client;

import org.springframework.web.bind.annotation.RequestMapping;

import com.softper.userservice.models.Customer;
import com.softper.userservice.resources.comunications.CustomerBoundResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient("customerservice")
@RequestMapping("/customerservice/customer")
public interface CustomerClient {
    
    @GetMapping("/{cargoId}")
    public ResponseEntity<?> findCustomersById(@PathVariable(value = "cargoId") int cargoId);

    @PostMapping("/{personId}")
    public ResponseEntity<Customer> generateNewCustomer(@PathVariable(value = "personId")int personId);

}
