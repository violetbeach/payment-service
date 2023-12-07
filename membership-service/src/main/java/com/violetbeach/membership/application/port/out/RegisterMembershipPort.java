package com.violetbeach.membership.application.port.out;

import com.violetbeach.membership.domain.Membership;

public interface RegisterMembershipPort {
    Membership createMembership(
        Membership.MembershipName membershipName
        , Membership.MembershipEmail membershipEmail
        , Membership.MembershipAddress membershipAddress
        , Membership.MembershipIsValid membershipIsValid
        , Membership.MembershipIsCorp membershipIsCorp
    );
}
