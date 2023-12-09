package com.violetbeach.banking.adapter.out.persistence;

import com.violetbeach.banking.domain.FirmBankingRequest;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
class FirmBankingRequestMapper {
    public FirmBankingRequest mapToDomainEntity(FirmBankingRequestJpaEntity registeredBankAccountJpaEntity) {
        return FirmBankingRequest.generateFirmBankingRequest(
            new FirmBankingRequest.FirmBankingRequestId(registeredBankAccountJpaEntity.getRequestFirmBankingId()+""),
            new FirmBankingRequest.FromBankName(registeredBankAccountJpaEntity.getFromBankName()),
            new FirmBankingRequest.FromBankAccountNumber(registeredBankAccountJpaEntity.getFromBankAccountNumber()),
            new FirmBankingRequest.ToBankName(registeredBankAccountJpaEntity.getToBankName()),
            new FirmBankingRequest.ToBankAccountNumber(registeredBankAccountJpaEntity.getToBankAccountNumber()),
            new FirmBankingRequest.MoneyAmount(registeredBankAccountJpaEntity.getMoneyAmount()),
            new FirmBankingRequest.FirmBankingStatus(registeredBankAccountJpaEntity.getFirmBankingStatus()),
            UUID.fromString(registeredBankAccountJpaEntity.getUuid())
        );
    }

    public FirmBankingRequestJpaEntity mapToJpaEntity(FirmBankingRequest firmBankingRequest) {
        return new FirmBankingRequestJpaEntity(
            firmBankingRequest.getFromBankName(),
            firmBankingRequest.getFromBankAccountNumber(),
            firmBankingRequest.getToBankName(),
            firmBankingRequest.getToBankAccountNumber(),
            firmBankingRequest.getMoneyAmount(),
            firmBankingRequest.getFirmBankingStatus(),
            firmBankingRequest.getUuid()
        );
    }
}