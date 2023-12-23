package com.violetbeach.banking.adapter.axon.command;

import com.violetbeach.common.SelfValidating;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Builder
@Data
@EqualsAndHashCode(callSuper = false)
public class CreateRequestFirmBankingCommand  extends SelfValidating<CreateRequestFirmBankingCommand>  {
    private String fromBankName;

    private String fromBankAccountNumber;

    private String toBankName;

    private String toBankAccountNumber;

    private int moneyAmount;

    public CreateRequestFirmBankingCommand(String fromBankName, String fromBankAccountNumber,
        String toBankName, String toBankAccountNumber, int moneyAmount) {
        this.fromBankName = fromBankName;
        this.fromBankAccountNumber = fromBankAccountNumber;
        this.toBankName = toBankName;
        this.toBankAccountNumber = toBankAccountNumber;
        this.moneyAmount = moneyAmount;
        this.validateSelf();
    }
}