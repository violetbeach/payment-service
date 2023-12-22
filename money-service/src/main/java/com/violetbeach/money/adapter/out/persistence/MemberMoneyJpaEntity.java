package com.violetbeach.money.adapter.out.persistence;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "member_money")
@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
class MemberMoneyJpaEntity {
    @Id
    @GeneratedValue
    private Long memberMoneyId;
    private Long membershipId;
    private int balance;
    private String aggregateIdentifier;

    public MemberMoneyJpaEntity(Long membershipId, int balance, String aggregateIdentifier) {
        this.membershipId = membershipId;
        this.balance = balance;
        this.aggregateIdentifier = aggregateIdentifier;
    }
}