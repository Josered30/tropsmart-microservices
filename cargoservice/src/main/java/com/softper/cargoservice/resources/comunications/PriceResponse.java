package com.softper.cargoservice.resources.comunications;

import com.softper.cargoservice.resources.outputs.PriceOutput;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class PriceResponse extends BaseResponse<PriceOutput>{
    public PriceResponse(List<PriceOutput> priceOutputList) {super(priceOutputList);}
    public PriceResponse(PriceOutput priceOutput) {super(priceOutput);}
    public PriceResponse(String message){super(message);}
}
