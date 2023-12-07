package com.violetbeach.membership.adapter.out.persistence;

import com.violetbeach.membership.domain.Membership;
import org.springframework.stereotype.Component;

@Component
class MembershipMapper {
    Membership mapToDomainEntity(MembershipJpaEntity membership) {
        return Membership.generateMember(
            new Membership.MembershipId(membership.getMembershipId() + ""),
            new Membership.MembershipName(membership.getName()),
            new Membership.MembershipEmail(membership.getEmail()),
            new Membership.MembershipAddress(membership.getAddress()),
            new Membership.MembershipIsValid(membership.isValid()),
            new Membership.MembershipIsCorp(membership.isCorp())
        );
    }
}
