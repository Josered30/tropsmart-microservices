package com.softper.cargoservice.services;

import com.softper.cargoservice.resources.comunications.CargoBoundResponse;
import com.softper.cargoservice.resources.inputs.CargoInput;
import com.softper.cargoservice.models.Cargo;
//import com.softper.cargoservice.resources.comunications.CargoResponse;
//import com.softper.cargoservice.resources.comunications.CargoResponseFixed;
import com.softper.cargoservice.services.ICrudService;

import org.springframework.stereotype.Service;

@Service
public interface ICargoService extends ICrudService<Cargo>{
    
    CargoBoundResponse findCargoesByCustomerId(int customerId);
    CargoBoundResponse addCargoByCustomerId(int customerId, CargoInput cargoInput);
    CargoBoundResponse findCargoById(int cargoId);
    CargoBoundResponse findAllCargoes();
    CargoBoundResponse findAllCargoesFixed();
    CargoBoundResponse confirmCargoRequest(int cargoId);
    CargoBoundResponse setCargoDelivered(int cargoId);
    CargoBoundResponse rejectCargoById(int cargoId);
    CargoBoundResponse findCargoesByDriverId(int driverId);
    CargoBoundResponse findRequestedCargoesByDriverId(int driverId);
    CargoBoundResponse findConfirmedCargoesByDriverId(int driverId);
    CargoBoundResponse findFinishedCargoesByDriverId(int driverId);
}
