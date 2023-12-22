package com.violetbeach.money.adapter.axon.command;

import com.violetbeach.common.SelfValidating;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Builder
@Data
@EqualsAndHashCode(callSuper = false)
public class IncreaseMoneyRequestEventCommand extends SelfValidating<IncreaseMoneyRequestEventCommand> {
    @NotNull
    @TargetAggregateIdentifier
    private String aggregateIdentifier;
    @NotNull
    private final String targetMembershipId;
    private final int amount;

    public IncreaseMoneyRequestEventCommand(String aggregateIdentifier, String targetMembershipId, int amount) {
        this.aggregateIdentifier = aggregateIdentifier;
        this.targetMembershipId = targetMembershipId;
        this.amount = amount;

        this.validateSelf();
    }
}