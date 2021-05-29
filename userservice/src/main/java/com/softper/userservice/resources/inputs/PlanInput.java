package com.softper.userservice.resources.inputs;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PlanInput {
    private String planName;
    private int durationDays;
    private double price;
}
