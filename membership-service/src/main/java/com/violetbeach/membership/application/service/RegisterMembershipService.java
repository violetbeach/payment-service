package com.violetbeach.membership.application.service;

import com.violetbeach.common.UseCase;
import com.violetbeach.membership.application.port.in.RegisterMembershipCommand;
import com.violetbeach.membership.application.port.in.RegisterMembershipUseCase;
import com.violetbeach.membership.application.port.out.RegisterMembershipPort;
import com.violetbeach.membership.domain.Membership;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class RegisterMembershipService implements RegisterMembershipUseCase {

    private final RegisterMembershipPort registerMembershipPort;

    @Override
    public Membership registerMembership(RegisterMembershipCommand command) {
        return registerMembershipPort.createMembership(
            new Membership.MembershipName(command.getName()),
            new Membership.MembershipEmail(command.getEmail()),
            new Membership.MembershipAddress(command.getAddress()),
            new Membership.MembershipIsValid(command.isValid()),
            new Membership.MembershipIsCorp(command.isCorp())
        );
    }
}
