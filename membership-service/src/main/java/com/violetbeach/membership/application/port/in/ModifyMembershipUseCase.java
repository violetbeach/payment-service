package com.violetbeach.membership.application.port.in;

import com.violetbeach.membership.domain.Membership;

public interface ModifyMembershipUseCase {
    Membership modifyMembership(ModifyMembershipCommand command);
}
