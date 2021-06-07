package com.softper.customerservice.resources.comunications;

import com.softper.customerservice.resources.comunications.BaseResponse;
import com.softper.customerservice.resources.outputs.BalanceOutput;
import com.softper.customerservice.resources.outputs.CustomerOutput;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class CustomerBoundResponse extends BaseResponse{
    public CustomerBoundResponse(String title, String message, Integer status) { super(title, message, status);}

    CustomerOutput customerOutput;

    List<CustomerOutput> customerOutputs;

}
