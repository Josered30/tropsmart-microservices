package com.softper.userservice.resources.outputs;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class BalanceOutput {
    private String user;
    private String email;
    private double credits;
    private double addedMoney;
    private double spentMoney;

    public BalanceOutput(String user, String email, double credits, double addedMoney, double spentMoney) {
        this.user = user;
        this.email = email;
        this.credits = credits;
        this.addedMoney = addedMoney;
        this.spentMoney = spentMoney;
    }
}
