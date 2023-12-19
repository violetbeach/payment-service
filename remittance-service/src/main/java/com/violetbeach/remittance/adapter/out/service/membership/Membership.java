package com.violetbeach.remittance.adapter.out.service.membership;

public record Membership(
    String membershipId,
    String name,
    String email,
    String address,
    boolean isValid,
    boolean isCorp
) {
}
