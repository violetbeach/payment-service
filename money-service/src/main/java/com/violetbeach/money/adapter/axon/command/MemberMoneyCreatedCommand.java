package com.violetbeach.money.adapter.axon.command;

import com.violetbeach.common.SelfValidating;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class MemberMoneyCreatedCommand extends SelfValidating<MemberMoneyCreatedCommand> {
    @NotNull
    private final String targetMembershipId;

    public MemberMoneyCreatedCommand(String targetMembershipId) {
        this.targetMembershipId = targetMembershipId;

        this.validateSelf();
    }
}