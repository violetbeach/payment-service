package com.violetbeach.membership.application.service;

import com.violetbeach.membership.application.port.in.FindMembershipCommand;
import com.violetbeach.membership.application.port.in.FindMembershipUseCase;
import com.violetbeach.membership.application.port.out.FindMembershipPort;
import com.violetbeach.membership.domain.Membership;
import com.violetbeach.common.UseCase;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class FindMembershipService implements FindMembershipUseCase {
    private final FindMembershipPort findMembershipPort;

    @Override
    public Membership findMembership(FindMembershipCommand command) {
        return findMembershipPort.findMembership(new Membership.MembershipId(command.getMembershipId()));
    }

}