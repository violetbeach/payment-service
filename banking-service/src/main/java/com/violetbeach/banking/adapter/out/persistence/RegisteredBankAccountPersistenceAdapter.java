package com.violetbeach.banking.adapter.out.persistence;

import com.violetbeach.banking.application.port.in.GetRegisteredBankAccountCommand;
import com.violetbeach.banking.application.port.out.GetRegisteredBankAccountPort;
import com.violetbeach.banking.application.port.out.RegisterBankAccountPort;
import com.violetbeach.banking.domain.RegisteredBankAccount;
import com.violetbeach.common.PersistenceAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@PersistenceAdapter
@RequiredArgsConstructor
@Transactional
class RegisteredBankAccountPersistenceAdapter implements RegisterBankAccountPort,
    GetRegisteredBankAccountPort {

    private final SpringDataRegisteredBankAccountRepository bankAccountRepository;
    private final RegisteredBankAccountMapper mapper;

    @Override
    public RegisteredBankAccount createRegisteredBankAccount(
        RegisteredBankAccount.MembershipId membershipId, RegisteredBankAccount.BankName bankName,
        RegisteredBankAccount.BankAccountNumber bankAccountNumber,
        RegisteredBankAccount.LinkedStatusIsValid linkedStatusIsValid,
        RegisteredBankAccount.AggregateIdentifier aggregateIdentifier) {
        RegisteredBankAccountJpaEntity entity = bankAccountRepository.save(
            new RegisteredBankAccountJpaEntity(
                membershipId.getMembershipId(),
                bankName.getBankName(),
                bankAccountNumber.getBankAccountNumber(),
                linkedStatusIsValid.isLinkedStatusIsValid(),
                aggregateIdentifier.getAggregateIdentifier()
            )
        );
        return mapper.mapToDomainEntity(entity);
    }

    @Override
    public RegisteredBankAccount getRegisteredBankAccount(
        GetRegisteredBankAccountCommand command) {
        RegisteredBankAccountJpaEntity entity = bankAccountRepository.getByMembershipId(
            command.getMembershipId());
        return mapper.mapToDomainEntity(entity);
    }
}
