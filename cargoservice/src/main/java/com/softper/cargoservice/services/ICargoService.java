package com.softper.cargoservice.services;

import com.softper.cargoservice.resources.inputs.CargoInput;
import com.softper.cargoservice.models.Cargo;
import com.softper.cargoservice.resources.comunications.CargoResponse;
import com.softper.cargoservice.resources.comunications.CargoResponseFixed;
import com.softper.cargoservice.services.ICrudService;

import org.springframework.stereotype.Service;

@Service
public interface ICargoService extends ICrudService<Cargo>{
    CargoResponse findCargoesByCustomerId(int customerId);
    CargoResponse addCargoByCustomerId(int customerId, CargoInput cargoInput);
    CargoResponse findCargoById(int cargoId);
    CargoResponse findAllCargoes();
    CargoResponse findAllCargoesFixed();
    CargoResponse confirmCargoRequest(int cargoId);
    CargoResponse setCargoDelivered(int cargoId);
    CargoResponse rejectCargoById(int cargoId);
    CargoResponse findCargoesByDriverId(int driverId);
    CargoResponse findRequestedCargoesByDriverId(int driverId);
    CargoResponse findConfirmedCargoesByDriverId(int driverId);
    CargoResponse findFinishedCargoesByDriverId(int driverId);
}
