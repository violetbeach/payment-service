package com.violetbeach.money.application.port.out;

import com.violetbeach.money.domain.MemberMoney;

public interface GetMemberMoneyPort {
    MemberMoney getMemberMoney(
        MemberMoney.MembershipId memberId
    );
}
