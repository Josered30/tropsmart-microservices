package com.softper.userservice.resources.outputs;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class CargoOutputFixed {
    private int id;
    private String customer;
    private String driver;
    private double weight;
    private String description;
    private double servicePrice;
}
