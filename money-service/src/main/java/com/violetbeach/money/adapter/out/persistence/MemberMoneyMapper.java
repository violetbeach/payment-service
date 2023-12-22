package com.violetbeach.money.adapter.out.persistence;

import com.violetbeach.money.domain.MemberMoney;
import com.violetbeach.money.domain.MemberMoney.MoneyAggregateIdentifier;
import org.springframework.stereotype.Component;

@Component
class MemberMoneyMapper {
    public MemberMoney mapToDomainEntity(MemberMoneyJpaEntity memberMoneyJpaEntity) {
        return MemberMoney.generateMemberMoney(
            new MemberMoney.MemberMoneyId(memberMoneyJpaEntity.getMemberMoneyId()+""),
            new MemberMoney.MembershipId(memberMoneyJpaEntity.getMembershipId()+""),
            new MemberMoney.MoneyBalance(memberMoneyJpaEntity.getBalance()),
            new MoneyAggregateIdentifier(memberMoneyJpaEntity.getAggregateIdentifier())
        );
    }
}