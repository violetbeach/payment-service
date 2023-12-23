package com.violetbeach.banking.adapter.axon.event;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdateRequestFirmBankingEvent {
    @NotNull
    private String aggregateIdentifier;
    private int firmBankingStatus;
}