package com.softper.userservice.client;

import com.softper.userservice.models.Driver;
import com.softper.userservice.resources.comunications.DriverBoundResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@FeignClient("driver-service")
@RequestMapping("/driverservice/drivers")
public interface DriverClient {

    @PostMapping("/{personId}")
    public ResponseEntity<DriverBoundResponse> generateNewDriver(@PathVariable(value = "personId")int personId);

    @GetMapping("/by-driverid/{driverId}")
    public ResponseEntity<DriverBoundResponse> getDriverModelById(@PathVariable(value = "driverId") int driverId);

}
