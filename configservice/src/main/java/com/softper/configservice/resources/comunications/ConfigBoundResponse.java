package com.softper.configservice.resources.comunications;

import java.util.List;

import com.softper.configservice.models.Configuration;
import com.softper.configservice.resources.outputs.ConfigurationOutput;
import com.softper.configservice.resources.outputs.PaymentMethodOutput;
import com.softper.configservice.resources.outputs.ReviewOutput;

import lombok.Data;
import lombok.NoArgsConstructor; 

@Data
public class ConfigBoundResponse extends BaseResponse {

    public ConfigBoundResponse(String title, String message, Integer status) { super(title, message, status);}
    
    ConfigurationOutput configurationOutput;

    List<ConfigurationOutput> configurationOutputs;

    PaymentMethodOutput paymentMethodOutput;

    List<PaymentMethodOutput> paymentMethodOutputs;

    ReviewOutput reviewOutput;

    List<ReviewOutput> reviewOutputs;
}
