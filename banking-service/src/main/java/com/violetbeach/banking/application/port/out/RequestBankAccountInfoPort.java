package com.violetbeach.banking.application.port.out;

import com.violetbeach.banking.domain.BankAccount;

public interface RequestBankAccountInfoPort {
    BankAccount getBankAccountInfo(BankAccountInfoRequest request) ;
}