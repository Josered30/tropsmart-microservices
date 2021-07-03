package com.softper.configservice.resources.comunications;

import java.util.List;

import com.softper.configservice.resources.outputs.BalanceOutput;
import com.softper.configservice.resources.outputs.CustomerOutput;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CustomerBoundResponse extends BaseResponse{
    public CustomerBoundResponse(String title, String message, Integer status) { super(title, message, status);}

    CustomerOutput customerOutput;

    List<CustomerOutput> customerOutputs;

}
