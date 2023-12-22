package com.violetbeach.money.adapter.out.persistence;

import com.violetbeach.common.PersistenceAdapter;
import com.violetbeach.money.application.port.out.CreateMemberMoneyPort;
import com.violetbeach.money.application.port.out.IncreaseMoneyPort;
import com.violetbeach.money.domain.MemberMoney;
import com.violetbeach.money.domain.MemberMoney.MembershipId;
import com.violetbeach.money.domain.MemberMoney.MoneyAggregateIdentifier;
import com.violetbeach.money.domain.MoneyChangingRequest;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@PersistenceAdapter
@RequiredArgsConstructor
@Transactional
class MoneyChangingRequestPersistenceAdapter implements IncreaseMoneyPort, CreateMemberMoneyPort {
    private final SpringDataMoneyChangingRequestRepository moneyChangingRequestRepository;
    private final SpringDataMemberMoneyRepository memberMoneyRepository;
    private final MoneyChangingRequestMapper moneyChangingRequestMapper;
    private final MemberMoneyMapper memberMoneyMapper;

    @Override
    public MoneyChangingRequest createMoneyChangingRequest(MoneyChangingRequest.TargetMembershipId targetMembershipId, MoneyChangingRequest.MoneyChangingType moneyChangingType, MoneyChangingRequest.ChangingMoneyAmount changingMoneyAmount, MoneyChangingRequest.MoneyChangingStatus moneyChangingStatus, MoneyChangingRequest.Uuid uuid) {
        MoneyChangingRequestJpaEntity entity = moneyChangingRequestRepository.save(
            new MoneyChangingRequestJpaEntity(
                targetMembershipId.getTargetMembershipId(),
                moneyChangingType.getMoneyChangingType(),
                changingMoneyAmount.getChangingMoneyAmount(),
                LocalDateTime.now(),
                moneyChangingStatus.getChangingMoneyStatus(),
                UUID.randomUUID()
            )
        );
        return moneyChangingRequestMapper.mapToDomainEntity(entity);
    }

    @Override
    public MemberMoney increaseMoney(MembershipId memberId, int increaseMoneyAmount) {
        MemberMoneyJpaEntity entity = memberMoneyRepository.getReferenceById(Long.parseLong(memberId.getMembershipId()));
        entity.setBalance(entity.getBalance() + increaseMoneyAmount);
        return memberMoneyMapper.mapToDomainEntity(entity);
    }

    @Override
    public void createMemberMoney(MembershipId memberId, MoneyAggregateIdentifier aggregateIdentifier) {
        MemberMoneyJpaEntity entity = new MemberMoneyJpaEntity(
            Long.parseLong(memberId.getMembershipId()),
            0,
            aggregateIdentifier.getAggregateIdentifier()
        );
        memberMoneyRepository.save(entity);
    }
}