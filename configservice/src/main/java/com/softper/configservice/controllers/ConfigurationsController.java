package com.softper.configservice.controllers;

import com.softper.configservice.resources.comunications.ConfigBoundResponse;
import com.softper.configservice.resources.inputs.ConfigurationInput;
import com.softper.configservice.resources.inputs.PaymentMethodInput;
import com.softper.configservice.servicesImp.ConfigurationService;
import com.softper.configservice.servicesImp.PaymentMethodService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("configservice/configurations")
public class ConfigurationsController {

    @Autowired
    private ConfigurationService configurationService;

    @Autowired
    private PaymentMethodService paymentMethodService;

    @GetMapping
    public ResponseEntity<ConfigBoundResponse> findAllConfigurations()
    {
        ConfigBoundResponse result = configurationService.findAllConfigurations();

        //if(!result.success)
        //    return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }


    @GetMapping("/users/{userId}")
    public ResponseEntity<ConfigBoundResponse> findConfigurationByUserId(@PathVariable(value = "userId") int userId)
    {
        ConfigBoundResponse result = configurationService.findConfigurationByUserId(userId);

        //if(!result.success)
        //    return new ResponseEntity<>(result,HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/users/{userId}/payment-method")
    public ResponseEntity<ConfigBoundResponse> findPaymentMethodsByUserId(@PathVariable(value = "userId")int userId)
    {
        ConfigBoundResponse result = paymentMethodService.findPaymentMethodByUserId(userId);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/users/{userId}/payment-method")
    public ResponseEntity<ConfigBoundResponse> addPaymentMethod(@PathVariable(value="userId")int userId, @Valid @RequestBody PaymentMethodInput paymentMethodInput)
    {
        ConfigBoundResponse result = configurationService.addPaymentMethod(userId, paymentMethodInput);

        //if(!result.success)
        //    return new ResponseEntity<>(result,HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PutMapping("/users/{userId}")
    public ResponseEntity<ConfigBoundResponse> updateConfiguration(@PathVariable(value="userId")int userId, @Valid @RequestBody ConfigurationInput configurationInput)
    {
        ConfigBoundResponse result = configurationService.updateConfiguration(userId, configurationInput);

        //if(!result.success)
        //    return new ResponseEntity<>(result,HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    //======FeignClient Methods========================
    @PostMapping("/")
    public ResponseEntity<ConfigBoundResponse> generateConfiguration()
    {
        ConfigBoundResponse result = configurationService.generateConfiguration();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}