package com.softper.cargoservice.controllers;

import com.softper.cargoservice.resources.comunications.CargoBoundResponse;
import com.softper.cargoservice.servicesImp.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/cargoservice/routes")
public class RoutesController {

    @Autowired
    private RouteService routeService;

    @GetMapping
    public ResponseEntity<CargoBoundResponse> findAllRoutes()
    {
        CargoBoundResponse result = routeService.findAllRoutes();

        //if(!result.success)
        //    return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/{routeId}")
    public ResponseEntity<CargoBoundResponse> findRouteById(@PathVariable(value="routeId")int routeId)
    {
        CargoBoundResponse result = routeService.findRouteById(routeId);

        //if(!result.success)
        //    return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
