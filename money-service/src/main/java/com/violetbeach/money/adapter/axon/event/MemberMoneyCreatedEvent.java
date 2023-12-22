package com.violetbeach.money.adapter.axon.event;

import com.violetbeach.common.SelfValidating;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Builder
@Data
@EqualsAndHashCode(callSuper = false)
public class MemberMoneyCreatedEvent extends SelfValidating<MemberMoneyCreatedEvent> {
    @NotNull
    private final String targetMembershipId;

    public MemberMoneyCreatedEvent(String targetMembershipId) {
        this.targetMembershipId = targetMembershipId;

        this.validateSelf();
    }
}