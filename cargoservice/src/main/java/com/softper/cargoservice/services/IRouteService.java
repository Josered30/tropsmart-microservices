package com.softper.cargoservice.services;


import com.softper.cargoservice.models.Route;
import com.softper.cargoservice.resources.comunications.RouteResponse;
import com.softper.cargoservice.services.ICrudService;

public interface IRouteService extends ICrudService<Route> {
    RouteResponse getRouteInfo(int cargoId);
    RouteResponse findAllRoutes();
    RouteResponse findRouteById(int routeId);
}
