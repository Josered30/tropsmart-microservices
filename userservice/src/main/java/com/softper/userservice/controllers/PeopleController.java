package com.softper.userservice.controllers;

import com.softper.userservice.models.Person;
import com.softper.userservice.resources.comunications.UserBoundResponse;
import com.softper.userservice.servicesImp.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/userservice/people")
public class PeopleController {
    @Autowired
    private PersonService personService;

    @GetMapping
    public ResponseEntity<UserBoundResponse> findAllPersons() {
        UserBoundResponse result = personService.findAllPersons();

        //if(!result.success)
        //    return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(result,HttpStatus.OK);
    }
    

    
    @GetMapping("{personId}")
    public ResponseEntity<UserBoundResponse> findPersonById(@PathVariable(value = "personId")int personId) {
        UserBoundResponse result = personService.findPeopleById(personId);
        //if(!result.success)
        //    return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/by-personid/{personId}")
    public ResponseEntity<UserBoundResponse> getPersonModelById(@PathVariable(value = "personId")int personId)
    {
        UserBoundResponse result = personService.getPersonModelById(personId);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    
}
