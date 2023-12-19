package com.violetbeach.remittance.adapter.in.web;

public record RequestRemittanceRequest(
    String fromMembershipId,
    String toMembershipId,
    String toBankName,
    String toBankAccountNumber,
    int remittanceType, // 0: membership(내부 고객), 1: bank (외부 은행 계좌)
    int amount
) {
}
