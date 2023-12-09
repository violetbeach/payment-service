package com.violetbeach.banking.adapter.out.external.bank;

import com.violetbeach.banking.application.port.out.BankAccountInfoRequest;
import com.violetbeach.banking.application.port.out.RequestBankAccountInfoPort;
import com.violetbeach.banking.domain.BankAccount;
import com.violetbeach.common.ExternalBankSystemAdapter;
import lombok.RequiredArgsConstructor;

@ExternalBankSystemAdapter
@RequiredArgsConstructor
class BankAccountAdapter implements RequestBankAccountInfoPort {
    @Override
    public BankAccount getBankAccountInfo(BankAccountInfoRequest request) {
        return new BankAccount(request.bankName(), request.bankAccountNumber(), true);
    }
}