package com.violetbeach.money.adapter.axon.event;

import com.violetbeach.common.SelfValidating;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class MemberMoneyCreatedEvent extends SelfValidating<MemberMoneyCreatedEvent> {
    @NotNull
    private String targetMembershipId;

    public MemberMoneyCreatedEvent(String targetMembershipId) {
        this.targetMembershipId = targetMembershipId;

        this.validateSelf();
    }
}