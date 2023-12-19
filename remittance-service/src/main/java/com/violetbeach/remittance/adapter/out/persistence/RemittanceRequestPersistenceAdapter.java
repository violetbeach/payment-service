package com.violetbeach.remittance.adapter.out.persistence;

import com.violetbeach.common.PersistenceAdapter;
import com.violetbeach.remittance.application.port.in.FindRemittanceCommand;
import com.violetbeach.remittance.application.port.in.RequestRemittanceCommand;
import com.violetbeach.remittance.application.port.out.FindRemittancePort;
import com.violetbeach.remittance.application.port.out.RequestRemittancePort;
import com.violetbeach.remittance.domain.RemittanceRequest;
import java.util.List;
import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
class RemittanceRequestPersistenceAdapter implements RequestRemittancePort, FindRemittancePort {
    private final SpringDataRemittanceRequestRepository remittanceRequestRepository;
    private final RemittanceRequestMapper mapper;

    @Override
    public RemittanceRequest createRemittanceRequestHistory(RequestRemittanceCommand command) {
        RemittanceRequestJpaEntity entity = RemittanceRequestJpaEntity.builder()
            .fromMembershipId(command.getFromMembershipId())
            .toMembershipId(command.getToMembershipId())
            .toBankName(command.getToBankName())
            .toBankAccountNumber(command.getToBankAccountNumber())
            .amount(command.getAmount())
            .remittanceType(command.getRemittanceType())
            .build();
        remittanceRequestRepository.save(entity);
        return mapper.mapToDomainEntity(entity);
    }

    @Override
    public boolean saveRemittanceRequestHistory(RemittanceRequest remittanceRequest) {
        RemittanceRequestJpaEntity entity = mapper.mapToJpaEntity(remittanceRequest);
        remittanceRequestRepository.save(entity);
        return true;
    }

    @Override
    public List<RemittanceRequest> findRemittanceHistory(FindRemittanceCommand command) {
        return null;
    }
}
