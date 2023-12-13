package com.violetbeach.money.adapter.in.web;

public record MoneyChangingResultDetail(
    String moneyChangingRequestId,
    int moneyChangingType,
    int moneyChangingResultStatus,
    int amount
) {
}
