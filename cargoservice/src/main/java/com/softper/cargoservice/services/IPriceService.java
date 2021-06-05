package com.softper.cargoservice.services;

import com.softper.cargoservice.models.Price;
import com.softper.cargoservice.resources.comunications.CargoBoundResponse;
import com.softper.cargoservice.services.ICrudService;

public interface IPriceService extends ICrudService<Price> {
    CargoBoundResponse findAllPrices();
    CargoBoundResponse findPriceById(int priceId);
    CargoBoundResponse findPricesByPriceType(int priceType);
}
