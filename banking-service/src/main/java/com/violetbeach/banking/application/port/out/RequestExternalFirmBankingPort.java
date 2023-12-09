package com.violetbeach.banking.application.port.out;

import com.violetbeach.banking.domain.ExternalFirmBankingRequest;
import com.violetbeach.banking.domain.FirmBankingResult;

public interface RequestExternalFirmBankingPort {
    FirmBankingResult requestExternalFirmBanking(ExternalFirmBankingRequest request);
}