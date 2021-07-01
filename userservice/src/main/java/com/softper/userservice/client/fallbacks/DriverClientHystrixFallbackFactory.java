package com.softper.userservice.client.fallbacks;

import com.softper.userservice.client.DriverClient;
import com.softper.userservice.models.Driver;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;


@Component
public class DriverClientHystrixFallbackFactory implements DriverClient {
    @Override
    public ResponseEntity<Driver> getDriverById(int driverId) {
        return null;
    }

    @Override
    public ResponseEntity<Driver> generateNewDriver(int personId) {
        return null;
    }

    @Override
    public ResponseEntity<Driver> getDriverByPersonId(int personId) {
        return null;
    }
}
