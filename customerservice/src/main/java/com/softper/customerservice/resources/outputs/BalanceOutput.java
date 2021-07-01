package com.softper.customerservice.resources.outputs;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class BalanceOutput {
    private int id;
    private double addedMoney;
    private double spentMoney;
}
