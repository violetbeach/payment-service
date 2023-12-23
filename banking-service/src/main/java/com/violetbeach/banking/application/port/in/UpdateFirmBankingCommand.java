package com.violetbeach.banking.application.port.in;

import com.violetbeach.common.SelfValidating;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Builder
@Data
@EqualsAndHashCode(callSuper = false)
public class UpdateFirmBankingCommand extends SelfValidating<UpdateFirmBankingCommand> {

    @NotNull
    private final String firmBankingAggregateIdentifier;
    private final int firmBankingStatus;

    public UpdateFirmBankingCommand(String firmBankingAggregateIdentifier, int firmBankingStatus) {
        this.firmBankingAggregateIdentifier = firmBankingAggregateIdentifier;
        this.firmBankingStatus = firmBankingStatus;
        
        this.validateSelf();
    }
}