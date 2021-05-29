package com.softper.userservice.controllers;

import com.softper.userservice.resources.comunications.UserBoundResponse;
import com.softper.userservice.resources.inputs.PlanInput;
import com.softper.userservice.servicesImp.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("api/userservice/plans")
public class PlansController {

    @Autowired
    private PlanService planService;

    @GetMapping
    public ResponseEntity<UserBoundResponse> findAllPlans() {
        UserBoundResponse result =  planService.findAllPlans();

        //if(!result.success)
        //    return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(result,HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<UserBoundResponse> addNewPlan(@Valid @RequestBody PlanInput planInput) {
        UserBoundResponse result = planService.registerPlan(planInput);

        //if(!result.success)
        //    return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(result,HttpStatus.OK);
    }

    @GetMapping("/{planId}")
    public ResponseEntity<UserBoundResponse> findPlanById(@PathVariable(value = "planId") int planId)
    {
        UserBoundResponse result = planService.findPlanById(planId);

        /*
        if(!result.success)
            return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
            */

        return new ResponseEntity<>(result,HttpStatus.OK);
    }

    @GetMapping("/{priceValue}/more")
    public ResponseEntity<UserBoundResponse> findPlansHigherThan(@PathVariable(value = "priceValue")double priceValue)
    {
        UserBoundResponse result =  planService.findPlansHigherThan(priceValue);

        //if(!result.success)
        //    return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(result,HttpStatus.OK);
    }

    @GetMapping("/{priceValue}/less")
    public ResponseEntity<UserBoundResponse> findPlansLessThan(@PathVariable(value = "priceValue")double priceValue)
    {
        UserBoundResponse result = planService.findPlansLessThan(priceValue);

        //if(!result.success)
        //    return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(result,HttpStatus.OK);
    }

    @GetMapping("/{priceValue}")
    public ResponseEntity<UserBoundResponse> findByPrice(@PathVariable(value = "priceValue")double priceValue)
    {
        UserBoundResponse result = planService.findPlansByPrice(priceValue);

        //if(!result.success)
        //    return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(result,HttpStatus.OK);

    }
}
