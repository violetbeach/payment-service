package com.violetbeach.membership.application.service;

import com.violetbeach.membership.application.port.in.ModifyMembershipCommand;
import com.violetbeach.membership.application.port.in.ModifyMembershipUseCase;
import com.violetbeach.membership.application.port.out.ModifyMembershipPort;
import com.violetbeach.membership.domain.Membership;
import common.UseCase;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class ModifyMembershipService implements ModifyMembershipUseCase {
    private final ModifyMembershipPort modifyMembershipPort;

    @Override
    public Membership modifyMembership(ModifyMembershipCommand command) {
        return modifyMembershipPort.modifyMembership(
            new Membership.MembershipId(command.getMembershipId()),
            new Membership.MembershipName(command.getName()),
            new Membership.MembershipEmail(command.getEmail()),
            new Membership.MembershipAddress(command.getAddress()),
            new Membership.MembershipIsValid(command.isValid()),
            new Membership.MembershipIsCorp(command.isCorp())
        );
    }
}
