package com.violetbeach.banking.adapter.in.web;

import com.violetbeach.banking.application.port.in.UpdateFirmBankingCommand;
import com.violetbeach.banking.application.port.in.UpdateFirmBankingUseCase;
import com.violetbeach.common.WebAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@WebAdapter(path = "/banking/firm-banking/request")
@RequiredArgsConstructor
public class RequestFirmBankingController {

    private final UpdateFirmBankingUseCase updateFirmBankingUseCase;

    @PutMapping
    void updateFirmBanking(@RequestBody UpdateFirmBankingRequest request) {
        UpdateFirmBankingCommand command = UpdateFirmBankingCommand.builder()
            .firmBankingAggregateIdentifier(request.firmBankingAggregateIdentifier())
            .build();

        updateFirmBankingUseCase.updateFirmBanking(command);
    }
}