package com.violetbeach.banking.adapter.in.web;

import com.violetbeach.banking.application.port.in.GetRegisteredBankAccountCommand;
import com.violetbeach.banking.application.port.in.GetRegisteredBankAccountUseCase;
import com.violetbeach.banking.domain.RegisteredBankAccount;
import com.violetbeach.common.WebAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@WebAdapter(path = "/banking/account")
@RequiredArgsConstructor
public class GetRegisteredBankAccountController {

    private final GetRegisteredBankAccountUseCase getRegisteredBankAccountUseCase;

    @GetMapping(path = "/{membershipId}")
    RegisteredBankAccount getRegisteredBankAccount(@PathVariable String membershipId) {
        GetRegisteredBankAccountCommand command = GetRegisteredBankAccountCommand.builder()
            .membershipId(membershipId).build();
        return getRegisteredBankAccountUseCase.getRegisteredBankAccount(command);
    }

}
