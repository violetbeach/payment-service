package com.violetbeach.money.adapter.axon.event;

import com.violetbeach.common.SelfValidating;
import com.violetbeach.money.application.port.in.IncreaseMoneyRequestCommand;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Builder
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class IncreaseMoneyEvent extends SelfValidating<IncreaseMoneyRequestCommand>  {
    @NotNull
    private String aggregateIdentifier;
    @NotNull
    private String targetMembershipId;
    @NotNull
    private int amount;

    public IncreaseMoneyEvent(String aggregateIdentifier, String targetMembershipId, int amount) {
        this.aggregateIdentifier = aggregateIdentifier;
        this.targetMembershipId = targetMembershipId;
        this.amount = amount;
    }
}