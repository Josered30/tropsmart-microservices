package com.softper.cargoservice.services;


import com.softper.cargoservice.models.Route;
import com.softper.cargoservice.resources.comunications.CargoBoundResponse;
import com.softper.cargoservice.services.ICrudService;

public interface IRouteService extends ICrudService<Route> {
    CargoBoundResponse getRouteInfo(int cargoId);
    CargoBoundResponse findAllRoutes();
    CargoBoundResponse findRouteById(int routeId);
}
