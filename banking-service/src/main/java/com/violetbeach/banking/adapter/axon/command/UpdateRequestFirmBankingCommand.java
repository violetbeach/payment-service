package com.violetbeach.banking.adapter.axon.command;

import com.violetbeach.common.SelfValidating;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Builder
@Data
@EqualsAndHashCode(callSuper = false)
public class UpdateRequestFirmBankingCommand extends SelfValidating<UpdateRequestFirmBankingCommand> {
    @NotNull
    @TargetAggregateIdentifier
    private String aggregateIdentifier;
    private final int firmBankingStatus;

    public UpdateRequestFirmBankingCommand(String aggregateIdentifier, int firmBankingStatus) {
        this.aggregateIdentifier = aggregateIdentifier;
        this.firmBankingStatus = firmBankingStatus;

        this.validateSelf();
    }
}