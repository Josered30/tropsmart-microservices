package com.softper.userservice.services;

import com.softper.userservice.models.Balance;
import com.softper.userservice.resources.comunications.UserBoundResponse;

public interface IBalanceService extends ICrudService<Balance>{
    UserBoundResponse findBalanceById(int userId);
    UserBoundResponse findAllBalances();
    UserBoundResponse getBalanceModelById(int balanceId);
    UserBoundResponse rechargeCreditsByPersonId(int personId, double credits);
}
