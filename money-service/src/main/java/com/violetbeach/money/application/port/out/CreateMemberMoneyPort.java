package com.violetbeach.money.application.port.out;

import com.violetbeach.money.domain.MemberMoney;

public interface CreateMemberMoneyPort {
    void createMemberMoney(
        MemberMoney.MembershipId memberId,
        MemberMoney.MoneyAggregateIdentifier aggregateIdentifier
    );
}