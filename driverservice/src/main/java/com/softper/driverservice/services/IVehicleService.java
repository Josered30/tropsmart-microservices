package com.softper.driverservice.services;

import com.softper.driverservice.models.Vehicle;
import com.softper.driverservice.resources.comunications.DriverBoundResponse;
import com.softper.driverservice.resources.inputs.VehicleInput;
import com.softper.driverservice.services.ICrudService;

public interface IVehicleService extends ICrudService<Vehicle> {
    DriverBoundResponse findVehiclesByDriverId(int driverId);
    DriverBoundResponse addVehicleByUserId(int driverId, VehicleInput vehicleInput);
    DriverBoundResponse findAllVehicles();
    DriverBoundResponse findVehicleById(int vehicleId);
}
