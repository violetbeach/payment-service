package com.violetbeach.banking.application.port.in;

import com.violetbeach.banking.domain.RegisteredBankAccount;

public interface GetRegisteredBankAccountUseCase {

    RegisteredBankAccount getRegisteredBankAccount(GetRegisteredBankAccountCommand command);
}
