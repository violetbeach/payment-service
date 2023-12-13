package com.violetbeach.money.adapter.out.persistence;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "money_changing_request")
@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
class MoneyChangingRequestJpaEntity {
    @Id
    @GeneratedValue
    private Long moneyChangingRequestId;
    private String targetMembershipId;
    private int moneyChangingType; // 0: 증액, 1: 감액
    private int moneyAmount;
    private LocalDateTime createdAt;
    private int changingMoneyStatus; // 0: 요청, 1: 성공, 2: 실패
    private String uuid;

    public MoneyChangingRequestJpaEntity(String targetMembershipId, int moneyChangingType, int moneyAmount, LocalDateTime createdAt, int changingMoneyStatus, UUID uuid) {
        this.targetMembershipId = targetMembershipId;
        this.moneyChangingType = moneyChangingType;
        this.moneyAmount = moneyAmount;
        this.createdAt = createdAt;
        this.changingMoneyStatus = changingMoneyStatus;
        this.uuid = uuid.toString();
    }
}