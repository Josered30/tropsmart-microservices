package com.softper.userservice.resources.outputs;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PaymentMethodOutput {
    private int id;
    private String bankName;
    private int swiftCode;
    private long accountNumber;

    public PaymentMethodOutput(String bankName, int swiftCode, long accountNumber) {
        this.bankName = bankName;
        this.swiftCode = swiftCode;
        this.accountNumber = accountNumber;
    }
}
