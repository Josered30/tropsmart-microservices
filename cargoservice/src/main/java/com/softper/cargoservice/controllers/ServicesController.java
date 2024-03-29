package com.softper.cargoservice.controllers;

import com.softper.cargoservice.models.ServiceRequest;
import com.softper.cargoservice.resources.comunications.CargoBoundResponse;
import com.softper.cargoservice.servicesImp.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/cargoservice/services")
public class ServicesController {
    @Autowired
    private ServiceService serviceService;

    @GetMapping
    public ResponseEntity<CargoBoundResponse> findAllServices()
    {
        CargoBoundResponse result = serviceService.findAllServices();

        //if(!result.success)
        //    return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(result,HttpStatus.OK);
    }

    @PostMapping("/drivers/{driverId}")
    public ResponseEntity<CargoBoundResponse> addServiceByDriverId(@PathVariable(value = "driverId")int driverId)
    {
        CargoBoundResponse result = serviceService.createService(driverId);

        //if(!result.success)
        //    return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(result,HttpStatus.OK);
    }

    @GetMapping("/drivers/{driverId}/some")
    public ResponseEntity<CargoBoundResponse> findServiceByDriverId(@PathVariable(value = "driverId")int driverId)
    {
        CargoBoundResponse result = serviceService.findSomeServiceByDriverId(driverId);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/drivers/{driverId}")
    public ResponseEntity<CargoBoundResponse> findServicesByDriverId(@PathVariable(value = "driverId")int driverId)
    {
        CargoBoundResponse result = serviceService.findServicesByDriverId(driverId);

        //if(!result.success)
        //    return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(result,HttpStatus.OK);
    }
}
