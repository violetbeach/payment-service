package com.violetbeach.membership.application.port.in;

import com.violetbeach.membership.domain.Membership;

public interface RegisterMembershipUseCase {
    Membership registerMembership(RegisterMembershipCommand command);
}
