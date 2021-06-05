package com.softper.userservice.resources.comunications;

import java.util.List;

import com.softper.userservice.resources.outputs.CargoOutput;
import com.softper.userservice.resources.outputs.PriceOutput;
import com.softper.userservice.resources.outputs.RouteOutput;
import com.softper.userservice.resources.outputs.ServiceOutput;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CargoBoundResponse extends BaseResponse {
    public CargoBoundResponse(String title, String message, Integer status) { super(title, message, status);}

    CargoOutput cargoOutput;

    List<CargoOutput> cargoOutputs;
    
    PriceOutput priceOutput;

    List<PriceOutput> priceOutputs;

    RouteOutput routeOutput;

    List<RouteOutput> routeOutputs;

    ServiceOutput ServiceOutput;

    List<ServiceOutput> serviceOutputs;
}
