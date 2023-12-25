package com.violetbeach.banking.adapter.in.web;

import com.violetbeach.banking.application.port.in.RegisterBankAccountCommand;
import com.violetbeach.banking.application.port.in.RegisterBankAccountUseCase;
import com.violetbeach.common.WebAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@WebAdapter(path = "/banking/account")
@RequiredArgsConstructor
public class RegisterBankAccountController {

    private final RegisterBankAccountUseCase registeredBankAccountUseCase;

    @PostMapping
    void registerBankAccount(@RequestBody RegisterBankAccountRequest request) {
        RegisterBankAccountCommand command = RegisterBankAccountCommand.builder()
            .membershipId(request.membershipId())
            .bankName(request.bankName())
            .bankAccountNumber(request.bankAccountNumber())
            .isValid(request.isValid())
            .build();

        registeredBankAccountUseCase.registerBankAccount(command);
    }
}
