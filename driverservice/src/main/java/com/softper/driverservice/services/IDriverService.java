package com.softper.driverservice.services;

import com.softper.driverservice.models.Driver;
import com.softper.driverservice.models.Location;
import com.tropsmart.resources.comunications.DriverBoundResponse;


public interface IDriverService extends ICrudService<Driver> {
    DriverBoundResponse findNearDrivers(Location location);
    DriverBoundResponse findDriverById(int driverId);
    DriverBoundResponse findAllDrivers();
    DriverBoundResponse generateNewDriver(int personId);
    DriverBoundResponse findDriverByPersonId(int personId);
}
