package com.violetbeach.banking.adapter.out.persistence;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "request_firmbanking")
@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FirmBankingRequestJpaEntity {

    @Id
    @GeneratedValue
    private Long requestFirmBankingId;
    private String fromBankName;
    private String fromBankAccountNumber;
    private String toBankName;
    private String toBankAccountNumber;
    private int moneyAmount; // won
    private int firmBankingStatus; // 0: 요청, 1: 완료, 2: 실패
    private String uuid;
    private String aggregateIdentifier;

    public FirmBankingRequestJpaEntity(String fromBankName, String fromBankAccountNumber,
        String toBankName, String toBankAccountNumber, int moneyAmount, int firmBankingStatus,
        UUID uuid, String aggregateIdentifier) {
        this.fromBankName = fromBankName;
        this.fromBankAccountNumber = fromBankAccountNumber;
        this.toBankName = toBankName;
        this.toBankAccountNumber = toBankAccountNumber;
        this.moneyAmount = moneyAmount;
        this.firmBankingStatus = firmBankingStatus;
        this.uuid = uuid.toString();
        this.aggregateIdentifier = aggregateIdentifier;
    }

}
