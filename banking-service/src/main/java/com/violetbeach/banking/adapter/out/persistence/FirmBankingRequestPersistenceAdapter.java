package com.violetbeach.banking.adapter.out.persistence;

import com.violetbeach.banking.application.port.out.RequestFirmBankingPort;
import com.violetbeach.banking.domain.FirmBankingRequest;
import com.violetbeach.banking.domain.FirmBankingRequest.FirmBankingAggregateIdentifier;
import com.violetbeach.common.PersistenceAdapter;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@PersistenceAdapter
@RequiredArgsConstructor
@Transactional
class FirmBankingRequestPersistenceAdapter implements RequestFirmBankingPort {

    private final SpringDataFirmBankingRequestRepository firmBankingRequestRepository;
    private final FirmBankingRequestMapper mapper;

    @Override
    public FirmBankingRequest createFirmBankingRequest(FirmBankingRequest.FromBankName fromBankName,
        FirmBankingRequest.FromBankAccountNumber fromBankAccountNumber,
        FirmBankingRequest.ToBankName toBankName,
        FirmBankingRequest.ToBankAccountNumber toBankAccountNumber,
        FirmBankingRequest.MoneyAmount moneyAmount,
        FirmBankingRequest.FirmBankingStatus firmBankingStatus,
        FirmBankingRequest.FirmBankingAggregateIdentifier firmBankingAggregateIdentifier) {
        FirmBankingRequestJpaEntity entity = firmBankingRequestRepository.save(
            new FirmBankingRequestJpaEntity(
                fromBankName.getFromBankName(),
                fromBankAccountNumber.getFromBankAccountNumber(),
                toBankName.getToBankName(),
                toBankAccountNumber.getToBankAccountNumber(),
                moneyAmount.getMoneyAmount(), firmBankingStatus.getFirmBankingStatus(),
                UUID.randomUUID(),
                firmBankingAggregateIdentifier.getAggregateIdentifier()
            )
        );
        return mapper.mapToDomainEntity(entity);
    }

    @Override
    public FirmBankingRequest modifyFirmBankingRequest(FirmBankingRequest firmBankingRequest) {
        FirmBankingRequestJpaEntity entity = mapper.mapToJpaEntity(firmBankingRequest);
        UUID newUuid = UUID.randomUUID();
        entity.setUuid(newUuid.toString());
        firmBankingRequestRepository.save(entity);
        return mapper.mapToDomainEntity(entity);
    }

    @Override
    public FirmBankingRequest getFirmBankingRequest(FirmBankingAggregateIdentifier identifier) {
        FirmBankingRequestJpaEntity entity = firmBankingRequestRepository.getByAggregateIdentifier(
            identifier.getAggregateIdentifier());

        return mapper.mapToDomainEntity(entity);
    }
}
