package com.violetbeach.money.application.port.out;

import com.violetbeach.money.domain.MemberMoney;
import com.violetbeach.money.domain.MoneyChangingRequest;

public interface IncreaseMoneyPort {
    MoneyChangingRequest createMoneyChangingRequest(
        MoneyChangingRequest.TargetMembershipId targetMembershipId,
        MoneyChangingRequest.MoneyChangingType moneyChangingType,
        MoneyChangingRequest.ChangingMoneyAmount changingMoneyAmount,
        MoneyChangingRequest.MoneyChangingStatus moneyChangingStatus,
        MoneyChangingRequest.Uuid uuid
    );

    MemberMoney increaseMoney(
        MemberMoney.MembershipId memberId,
        int increaseMoneyAmount
    );
}