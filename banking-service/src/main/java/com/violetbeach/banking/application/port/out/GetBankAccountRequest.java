package com.violetbeach.banking.application.port.out;

public record GetBankAccountRequest(
    String bankName,
    String bankAccountNumber
) {

}