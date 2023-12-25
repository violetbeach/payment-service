package com.violetbeach.banking.application.service;

import com.violetbeach.banking.adapter.axon.command.UpdateRequestFirmBankingCommand;
import com.violetbeach.banking.application.port.in.UpdateFirmBankingCommand;
import com.violetbeach.banking.application.port.in.UpdateFirmBankingUseCase;
import com.violetbeach.banking.application.port.out.RequestFirmBankingPort;
import com.violetbeach.banking.domain.FirmBankingRequest;
import com.violetbeach.common.UseCase;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;

@UseCase
@RequiredArgsConstructor
public class RequestFirmBankingService implements UpdateFirmBankingUseCase {

    private final RequestFirmBankingPort requestFirmBankingPort;
    private final CommandGateway commandGateway;

    @Override
    public void updateFirmBanking(UpdateFirmBankingCommand command) {
        commandGateway.send(new UpdateRequestFirmBankingCommand(
            command.getFirmBankingAggregateIdentifier(),
            command.getFirmBankingStatus()
        )).whenComplete((result, throwable) -> {
            if (throwable == null) {
                FirmBankingRequest firmBankingRequest = requestFirmBankingPort.getFirmBankingRequest(
                    new FirmBankingRequest.FirmBankingAggregateIdentifier(result.toString())
                );

                // 2. 외부 은행에 펌뱅킹 요청
                if (command.getFirmBankingStatus() == 0) {
                    firmBankingRequest.setCompleted();
                } else {
                    firmBankingRequest.setFailed();
                }
                requestFirmBankingPort.modifyFirmBankingRequest(firmBankingRequest);
            } else {
                throwable.printStackTrace();
            }
        });
    }
}