package com.softper.driverservice.services;

import com.softper.driverservice.models.Driver;
import com.softper.driverservice.models.Location;
import com.softper.driverservice.resources.comunications.DriverBoundResponse;
import com.softper.driverservice.services.ICrudService;

public interface IDriverService extends ICrudService<Driver> {
    DriverBoundResponse findNearDrivers(Location location);
    DriverBoundResponse findDriverById(int driverId);
    DriverBoundResponse findAllDrivers();
    Driver generateNewDriver(int personId);
    Driver findDriverByPersonId(int personId);
    Driver getDriverById(int driverId);
}
