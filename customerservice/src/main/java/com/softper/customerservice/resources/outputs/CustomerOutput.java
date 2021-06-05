package com.softper.customerservice.resources.outputs;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CustomerOutput {
    public int id;
    public String firstName;
    public String lastName;
    public double credits;
    public String email;

}
