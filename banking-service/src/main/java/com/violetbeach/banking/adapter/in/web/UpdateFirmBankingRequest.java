package com.violetbeach.banking.adapter.in.web;

public record UpdateFirmBankingRequest(
    String firmBankingAggregateIdentifier,
    int status
) {

}
