package com.softper.cargoservice.resources.comunications;

import java.util.List;

import com.softper.cargoservice.resources.outputs.BalanceOutput;
import com.softper.cargoservice.resources.outputs.CustomerOutput;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CustomerBoundResponse extends BaseResponse{
    public CustomerBoundResponse(String title, String message, Integer status) { super(title, message, status);}

    CustomerOutput customerOutput;

    List<CustomerOutput> customerOutputs;

    BalanceOutput balanceOutput;

    List<BalanceOutput> balanceOutputs;
}
