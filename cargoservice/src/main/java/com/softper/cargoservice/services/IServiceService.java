package com.softper.cargoservice.services;

import com.softper.cargoservice.models.Service;
import com.softper.cargoservice.resources.comunications.ServiceResponse;
import com.softper.cargoservice.services.ICrudService;

public interface IServiceService extends ICrudService<Service> {
    ServiceResponse findSomeServiceByDriverId(int driverId);
    ServiceResponse findServicesByDriverId(int driverId);
    ServiceResponse findAllServices();
    ServiceResponse createService(int driverId);
}
