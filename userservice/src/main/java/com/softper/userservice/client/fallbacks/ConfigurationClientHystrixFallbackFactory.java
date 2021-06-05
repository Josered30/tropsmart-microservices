package com.softper.userservice.client.fallbacks;

import com.softper.userservice.client.ConfigurationClient;
import com.softper.userservice.models.Configuration;
import com.softper.userservice.resources.comunications.ConfigBoundResponse;
import com.softper.userservice.resources.outputs.ConfigurationOutput;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;


@Component
public class ConfigurationClientHystrixFallbackFactory  implements ConfigurationClient {

    @Override
    public ResponseEntity<ConfigBoundResponse> generateConfiguration() {
        Configuration newConfiguration = new Configuration();
        newConfiguration.setLanguage("None");
        newConfiguration.setPaymentCurrency("None");

        ConfigBoundResponse configurationBoundedResponse = new ConfigBoundResponse("NullGenerateConfiguration", null,1);

        ConfigurationOutput newConfigurationOutput = new ConfigurationOutput();
        //newConfigurationOutput.setId(newConfiguration.getId());
        newConfigurationOutput.setPaymentCurrency(newConfiguration.getPaymentCurrency());
        newConfigurationOutput.setLanguage(newConfiguration.getLanguage());

        configurationBoundedResponse.setConfigurationOutput(newConfigurationOutput);
        return ResponseEntity.ok(configurationBoundedResponse);
    }







}
