package com.violetbeach.banking.application.port.in;

import com.violetbeach.banking.domain.RegisteredBankAccount;

public interface RegisterBankAccountUseCase {
    RegisteredBankAccount registerBankAccount(RegisterBankAccountCommand command);
}