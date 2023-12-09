package com.violetbeach.banking.adapter.in.web;

public record RegisterBankAccountRequest(
    String membershipId,
    String bankName,
    String bankAccountNumber,
    boolean isValid
) {
}
