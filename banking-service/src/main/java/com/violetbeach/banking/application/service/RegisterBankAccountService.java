package com.violetbeach.banking.application.service;

import com.violetbeach.banking.application.port.in.RegisterBankAccountCommand;
import com.violetbeach.banking.application.port.in.RegisterBankAccountUseCase;
import com.violetbeach.banking.application.port.out.BankAccountInfoRequest;
import com.violetbeach.banking.application.port.out.GetMembershipPort;
import com.violetbeach.banking.application.port.out.RegisterBankAccountPort;
import com.violetbeach.banking.application.port.out.RequestBankAccountInfoPort;
import com.violetbeach.banking.domain.BankAccount;
import com.violetbeach.banking.domain.MembershipStatus;
import com.violetbeach.banking.domain.RegisteredBankAccount;
import com.violetbeach.banking.domain.RegisteredBankAccount.BankAccountNumber;
import com.violetbeach.banking.domain.RegisteredBankAccount.BankName;
import com.violetbeach.banking.domain.RegisteredBankAccount.LinkedStatusIsValid;
import com.violetbeach.banking.domain.RegisteredBankAccount.MembershipId;
import com.violetbeach.common.UseCase;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class RegisterBankAccountService implements RegisterBankAccountUseCase {
    private final RegisterBankAccountPort registerBankAccountPort;
    private final RequestBankAccountInfoPort requestBankAccountInfoPort;
    private final GetMembershipPort getMembershipPort;
    @Override
    public RegisteredBankAccount registerBankAccount(RegisterBankAccountCommand command) {
        MembershipStatus membershipStatus = getMembershipPort.getMembership(command.getMembershipId());
        if(!membershipStatus.isValid()) {
            return null;
        }

        BankAccount accountInfo = requestBankAccountInfoPort.getBankAccountInfo(
            new BankAccountInfoRequest(command.getBankName(), command.getBankAccountNumber()));
        accountInfo.verifyBank();

        return registerBankAccountPort.createRegisteredBankAccount(
            new MembershipId(command.getMembershipId() + ""),
            new BankName(command.getBankName()),
            new BankAccountNumber(command.getBankAccountNumber()),
            new LinkedStatusIsValid(command.isValid())
        );
    }
}