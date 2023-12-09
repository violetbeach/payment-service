package com.violetbeach.banking.adapter.out.persistence;

import com.violetbeach.banking.application.port.out.RegisterBankAccountPort;
import com.violetbeach.banking.domain.RegisteredBankAccount;
import com.violetbeach.common.PersistenceAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@PersistenceAdapter
@RequiredArgsConstructor
@Transactional
class RegisteredBankAccountPersistenceAdapter implements RegisterBankAccountPort {
    private final SpringDataRegisteredBankAccountRepository bankAccountRepository;
    private final RegisteredBankAccountMapper mapper;

    @Override
    public RegisteredBankAccount createRegisteredBankAccount(RegisteredBankAccount.MembershipId membershipId, RegisteredBankAccount.BankName bankName, RegisteredBankAccount.BankAccountNumber bankAccountNumber, RegisteredBankAccount.LinkedStatusIsValid linkedStatusIsValid) {
        RegisteredBankAccountJpaEntity entity = bankAccountRepository.save(
            new RegisteredBankAccountJpaEntity(
                membershipId.getMembershipId(),
                bankName.getBankName(),
                bankAccountNumber.getBankAccountNumber(),
                linkedStatusIsValid.isLinkedStatusIsValid()
            )
        );
        return mapper.mapToDomainEntity(entity);
    }
}
