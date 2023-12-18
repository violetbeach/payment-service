package com.violetbeach.money.domain;

import lombok.Getter;

@Getter
public class MembershipStatus {
    private final String membershipId;
    private final boolean isValid;

    public MembershipStatus(String membershipId, boolean isValid) {
        this.membershipId = membershipId;
        this.isValid = isValid;
    }
}
