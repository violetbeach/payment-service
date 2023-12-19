package com.violetbeach.remittance.application.port.out.money;

public record MoneyInfo(
    String membershipId,
    int balance
) {
}