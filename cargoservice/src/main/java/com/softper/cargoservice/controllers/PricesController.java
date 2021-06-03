package com.softper.cargoservice.controllers;

import com.softper.cargoservice.resources.comunications.PriceResponse;
import com.softper.cargoservice.servicesImp.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/cargoservice/prices")
public class PricesController {
    @Autowired
    PriceService priceService;

    @GetMapping
    public ResponseEntity<PriceResponse> findAllPrices()
    {
        PriceResponse result = priceService.findAllPrices();

        //if(!result.success)
        //    return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(result,HttpStatus.OK);
    }

    @GetMapping("/{priceId}")
    public ResponseEntity<PriceResponse> findPriceById(@PathVariable(value = "priceId")int priceId)
    {
        PriceResponse result = priceService.findPriceById(priceId);

        //if(!result.success)
        //    return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(result,HttpStatus.OK);
    }

    @GetMapping("price-type/{priceType}")
    public ResponseEntity<PriceResponse> findPricesByPriceType(@PathVariable(value = "priceType")int priceType)
    {
        PriceResponse result =  priceService.findPricesByPriceType(priceType);

        if(!result.success)
            return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(result,HttpStatus.OK);
    }
}
