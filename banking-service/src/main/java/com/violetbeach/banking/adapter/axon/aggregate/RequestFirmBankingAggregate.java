package com.violetbeach.banking.adapter.axon.aggregate;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

import com.violetbeach.banking.adapter.axon.command.CreateRequestFirmBankingCommand;
import com.violetbeach.banking.adapter.axon.event.RequestFirmBankingCreatedEvent;
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
public class RequestFirmBankingAggregate {

    @AggregateIdentifier
    private String id;
    private String fromBankName;
    private String fromBankAccountNumber;
    private String toBankName;
    private String toBankAccountNumber;

    private int moneyAmount;
    private int firmBankingStatus;

    @CommandHandler
    public RequestFirmBankingAggregate(@NotNull CreateRequestFirmBankingCommand command) {
        // store event
        apply(new RequestFirmBankingCreatedEvent(command.getFromBankName(),
            command.getFromBankAccountNumber(),
            command.getToBankName(),
            command.getToBankAccountNumber(),
            command.getMoneyAmount()));
    }

    @EventSourcingHandler
    public void on(RequestFirmBankingCreatedEvent event) {
        id = UUID.randomUUID().toString();
        fromBankName = event.getFromBankName();
        fromBankAccountNumber = event.getFromBankAccountNumber();
        toBankName = event.getToBankName();
        toBankAccountNumber = event.getToBankAccountNumber();
    }
}