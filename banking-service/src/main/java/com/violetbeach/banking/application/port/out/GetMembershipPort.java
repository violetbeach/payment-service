package com.violetbeach.banking.application.port.out;

import com.violetbeach.banking.domain.MembershipStatus;

public interface GetMembershipPort {
    MembershipStatus getMembership(String membershipId);
}