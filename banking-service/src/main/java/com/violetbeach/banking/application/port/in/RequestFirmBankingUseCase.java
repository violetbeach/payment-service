package com.violetbeach.banking.application.port.in;

import com.violetbeach.banking.domain.FirmBankingRequest;

public interface RequestFirmBankingUseCase {
    FirmBankingRequest requestFirmBanking(RequestFirmBankingCommand command);
}