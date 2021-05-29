package com.softper.cargoservice.services;

import com.softper.cargoservice.models.Price;
import com.softper.cargoservice.resources.comunications.PriceResponse;
import com.softper.cargoservice.services.ICrudService;

public interface IPriceService extends ICrudService<Price> {
    PriceResponse findAllPrices();
    PriceResponse findPriceById(int priceId);
    PriceResponse findPricesByPriceType(int priceType);
}
