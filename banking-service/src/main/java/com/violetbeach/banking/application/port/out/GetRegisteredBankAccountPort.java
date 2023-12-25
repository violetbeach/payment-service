package com.violetbeach.banking.application.port.out;

import com.violetbeach.banking.application.port.in.GetRegisteredBankAccountCommand;
import com.violetbeach.banking.domain.RegisteredBankAccount;

public interface GetRegisteredBankAccountPort {

    RegisteredBankAccount getRegisteredBankAccount(
        GetRegisteredBankAccountCommand command);
}