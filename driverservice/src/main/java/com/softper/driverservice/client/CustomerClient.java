package com.softper.driverservice.client;

import com.softper.driverservice.resources.comunications.CustomerBoundResponse;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("customer-service")
@RequestMapping("/customerservice")
public interface CustomerClient {
    
    
    @GetMapping("/customers/{customerId}")
    public ResponseEntity<CustomerBoundResponse> findCustomersById(@PathVariable(value = "customerId") int customerId);
}
