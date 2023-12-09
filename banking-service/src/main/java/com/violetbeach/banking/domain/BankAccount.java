package com.violetbeach.banking.domain;

import lombok.Getter;

@Getter
public class BankAccount {
    private String bankName;
    private String bankAccountNumber;
    private boolean isValid;

    public BankAccount(String bankName, String bankAccountNumber, boolean isValid) {
        this.bankName = bankName;
        this.bankAccountNumber = bankAccountNumber;
        this.isValid = isValid;
    }

    public void verifyBank() {
        if(!isValid) {
            throw new IllegalArgumentException("등록되지 않은 계좌 " + bankName + ":" + bankAccountNumber);
        }
    }
}