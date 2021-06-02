package com.softper.cargoservice.controllers;

import com.softper.cargoservice.resources.inputs.CargoInput;
import com.softper.cargoservice.services.ICargoService;
import com.softper.cargoservice.resources.comunications.CargoBoundResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("/cargoservice/cargoes")
public class CargoesController {

    @Autowired
    private ICargoService cargoService;

    @GetMapping
    public ResponseEntity<CargoBoundResponse> findAllCargoes()
    {
        CargoBoundResponse result = cargoService.findAllCargoes();

        //if(!result.success)
        //    return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/fixed")
    public ResponseEntity<CargoBoundResponse> findAllCargoesFixed()
    {
        CargoBoundResponse result = cargoService.findAllCargoesFixed();

        //if(!result.success)
        //    return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
        //
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/{cargoId}")
    public ResponseEntity<CargoBoundResponse> findCargoById(@PathVariable(value = "cargoId")int cargoId)
    {
        CargoBoundResponse result = cargoService.findCargoById(cargoId);

        //if(!result.success)
        //    return new ResponseEntity<>(result,HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("customers/{customerId}")
    public ResponseEntity<CargoBoundResponse> findCargoesByCustomerId(@PathVariable(value="customerId")int customerId)
    {
        CargoBoundResponse result = cargoService.findCargoesByCustomerId(customerId);

        //if(!result.success)
        //    return new ResponseEntity<>(result,HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("drivers/{driverId}")
    public ResponseEntity<CargoBoundResponse> findCargoesByDriverId(@PathVariable(value="driverId")int driverId)
    {
        CargoBoundResponse result = cargoService.findCargoesByDriverId(driverId);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }


    @PutMapping("{cargoId}/confirms")
    public ResponseEntity<CargoBoundResponse> setCargoConfirmed(@PathVariable(value = "cargoId")int cargoId)
    {
        CargoBoundResponse result = cargoService.confirmCargoRequest(cargoId);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PutMapping("{cargoId}/deliver")
    public ResponseEntity<CargoBoundResponse> setCargoDelivered(@PathVariable(value="cargoId")int cargoId)
    {
        CargoBoundResponse result = cargoService.setCargoDelivered(cargoId);

        //if(!result.success)
        //    return new ResponseEntity<>(result,HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PutMapping("{cargoId}/reject")
    public ResponseEntity<CargoBoundResponse> setCargoRejected(@PathVariable(value = "cargoId")int cargoId)
    {
        CargoBoundResponse result = cargoService.rejectCargoById(cargoId);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/customers/{customerId}")
    public ResponseEntity<CargoBoundResponse> postCargo(@PathVariable(value = "customerId")int customerId, @RequestBody CargoInput cargoInput)
    {
        CargoBoundResponse result = cargoService.addCargoByCustomerId(customerId,cargoInput);
        //if(!result.success)
        //    return new ResponseEntity<>(result,HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }




}
