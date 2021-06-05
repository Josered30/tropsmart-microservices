package com.softper.driverservice.client;

import com.softper.driverservice.resources.comunications.UserBoundResponse;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("user-service")
@RequestMapping("/userservice")
public interface UserClient {

    @GetMapping("/people/{personId}")
    public ResponseEntity<UserBoundResponse> findPersonById(@PathVariable(value = "personId")int personId);
    
}
