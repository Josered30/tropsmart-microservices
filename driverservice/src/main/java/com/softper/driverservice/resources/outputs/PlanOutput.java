package com.softper.driverservice.resources.outputs;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PlanOutput {
    private int id;
    private String planName;
    private int durationDays;
    private double price;
    private double tax;
}
