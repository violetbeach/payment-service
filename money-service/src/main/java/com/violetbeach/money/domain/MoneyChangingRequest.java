package com.violetbeach.money.domain;

import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Value;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class MoneyChangingRequest {
    private final String moneyChangingRequestId;
    private final String targetMembershipId;
    private final int changingType; // enum. 0: 증액, 1: 감액
    private final int changingMoneyAmount;
    private final int changingMoneyStatus; // enum. 0: 요청, 1: 성공, 2: 실패
    private final String uuid;
    private final LocalDateTime createdAt;

    public static MoneyChangingRequest generateMoneyChangingRequest (
        MoneyChangingRequestId moneyChangingRequestId,
        TargetMembershipId targetMembershipId,
        MoneyChangingType moneyChangingType,
        ChangingMoneyAmount changingMoneyAmount,
        MoneyChangingStatus moneyChangingStatus,
        String uuid
    ){
        return new MoneyChangingRequest(
            moneyChangingRequestId.getMoneyChangingRequestId(),
            targetMembershipId.getTargetMembershipId(),
            moneyChangingType.getMoneyChangingType(),
            changingMoneyAmount.getChangingMoneyAmount(),
            moneyChangingStatus.getChangingMoneyStatus(),
            uuid,
            LocalDateTime.now()
        );
    }

    @Value
    public static class MoneyChangingRequestId {
        public MoneyChangingRequestId(String value) {
            this.moneyChangingRequestId = value;
        }
        String moneyChangingRequestId ;
    }

    @Value
    public static class TargetMembershipId {
        public TargetMembershipId(String value) {
            this.targetMembershipId = value;
        }
        String targetMembershipId ;
    }

    @Value
    public static class MoneyChangingType {
        public MoneyChangingType(int value) {
            this.moneyChangingType = value;
        }
        int moneyChangingType ;
    }

    @Value
    public static class ChangingMoneyAmount {
        public ChangingMoneyAmount(int value) {
            this.changingMoneyAmount = value;
        }
        int changingMoneyAmount ;
    }

    @Value
    public static class MoneyChangingStatus {
        public MoneyChangingStatus(int value) {
            this.changingMoneyStatus = value;
        }
        int changingMoneyStatus ;
    }

    @Value
    public static class Uuid {
        public Uuid(String uuid) {
            this.uuid = uuid;
        }
        String uuid ;
    }
}