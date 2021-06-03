package com.softper.configservice.services;

import com.softper.configservice.models.PaymentMethod;
import com.softper.configservice.resources.comunications.ConfigBoundResponse;
import com.softper.configservice.resources.inputs.PaymentMethodInput;

public interface IPaymentMethodService extends ICrudService<PaymentMethod> {
    ConfigBoundResponse findAllPaymentMethod();
    ConfigBoundResponse findPaymentMethodById(int paymentMethodId);
    ConfigBoundResponse findPaymentMethodByUserId(int userId);
    ConfigBoundResponse addPaymentMethodByUserId(int userId, PaymentMethodInput paymentMethodInput);
}
