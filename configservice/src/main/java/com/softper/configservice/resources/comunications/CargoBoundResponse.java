package com.softper.configservice.resources.comunications;

import java.util.List;

import com.softper.configservice.resources.outputs.CargoOutput;
import com.softper.configservice.resources.outputs.PriceOutput;
import com.softper.configservice.resources.outputs.RouteOutput;
import com.softper.configservice.resources.outputs.ServiceOutput;

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
