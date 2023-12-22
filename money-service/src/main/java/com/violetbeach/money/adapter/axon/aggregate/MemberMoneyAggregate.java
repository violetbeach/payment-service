package com.violetbeach.money.adapter.axon.aggregate;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

import com.violetbeach.money.adapter.axon.command.IncreaseMoneyRequestEventCommand;
import com.violetbeach.money.adapter.axon.command.MemberMoneyCreatedCommand;
import com.violetbeach.money.adapter.axon.event.IncreaseMoneyEvent;
import com.violetbeach.money.adapter.axon.event.MemberMoneyCreatedEvent;
import jakarta.validation.constraints.NotNull;
import java.util.UUID;
import lombok.Data;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate
@Data
public class MemberMoneyAggregate {
    @AggregateIdentifier
    private String id;
    private Long membershipId;
    private int balance;

    @CommandHandler
    public MemberMoneyAggregate(MemberMoneyCreatedCommand command) {
        apply(new MemberMoneyCreatedEvent(command.getTargetMembershipId()));
    }

    @CommandHandler
    public String handle(@NotNull IncreaseMoneyRequestEventCommand command) {
        apply(new IncreaseMoneyEvent(id, command.getTargetMembershipId(), command.getAmount()));
        return id;
    }

    @EventSourcingHandler
    public void on(MemberMoneyCreatedEvent event) {
        id = UUID.randomUUID().toString();
        membershipId = Long.parseLong(event.getTargetMembershipId());
        balance = 0;
    }

    @EventSourcingHandler
    public void on(IncreaseMoneyEvent event) {
        id = event.getAggregateIdentifier();
        membershipId = Long.parseLong(event.getTargetMembershipId());
        balance = event.getAmount();
    }

}
