package com.violetbeach.banking.application.port.out;

public record BankAccountInfoRequest(
    String bankName,
    String bankAccountNumber
) {
}
