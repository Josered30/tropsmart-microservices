package com.softper.configservice.resources.outputs;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PriceOutput {
    private double totalPrice;
    private double tax;
    private String priceFrom;
}
