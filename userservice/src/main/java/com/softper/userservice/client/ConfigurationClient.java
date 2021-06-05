package com.softper.userservice.client;


import com.softper.userservice.client.fallbacks.ConfigurationClientHystrixFallbackFactory;
import com.softper.userservice.models.Customer;
import com.softper.userservice.resources.comunications.ConfigBoundResponse;

import com.softper.userservice.resources.comunications.CustomerBoundResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;


@FeignClient(value = "config-service", fallback = ConfigurationClientHystrixFallbackFactory.class)
@RequestMapping("/configservice/configurations")
public interface ConfigurationClient {
    @PostMapping("/")
    public ResponseEntity<ConfigBoundResponse> generateConfiguration();
}


