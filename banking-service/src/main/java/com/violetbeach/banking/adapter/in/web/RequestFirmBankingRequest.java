package com.violetbeach.banking.adapter.in.web;

public record RequestFirmBankingRequest(
    String fromBankName,
    String fromBankAccountNumber,
    String toBankName,
    String toBankAccountNumber,
    int moneyAmount
) {
}
