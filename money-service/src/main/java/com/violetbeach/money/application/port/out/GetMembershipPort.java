package com.violetbeach.money.application.port.out;

import com.violetbeach.money.domain.MembershipStatus;

public interface GetMembershipPort {
    MembershipStatus getMembership(String membershipId);
}