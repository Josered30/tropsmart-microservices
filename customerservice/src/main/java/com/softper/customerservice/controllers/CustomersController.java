package com.softper.customerservice.controllers;

import com.softper.customerservice.models.Customer;
import com.softper.customerservice.resources.comunications.CustomerBoundResponse;
import com.softper.customerservice.services.ICustomerService;
import com.softper.customerservice.servicesImp.CustomerService;
//import com.softper.customerservice.resources.comunications.CargoResponse;
//import com.softper.customerservice.servicesImp.CargoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/customerservice/customers")
public class CustomersController {

    @Autowired
    private ICustomerService customerService;

    //@Autowired
    //private CargoService cargoService;

    @GetMapping
    public ResponseEntity<CustomerBoundResponse> findAllCustomers()
    {
        CustomerBoundResponse result = customerService.findAllCustomers();

        //if(!result.success)
        //    return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<CustomerBoundResponse> findCustomerById(@PathVariable(value = "customerId") int customerId)
    {
        CustomerBoundResponse result = customerService.findCustomerById(customerId);

        //if(!result.success)
        //    return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }


    //@GetMapping("/{customerId}/cargoes")
    //public ResponseEntity<CargoResponse> getCargoesByCustomerId(@PathVariable(value = "customerId")int customerId)
    //{
    //    CargoResponse result = cargoService.findCargoesByCustomerId(customerId);
    //    return new ResponseEntity<>(result, HttpStatus.OK);
    //    return null;
    //}

    @PutMapping("/{customerId}/credits/{credits}")
    public ResponseEntity<CustomerBoundResponse> rechargeCreditsByCustomer(@PathVariable(value = "customerId")int customerId, @PathVariable(value = "credits")double credits)
    {
        CustomerBoundResponse result = customerService.rechargeCreditsByCustomerId(customerId, credits);

        //if(!result.success)
        //    return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    //Customer Feign Client======================================================================


    @PostMapping("/{personId}")
    public ResponseEntity<CustomerBoundResponse> generateNewCustomer(@PathVariable(value = "personId")int personId)
    {
        CustomerBoundResponse result = customerService.generateNewCustomer(personId);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/by-person-id/{personId}")
    public ResponseEntity<CustomerBoundResponse> findCustomerByPersonId(@PathVariable(value = "personId") int personId)
    {
        CustomerBoundResponse result = customerService.findCustomerByPersonId(personId);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}