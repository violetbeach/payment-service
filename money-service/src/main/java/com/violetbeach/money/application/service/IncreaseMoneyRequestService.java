package com.violetbeach.money.application.service;

import com.violetbeach.common.UseCase;
import com.violetbeach.money.adapter.axon.command.RechargingMoneyRequestCreateCommand;
import com.violetbeach.money.application.port.in.IncreaseMoneyRequestCommand;
import com.violetbeach.money.application.port.in.IncreaseMoneyRequestUseCase;
import com.violetbeach.money.application.port.out.GetMemberMoneyPort;
import com.violetbeach.money.domain.MemberMoney;
import com.violetbeach.money.domain.MemberMoney.MembershipId;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;

@UseCase
@RequiredArgsConstructor
public class IncreaseMoneyRequestService implements IncreaseMoneyRequestUseCase {

    private final GetMemberMoneyPort getMemberMoneyPort;
    private final CommandGateway commandGateway;

    @Override
    public void increaseMoneyRequest(IncreaseMoneyRequestCommand command) {
        MemberMoney memberMoney = getMemberMoneyPort.getMemberMoney(
            new MembershipId(command.getTargetMembershipId()));
        String moneyIdentifier = memberMoney.getAggregateIdentifier();

        RechargingMoneyRequestCreateCommand rechargingMoneyRequestCreateCommand = RechargingMoneyRequestCreateCommand.builder()
            .aggregateIdentifier(moneyIdentifier)
            .rechargingRequestId(UUID.randomUUID().toString())
            .membershipId(command.getTargetMembershipId())
            .amount(command.getAmount())
            .build();

        commandGateway.send(rechargingMoneyRequestCreateCommand)
            .whenComplete((Object result, Throwable throwable) -> {
                if (throwable != null) {
                    throw new RuntimeException(throwable);
                }
            });
    }
}