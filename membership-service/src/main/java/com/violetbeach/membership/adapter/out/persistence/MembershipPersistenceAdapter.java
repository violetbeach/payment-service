package com.violetbeach.membership.adapter.out.persistence;

import com.violetbeach.membership.application.port.out.FindMembershipPort;
import com.violetbeach.membership.application.port.out.ModifyMembershipPort;
import com.violetbeach.membership.application.port.out.RegisterMembershipPort;
import com.violetbeach.membership.domain.Membership;
import com.violetbeach.membership.domain.Membership.MembershipAddress;
import com.violetbeach.membership.domain.Membership.MembershipEmail;
import com.violetbeach.membership.domain.Membership.MembershipId;
import com.violetbeach.membership.domain.Membership.MembershipIsCorp;
import com.violetbeach.membership.domain.Membership.MembershipIsValid;
import com.violetbeach.membership.domain.Membership.MembershipName;
import common.PersistenceAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@PersistenceAdapter
@RequiredArgsConstructor
@Transactional
class MembershipPersistenceAdapter implements RegisterMembershipPort, FindMembershipPort,
    ModifyMembershipPort {
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

    @Override
    public Membership findMembership(Membership.MembershipId membershipId) {
        return membershipMapper.mapToDomainEntity(
            membershipRepository.getReferenceById(Long.parseLong(membershipId.getMembershipId()))
        );
    }

    @Override
    public Membership modifyMembership(MembershipId membershipId, MembershipName membershipName,
        MembershipEmail membershipEmail, MembershipAddress membershipAddress,
        MembershipIsValid membershipIsValid, MembershipIsCorp membershipIsCorp) {
        MembershipJpaEntity entity = membershipRepository.getReferenceById(Long.parseLong(membershipId.getMembershipId()));
        entity.setName(membershipName.getNameValue());
        entity.setEmail(membershipEmail.getEmailValue());
        entity.setAddress(membershipAddress.getAddressValue());
        entity.setValid(membershipIsValid.isValidValue());
        entity.setValid(membershipIsValid.isValidValue());

        return membershipMapper.mapToDomainEntity(entity);
    }
}
