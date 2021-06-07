package com.softper.userservice.resources.outputs;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ReviewOutput {
    private int id;
    private String Customer;
    private String Driver;
    private String cargo;
    private String commentary;
    private double calification;
}
