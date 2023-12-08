package com.violetbeach.membership.application.port.in;

import com.violetbeach.common.SelfValidating;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@EqualsAndHashCode(callSuper = false)
public class ModifyMembershipCommand extends SelfValidating<ModifyMembershipCommand> {
    @NotNull
    private final String membershipId;
    @NotNull
    @NotBlank
    private final String name;
    @NotNull
    private final String email;
    @NotNull
    private final String address;
    @NotNull
    private final boolean isCorp;
    @AssertTrue
    private final boolean isValid;

    public ModifyMembershipCommand(String membershipId, String name, String email, String address, boolean isCorp,
        boolean isValid) {
        this.membershipId = membershipId;
        this.name = name;
        this.email = email;
        this.address = address;
        this.isCorp = isCorp;
        this.isValid = isValid;

        this.validateSelf();
    }
}
