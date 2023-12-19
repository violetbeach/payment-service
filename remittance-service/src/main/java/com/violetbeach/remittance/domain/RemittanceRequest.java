package com.violetbeach.remittance.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Value;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class RemittanceRequest {
    private final String remittanceRequestId;
    private final String remittanceFromMembershipId;
    private final String toBankName;
    private final String toBankAccountNumber;
    private int remittanceType; // 0: membership(내부 고객), 1: bank (외부 은행 계좌)
    private int amount;
    private String remittanceStatus;

    public void setSuccess() {
        this.remittanceStatus = "success";
    }

    @Value
    public static class RemittanceRequestId {
        public RemittanceRequestId(String value) {
            this.remittanceRequestId = value;
        }
        String remittanceRequestId ;
    }

    @Value
    public static class RemittanceFromMembershipId {
        public RemittanceFromMembershipId(String value) {
            this.remittanceFromMembershipId = value;
        }
        String remittanceFromMembershipId ;
    }

    @Value
    public static class ToBankName {
        public ToBankName(String value) {
            this.toBankName = value;
        }
        String toBankName ;
    }

    @Value
    public static class ToBankAccountNumber {
        public ToBankAccountNumber(String value) {
            this.toBankAccountNumber = value;
        }
        String toBankAccountNumber ;
    }

    @Value
    public static class RemittanceType {
        public RemittanceType(int value) {
            this.remittanceType = value;
        }
        int remittanceType ;
    }

    @Value
    public static class Amount {
        public Amount(int value) {
            this.amount = value;
        }
        int amount ;
    }

    @Value
    public static class RemittanceStatus {
        public RemittanceStatus(String value) {
            this.remittanceStatus = value;
        }
        String remittanceStatus;
    }

    public static RemittanceRequest generateRemittanceRequest(
            RemittanceRequest.RemittanceRequestId remittanceRequestId,
            RemittanceRequest.RemittanceFromMembershipId remittanceFromMembershipId,
            RemittanceRequest.ToBankName toBankName,
            RemittanceRequest.ToBankAccountNumber toBankAccountNumber,
            RemittanceRequest.RemittanceType remittanceType,
            RemittanceRequest.Amount amount,
            RemittanceRequest.RemittanceStatus remittanceRequestStatus
    ){
        return new RemittanceRequest(
            remittanceRequestId.remittanceRequestId,
            remittanceFromMembershipId.remittanceFromMembershipId,
            toBankName.toBankName,
            toBankAccountNumber.toBankAccountNumber,
            remittanceType.remittanceType,
            amount.amount, remittanceRequestStatus.remittanceStatus
        );
    }
}
