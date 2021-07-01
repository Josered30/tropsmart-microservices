package com.softper.customerservice.resources.outputs;

import com.softper.customerservice.models.Customer;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CustomerOutput {
    public int id;
    public double credits;
}
