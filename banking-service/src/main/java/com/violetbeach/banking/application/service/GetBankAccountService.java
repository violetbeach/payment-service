package com.violetbeach.banking.application.service;

import com.violetbeach.banking.application.port.in.GetRegisteredBankAccountCommand;
import com.violetbeach.banking.application.port.in.GetRegisteredBankAccountUseCase;
import com.violetbeach.banking.application.port.out.GetRegisteredBankAccountPort;
import com.violetbeach.banking.domain.RegisteredBankAccount;
import com.violetbeach.common.UseCase;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class GetBankAccountService implements GetRegisteredBankAccountUseCase {

    private final GetRegisteredBankAccountPort getRegisteredBankAccountPort;

    @Override
    public RegisteredBankAccount getRegisteredBankAccount(GetRegisteredBankAccountCommand command) {
        return getRegisteredBankAccountPort.getRegisteredBankAccount(
            command
        );
    }
}
