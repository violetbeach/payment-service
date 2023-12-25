package com.violetbeach.banking.application.service;

import com.violetbeach.banking.adapter.axon.command.CreateRegisteredBankAccountCommand;
import com.violetbeach.banking.application.port.in.RegisterBankAccountCommand;
import com.violetbeach.banking.application.port.in.RegisterBankAccountUseCase;
import com.violetbeach.banking.application.port.out.RegisterBankAccountPort;
import com.violetbeach.banking.domain.RegisteredBankAccount;
import com.violetbeach.common.UseCase;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;

@UseCase
@RequiredArgsConstructor
public class RegisterBankAccountService implements RegisterBankAccountUseCase {

    private final RegisterBankAccountPort registerBankAccountPort;
    private final CommandGateway commandGateway;

    @Override
    public void registerBankAccount(RegisterBankAccountCommand command) {
        commandGateway.send(
            new CreateRegisteredBankAccountCommand(command.getMembershipId(), command.getBankName(),
                command.getBankAccountNumber())).whenComplete(
            (result, throwable) -> {
                if (throwable != null) {
                    throw new RuntimeException(throwable);
                }
                registerBankAccountPort.createRegisteredBankAccount(
                    new RegisteredBankAccount.MembershipId(command.getMembershipId() + ""),
                    new RegisteredBankAccount.BankName(command.getBankName()),
                    new RegisteredBankAccount.BankAccountNumber(command.getBankAccountNumber()),
                    new RegisteredBankAccount.LinkedStatusIsValid(command.isValid()),
                    new RegisteredBankAccount.AggregateIdentifier(result.toString())
                );
            }
        );
    }
}