package com.softper.driverservice.resources.inputs;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DriverInput {
    private String firstName;
    private String lastName;
    private String License;
}
