package com.softper.configservice.services;

import com.softper.configservice.models.Configuration;
import com.softper.configservice.resources.comunications.ConfigBoundResponse;
import com.softper.configservice.resources.inputs.ConfigurationInput;
import com.softper.configservice.resources.inputs.PaymentMethodInput;

public interface IConfigurationService extends ICrudService<Configuration> {
    ConfigBoundResponse findAllConfigurations();
    ConfigBoundResponse findConfigurationByUserId(int userId);
    ConfigBoundResponse addPaymentMethod(int userId, PaymentMethodInput paymentMethodInput);
    ConfigBoundResponse updateConfiguration(int userId, ConfigurationInput configurationInput);
    ConfigBoundResponse generateConfiguration(int userId);
    ConfigBoundResponse generateConfiguration();
}
