package com.violetbeach.banking.application.port.out;

import com.violetbeach.banking.domain.FirmBankingRequest;

public interface RequestFirmBankingPort {
    FirmBankingRequest createFirmBankingRequest(
        FirmBankingRequest.FromBankName fromBankName,
        FirmBankingRequest.FromBankAccountNumber fromBankAccountNumber,
        FirmBankingRequest.ToBankName toBankName,
        FirmBankingRequest.ToBankAccountNumber toBankAccountNumber,
        FirmBankingRequest.MoneyAmount moneyAmount,
        FirmBankingRequest.FirmBankingStatus firmBankingStatus,
        FirmBankingRequest.FirmBankingAggregateIdentifier firmBankingAggregateIdentifier
    );

    FirmBankingRequest modifyFirmBankingRequest(
        FirmBankingRequest entity
    );

    FirmBankingRequest getFirmBankingRequest(
        FirmBankingRequest.FirmBankingAggregateIdentifier firmBankingAggregateIdentifier
    );
}