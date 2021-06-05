package com.softper.configservice.resources.outputs;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class ConfigurationOutput {
    private Integer id;
    private String firstName;
    private String lastName;
    private String Phone;
    private String language;
    private String paymentCurrency;
}
