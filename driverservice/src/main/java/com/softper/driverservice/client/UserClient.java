package com.softper.driverservice.client;


import com.tropsmart.resources.comunications.UserBoundResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("user-service")
@RequestMapping("/userservice")
public interface UserClient {

    @GetMapping("/people/{personId}")
    ResponseEntity<UserBoundResponse> findPersonById(@PathVariable(value = "personId")int personId);
    
}
