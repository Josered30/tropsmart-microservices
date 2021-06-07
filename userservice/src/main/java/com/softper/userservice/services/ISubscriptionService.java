package com.softper.userservice.services;

import com.softper.userservice.models.Subscription;
import com.softper.userservice.resources.comunications.UserBoundResponse;

public interface ISubscriptionService extends ICrudService<Subscription> {
    UserBoundResponse findSubscriptionById(int subscriptionId);
    UserBoundResponse subscribe(int userId, int planId);
    UserBoundResponse findSubscriptionsByUserId(int userId);
    UserBoundResponse findAllSubscriptions();
    UserBoundResponse cancelSubscription(int subscriptionId);
    UserBoundResponse enableSubscriptionById(int subscriptionId);
    UserBoundResponse deleteSubscriptionBySubscriptionId(int subscriptionId);
    UserBoundResponse getSubscriptionModelById(int subscriptionId);
}
