package com.softper.userservice.client;


import com.softper.userservice.client.fallbacks.ConfigurationClientHystrixFallbackFactory;
import com.tropsmart.resources.comunications.ConfigBoundResponse;
import org.springframework.cloud.openfeign.FeignClient;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;


@FeignClient(value = "config-service", fallback = ConfigurationClientHystrixFallbackFactory.class)
@RequestMapping("/configservice/configurations")
public interface ConfigurationClient {
    @PostMapping("/")
    public ResponseEntity<ConfigBoundResponse> generateConfiguration();
}


