package com.softper.driverservice.controllers;

import com.softper.driverservice.models.Driver;
import com.softper.driverservice.servicesImp.ReviewService;
import com.softper.driverservice.servicesImp.DriverService;

import com.tropsmart.resources.comunications.DriverBoundResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/driverservice/drivers")
public class DriversController {

    @Autowired
    private DriverService driverService;

    @Autowired
    private ReviewService reviewService;

    @GetMapping
    public ResponseEntity<DriverBoundResponse> findAllDrivers()
    {
        DriverBoundResponse result = driverService.findAllDrivers();

        //if(!result.success)
        //    return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/{driverId}")
    public ResponseEntity<DriverBoundResponse> findDriverById(@PathVariable(value = "driverId") int driverId)
    {
        DriverBoundResponse result = driverService.findDriverById(driverId);

        //if(!result.success)
        //    return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/{driverId}/reviews")
    public ResponseEntity<DriverBoundResponse> findReviewsByDriverId(@PathVariable(value = "driverId")int driverId)
    {
        DriverBoundResponse result = reviewService.findReviewsByDriverId(driverId);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    //Driver Feign Client =======================================================


    @PostMapping("/{personId}")
    public ResponseEntity<DriverBoundResponse> generateNewDriver(@PathVariable(value = "personId")int personId)
    {
        DriverBoundResponse result = driverService.generateNewDriver(personId);

        return new ResponseEntity<DriverBoundResponse>(result, HttpStatus.OK);
    }

    @GetMapping("/by-person-id/{personId}")
    public ResponseEntity<DriverBoundResponse> getDriverByPersonId(@PathVariable(value = "personId") int personId)
    {
        DriverBoundResponse result = driverService.findDriverByPersonId(personId);
        return new ResponseEntity<DriverBoundResponse>(result, HttpStatus.OK);
    }
}
