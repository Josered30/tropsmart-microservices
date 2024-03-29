package com.softper.userservice.resources.outputs;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class ServiceOutput {
    private String firstName;
    private String lastName;
    private Date startedTime;
    private Date finishTime;
    private String serviceState;
    private int id;
}
