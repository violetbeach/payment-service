package com.violetbeach.money.application.port.out;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisteredBankAccountAggregateIdentifier {

    private String registeredBankAccountId;
    private String aggregateIdentifier;
    private String membershipId;
    private String bankName;
    private String bankAccountNumber;
}
