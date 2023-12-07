package com.violetbeach.membership.adapter.out.persistence;

import com.violetbeach.membership.application.port.out.RegisterMembershipPort;
import com.violetbeach.membership.domain.Membership;
import com.violetbeach.membership.domain.Membership.MembershipAddress;
import com.violetbeach.membership.domain.Membership.MembershipEmail;
import com.violetbeach.membership.domain.Membership.MembershipIsCorp;
import com.violetbeach.membership.domain.Membership.MembershipIsValid;
import com.violetbeach.membership.domain.Membership.MembershipName;
import common.PersistenceAdapter;
import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
class MembershipPersistenceAdapter implements RegisterMembershipPort {
    private final SpringDataMembershipRepository membershipRepository;
    private final MembershipMapper membershipMapper;

    @Override
    public Membership createMembership(MembershipName membershipName, MembershipEmail membershipEmail,
        MembershipAddress membershipAddress, MembershipIsValid membershipIsValid,
        MembershipIsCorp membershipIsCorp) {
        MembershipJpaEntity entity = membershipRepository.save(
            new MembershipJpaEntity(
                membershipName.getNameValue(),
                membershipEmail.getEmailValue(),
                membershipAddress.getAddressValue(),
                membershipIsValid.isValidValue(),
                membershipIsCorp.isCorpValue()
            )
        );
        return membershipMapper.mapToDomainEntity(entity);
    }
}
