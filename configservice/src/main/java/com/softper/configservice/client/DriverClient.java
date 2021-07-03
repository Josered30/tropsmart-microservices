package com.softper.configservice.client;

import com.softper.configservice.resources.comunications.DriverBoundResponse;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("driver-service")
@RequestMapping("/driverservice/drivers")
public interface DriverClient {
    @GetMapping("/by-driverid/{driverId}")
    public ResponseEntity<DriverBoundResponse> getDriverModelById(@PathVariable(value = "driverId") int driverId);

}
