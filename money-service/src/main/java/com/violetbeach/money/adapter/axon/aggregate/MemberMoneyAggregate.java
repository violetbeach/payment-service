package com.violetbeach.money.adapter.axon.aggregate;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

import com.violetbeach.money.adapter.axon.command.MemberMoneyCreatedCommand;
import com.violetbeach.money.adapter.axon.event.MemberMoneyCreatedEvent;
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

    @EventSourcingHandler
    public void on(MemberMoneyCreatedEvent event) {
        id = UUID.randomUUID().toString();
        membershipId = Long.parseLong(event.getTargetMembershipId());
        balance = 0;
    }

}
