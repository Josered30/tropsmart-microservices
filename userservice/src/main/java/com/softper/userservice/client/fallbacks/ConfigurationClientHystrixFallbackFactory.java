package com.softper.userservice.client.fallbacks;

import com.softper.userservice.client.ConfigurationClient;
import com.tropsmart.resources.comunications.ConfigBoundResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;


@Component
public class ConfigurationClientHystrixFallbackFactory  implements ConfigurationClient {

    @Override
    public ResponseEntity<ConfigBoundResponse> generateConfiguration() {
        ConfigBoundResponse configurationBoundedResponse = new ConfigBoundResponse("generateConfiguration", "fallback response",-1);
        return ResponseEntity.ok(configurationBoundedResponse);
    }

}
