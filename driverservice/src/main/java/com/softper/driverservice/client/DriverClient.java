package com.softper.driverservice.client;

import com.softper.driverservice.models.Driver;
import com.softper.driverservice.resources.comunications.DriverBoundResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@FeignClient("driver-service")
@RequestMapping("/driverservice/drivers")
public interface DriverClient {

    /*
    @GetMapping("/by-id/{driverId}")
    public ResponseEntity<Driver> getDriverById(@PathVariable(value = "driverId") int driverId);

    @PostMapping("/{personId}")
    public ResponseEntity<Driver> generateNewDriver(@PathVariable(value = "personId")int personId);

    @GetMapping("/by-personId/{personId}")
    public ResponseEntity<Driver> getDriverByPersonId(@PathVariable(value = "personId") int personId);
    */

    @PostMapping("/{personId}")
    public ResponseEntity<DriverBoundResponse> generateNewDriver(@PathVariable(value = "personId")int personId);

    @GetMapping("/by-driverid/{driverId}")
    public ResponseEntity<DriverBoundResponse> getDriverModelById(@PathVariable(value = "driverId") int driverId);

}
