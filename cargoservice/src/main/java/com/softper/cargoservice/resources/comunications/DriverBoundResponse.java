package com.softper.cargoservice.resources.comunications;

import java.util.List;

import com.softper.cargoservice.resources.outputs.DriverOutput;
import com.softper.cargoservice.resources.outputs.ReviewOutput;
import com.softper.cargoservice.resources.outputs.VehicleOutput;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DriverBoundResponse extends BaseResponse {
    public DriverBoundResponse(String title, String message, Integer status) {super(title, message, status);}

    DriverOutput driverOutput;

    List<DriverOutput> driverOutputs;

    ReviewOutput reviewOutput;

    List<ReviewOutput> reviewOutputs;

    VehicleOutput vehicleOutput;

    List<VehicleOutput> vehicleOutputs;
}
