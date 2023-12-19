package com.violetbeach.remittance.application.port.out.membership;

public record MembershipStatus(
    String membershipId,
    boolean isValid
) {
}
