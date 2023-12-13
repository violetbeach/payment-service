package com.violetbeach.money.adapter.in.web;

public record IncreaseMoneyChangingRequest(
    String targetMembershipId,
    int amount
) {
}