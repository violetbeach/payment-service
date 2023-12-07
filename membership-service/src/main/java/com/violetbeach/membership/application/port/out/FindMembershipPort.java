package com.violetbeach.membership.application.port.out;

import com.violetbeach.membership.domain.Membership;

public interface FindMembershipPort {
    Membership findMembership(
        Membership.MembershipId membershipId
    );
}
