package com.softper.userservice.client;

import org.springframework.web.bind.annotation.RequestMapping;

import com.softper.userservice.models.Customer;
import com.softper.userservice.resources.comunications.CustomerBoundResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient("CUSTOMER-SERVICE")
@RequestMapping("/customers")
public interface CustomerClient {
    
    @GetMapping("/{cargoId}")
    public ResponseEntity<CustomerBoundResponse> findCustomersById(@PathVariable(value = "cargoId") int cargoId);

    @PostMapping("/")
    public ResponseEntity<Customer> generateNewCustomer(@PathVariable(value = "personId")int personId);

}
