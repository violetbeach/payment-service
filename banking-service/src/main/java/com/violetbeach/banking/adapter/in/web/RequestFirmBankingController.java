package com.violetbeach.banking.adapter.in.web;

import com.violetbeach.banking.application.port.in.RequestFirmBankingCommand;
import com.violetbeach.banking.application.port.in.RequestFirmBankingUseCase;
import com.violetbeach.banking.application.port.in.UpdateFirmBankingCommand;
import com.violetbeach.banking.application.port.in.UpdateFirmBankingUseCase;
import com.violetbeach.common.WebAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@WebAdapter(path = "/banking/firm-banking/request")
@RequiredArgsConstructor
public class RequestFirmBankingController {

    private final RequestFirmBankingUseCase requestFirmbankingUseCase;

    private final UpdateFirmBankingUseCase updateFirmBankingUseCase;

    @PostMapping
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

    @PutMapping
    void updateFirmBanking(@RequestBody UpdateFirmBankingRequest request) {
        UpdateFirmBankingCommand command = UpdateFirmBankingCommand.builder()
            .firmBankingAggregateIdentifier(request.firmBankingAggregateIdentifier())
            .build();

        updateFirmBankingUseCase.updateFirmBanking(command);
    }
}