package com.softper.driverservice.resources.outputs;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class VehicleOutput {
    private String driver;
    private String model;
    private String brand;
    private double loadingCapacity;
}
