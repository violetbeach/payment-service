package com.violetbeach.banking.adapter.in.web;

import com.violetbeach.banking.application.port.in.RequestFirmBankingCommand;
import com.violetbeach.banking.application.port.in.RequestFirmBankingUseCase;
import com.violetbeach.common.WebAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@WebAdapter(path = "/banking/firmbanking")
@RequiredArgsConstructor
public class RequestFirmBankingController {

    private final RequestFirmBankingUseCase requestFirmbankingUseCase;

    @PostMapping("/request")
    void requestFirmBanking(@RequestBody RequestFirmBankingRequest request) {
        RequestFirmBankingCommand command = RequestFirmBankingCommand.builder()
            .toBankName(request.toBankName())
            .toBankAccountNumber(request.toBankAccountNumber())
            .fromBankName(request.fromBankName())
            .fromBankAccountNumber(request.fromBankAccountNumber())
            .moneyAmount(request.moneyAmount())
            .build();

        requestFirmbankingUseCase.requestFirmBanking(command);
    }
}
