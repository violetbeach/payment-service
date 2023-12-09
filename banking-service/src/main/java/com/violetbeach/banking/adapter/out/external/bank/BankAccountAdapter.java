package com.violetbeach.banking.adapter.out.external.bank;

import com.violetbeach.banking.application.port.out.BankAccountInfoRequest;
import com.violetbeach.banking.application.port.out.RequestBankAccountInfoPort;
import com.violetbeach.banking.application.port.out.RequestExternalFirmBankingPort;
import com.violetbeach.banking.domain.BankAccount;
import com.violetbeach.banking.domain.ExternalFirmBankingRequest;
import com.violetbeach.banking.domain.FirmBankingResult;
import com.violetbeach.common.ExternalBankSystemAdapter;
import lombok.RequiredArgsConstructor;

@ExternalBankSystemAdapter
@RequiredArgsConstructor
class BankAccountAdapter implements RequestBankAccountInfoPort, RequestExternalFirmBankingPort {
    @Override
    public BankAccount getBankAccountInfo(BankAccountInfoRequest request) {
        return new BankAccount(request.bankName(), request.bankAccountNumber(), true);
    }

    /**
     * 외부 은행의 동작을 Mocking
     * @param request
     * @return
     */
    @Override
    public FirmBankingResult requestExternalFirmBanking(ExternalFirmBankingRequest request) {
        return new FirmBankingResult(0);
    }
}