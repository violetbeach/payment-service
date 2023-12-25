package com.violetbeach.banking.domain;

import lombok.Getter;

@Getter
public class ExternalFirmBankingRequest {

    private final String fromBankName;
    private final String fromBankAccountNumber;
    private final String toBankName;
    private final String toBankAccountNumber;
    private int amount;

    public ExternalFirmBankingRequest(String fromBankName, String fromBankAccountNumber,
        String toBankName, String toBankAccountNumber, int amount) {
        this.fromBankName = fromBankName;
        this.fromBankAccountNumber = fromBankAccountNumber;
        this.toBankName = toBankName;
        this.toBankAccountNumber = toBankAccountNumber;
        this.amount = amount;
    }
}