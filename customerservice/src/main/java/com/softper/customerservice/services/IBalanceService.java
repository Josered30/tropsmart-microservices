package com.softper.customerservice.services;

import com.softper.customerservice.models.Balance;
import com.softper.customerservice.resources.comunications.CustomerBoundResponse;

public interface IBalanceService extends ICrudService<Balance>{
    CustomerBoundResponse findBalanceById(int userId);
    CustomerBoundResponse findAllBalances();
    /*
    CustomerBoundResponse findBalanceByUserId(int userId);
    CustomerBoundResponse rechargeCreditsByUserId(int userId, double money);
    CustomerBoundResponse findBalanceByPersonId(int personId);
    */
}
