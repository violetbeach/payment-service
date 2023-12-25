package com.violetbeach.banking.application.port.out;

import com.violetbeach.banking.domain.RegisteredBankAccount;

public interface RegisterBankAccountPort {

    RegisteredBankAccount createRegisteredBankAccount(
        RegisteredBankAccount.MembershipId membershipId,
        RegisteredBankAccount.BankName bankName,
        RegisteredBankAccount.BankAccountNumber bankAccountNumber,
        RegisteredBankAccount.LinkedStatusIsValid linkedStatusIsValid,
        RegisteredBankAccount.AggregateIdentifier aggregateIdentifier
    );
}