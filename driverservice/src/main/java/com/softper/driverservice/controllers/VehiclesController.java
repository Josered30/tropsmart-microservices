package com.softper.driverservice.controllers;

import com.softper.driverservice.resources.comunications.DriverBoundResponse;
import com.softper.driverservice.resources.inputs.VehicleInput;
import com.softper.driverservice.servicesImp.VehicleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/driverservice/vehicles")
public class VehiclesController {

    @Autowired
    private VehicleService vehicleService;

    @GetMapping
    public ResponseEntity<DriverBoundResponse> findAllVehicles()
    {
        DriverBoundResponse result = vehicleService.findAllVehicles();

        //if(!result.success)
        //    return new ResponseEntity<>(result, HttpStatus.EXPECTATION_FAILED);

        return new ResponseEntity<>(result,HttpStatus.OK);
    }

    @GetMapping("/{vehicleId}")
    public ResponseEntity<DriverBoundResponse> findVehicleById(@PathVariable(value = "vehicleId")int vehicleId)
    {
        DriverBoundResponse result = vehicleService.findVehicleById(vehicleId);

        //if(!result.success)
        //    return new ResponseEntity<>(result, HttpStatus.EXPECTATION_FAILED);

        return new ResponseEntity<>(result,HttpStatus.OK);
    }

    @PostMapping("/drivers/{driverId}")
    public ResponseEntity<DriverBoundResponse> addVehicleByUserId(@PathVariable(value = "driverId")int driverId, @RequestBody VehicleInput vehicleInput)
    {
        DriverBoundResponse result = vehicleService.addVehicleByUserId(driverId, vehicleInput);

        //if(!result.success)
        //    return new ResponseEntity<>(result, HttpStatus.EXPECTATION_FAILED);

        return new ResponseEntity<>(result,HttpStatus.OK);
    }

    @GetMapping("/drivers/{driverId}")
    public ResponseEntity<DriverBoundResponse> findVehiclesByDriverId(@PathVariable(value = "driverId")int driverId)
    {
        DriverBoundResponse result = vehicleService.findVehiclesByDriverId(driverId);

        //if(!result.success)
        //    return new ResponseEntity<>(result, HttpStatus.EXPECTATION_FAILED);

        return new ResponseEntity<>(result,HttpStatus.OK);
    }
}
