package com.violetbeach.money.application.service;

import com.violetbeach.common.UseCase;
import com.violetbeach.money.adapter.axon.command.MemberMoneyCreatedCommand;
import com.violetbeach.money.application.port.in.CreateMemberMoneyRequestCommand;
import com.violetbeach.money.application.port.in.CreateMemberMoneyRequestUseCase;
import com.violetbeach.money.application.port.out.CreateMemberMoneyPort;
import com.violetbeach.money.domain.MemberMoney.MembershipId;
import com.violetbeach.money.domain.MemberMoney.MoneyAggregateIdentifier;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;

@UseCase
@RequiredArgsConstructor
public class CreateMoneyRequestService implements CreateMemberMoneyRequestUseCase {
    private final CreateMemberMoneyPort createMemberMoneyPort;
    private final CommandGateway commandGateway;

    @Override
    public void createMoney(CreateMemberMoneyRequestCommand command) {
        MemberMoneyCreatedCommand axonCommand = new MemberMoneyCreatedCommand(command.getTargetMembershipId());
        commandGateway.send(axonCommand).whenComplete((result, throwable) -> {
            if(throwable != null) {
                throw new RuntimeException(throwable);
            } else {
                createMemberMoneyPort.createMemberMoney(
                    new MembershipId(command.getTargetMembershipId()),
                    new MoneyAggregateIdentifier(result.toString())
                );
            }
        });
    }
}