package com.violetbeach.banking.adapter.axon.command;

import com.violetbeach.common.SelfValidating;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Builder
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class CreateRegisteredBankAccountCommand extends
    SelfValidating<CreateRegisteredBankAccountCommand> {

    private String membershipId;
    private String bankName;
    private String bankAccountNumber;

    public CreateRegisteredBankAccountCommand(String membershipId, String bankName,
        String bankAccountNumber) {
        this.membershipId = membershipId;
        this.bankName = bankName;
        this.bankAccountNumber = bankAccountNumber;

        this.validateSelf();
    }
}