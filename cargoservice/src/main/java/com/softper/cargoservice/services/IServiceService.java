package com.softper.cargoservice.services;

import com.softper.cargoservice.models.Service;
import com.softper.cargoservice.resources.comunications.CargoBoundResponse;
import com.softper.cargoservice.services.ICrudService;

public interface IServiceService extends ICrudService<Service> {
    CargoBoundResponse findSomeServiceByDriverId(int driverId);
    CargoBoundResponse findServicesByDriverId(int driverId);
    CargoBoundResponse findAllServices();
    CargoBoundResponse createService(int driverId);
}
