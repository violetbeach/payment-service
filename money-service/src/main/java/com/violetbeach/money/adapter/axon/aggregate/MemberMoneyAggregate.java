package com.violetbeach.money.adapter.axon.aggregate;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

import com.violetbeach.money.adapter.axon.command.CreateMemberMoneyCommand;
import com.violetbeach.money.adapter.axon.command.RechargingMoneyRequestCreateCommand;
import com.violetbeach.money.adapter.axon.event.MemberMoneyCreatedEvent;
import com.violetbeach.money.adapter.axon.event.RechargingRequestCreatedEvent;
import com.violetbeach.money.application.port.out.GetRegisteredBankAccountPort;
import com.violetbeach.money.application.port.out.RegisteredBankAccountAggregateIdentifier;
import jakarta.validation.constraints.NotNull;
import java.util.UUID;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate
@Data
@NoArgsConstructor
public class MemberMoneyAggregate {

    @AggregateIdentifier
    private String id;
    private Long membershipId;
    private int balance;

    @CommandHandler
    public MemberMoneyAggregate(@NotNull CreateMemberMoneyCommand command) {
        apply(new MemberMoneyCreatedEvent(command.getTargetMembershipId()));
    }

    @CommandHandler
    public void handle(RechargingMoneyRequestCreateCommand command,
        GetRegisteredBankAccountPort getRegisteredBankAccountPort) {
        id = command.getAggregateIdentifier();

        RegisteredBankAccountAggregateIdentifier bankAccountAggregate = getRegisteredBankAccountPort.getRegisteredBankAccount(
            command.getMembershipId());
        apply(new RechargingRequestCreatedEvent(
            command.getRechargingRequestId()
            , command.getMembershipId()
            , command.getAmount()
            , bankAccountAggregate.getAggregateIdentifier()
            , bankAccountAggregate.getBankName()
            , bankAccountAggregate.getBankAccountNumber()
        ));
    }

    @EventSourcingHandler
    public void on(MemberMoneyCreatedEvent event) {
        id = UUID.randomUUID().toString();
        membershipId = Long.parseLong(event.getTargetMembershipId());
        balance = 0;
    }

}
