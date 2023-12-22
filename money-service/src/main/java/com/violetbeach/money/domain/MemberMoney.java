package com.violetbeach.money.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Value;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class MemberMoney {
    private final String memberMoneyId;
    private final String membershipId;
    private final int balance;

    public static MemberMoney generateMemberMoney (
        MemberMoneyId memberMoneyId,
        MembershipId membershipId,
        MoneyBalance moneyBalance
    ){
        return new MemberMoney(
            memberMoneyId.memberMoneyId,
            membershipId.membershipId,
            moneyBalance.balance
        );
    }

    @Value
    public static class MemberMoneyId {
        public MemberMoneyId(String value) {
            this.memberMoneyId = value;
        }
        String memberMoneyId ;
    }

    @Value
    public static class MembershipId {
        public MembershipId(String value) {
            this.membershipId = value;
        }
        String membershipId ;
    }

    @Value
    public static class MoneyBalance {
        public MoneyBalance(int value) {
            this.balance = value;
        }
        int balance ;
    }

    @Value
    public static class MoneyAggregateIdentifier {
        public MoneyAggregateIdentifier(String value) {
            this.aggregateIdentifier = value;
        }
        String aggregateIdentifier ;
    }
}