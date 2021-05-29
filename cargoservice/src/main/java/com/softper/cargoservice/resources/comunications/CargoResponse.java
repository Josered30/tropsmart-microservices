package com.softper.cargoservice.resources.comunications;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import com.softper.cargoservice.resources.comunications.BaseResponse;
import com.softper.cargoservice.resources.outputs.CargoOutput;

@Data
@NoArgsConstructor
public class CargoResponse extends BaseResponse<CargoOutput>{
    public CargoResponse(List<CargoOutput> cargoOutputList) {super(cargoOutputList);}
    public CargoResponse(CargoOutput cargoOutput) {super(cargoOutput);}
    public CargoResponse(String message) { super(message);}
}
