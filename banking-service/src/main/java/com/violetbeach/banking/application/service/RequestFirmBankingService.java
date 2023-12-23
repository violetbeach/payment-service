package com.violetbeach.banking.application.service;

import com.violetbeach.banking.adapter.axon.command.CreateRequestFirmBankingCommand;
import com.violetbeach.banking.application.port.in.RequestFirmBankingCommand;
import com.violetbeach.banking.application.port.in.RequestFirmBankingUseCase;
import com.violetbeach.banking.application.port.out.RequestExternalFirmBankingPort;
import com.violetbeach.banking.application.port.out.RequestFirmBankingPort;
import com.violetbeach.banking.domain.ExternalFirmBankingRequest;
import com.violetbeach.banking.domain.FirmBankingRequest;
import com.violetbeach.banking.domain.FirmBankingRequest.FirmBankingAggregateIdentifier;
import com.violetbeach.banking.domain.FirmBankingRequest.FirmBankingStatus;
import com.violetbeach.banking.domain.FirmBankingRequest.FromBankAccountNumber;
import com.violetbeach.banking.domain.FirmBankingRequest.FromBankName;
import com.violetbeach.banking.domain.FirmBankingRequest.MoneyAmount;
import com.violetbeach.banking.domain.FirmBankingRequest.ToBankAccountNumber;
import com.violetbeach.banking.domain.FirmBankingRequest.ToBankName;
import com.violetbeach.banking.domain.FirmBankingResult;
import com.violetbeach.common.UseCase;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;

@UseCase
@RequiredArgsConstructor
public class RequestFirmBankingService implements RequestFirmBankingUseCase {

    private final RequestFirmBankingPort requestFirmbankingPort;
    private final RequestExternalFirmBankingPort requestExternalFirmbankingPort;
    private final CommandGateway commandGateway;

    @Override
    public void requestFirmBanking(RequestFirmBankingCommand command) {
        commandGateway.send(new CreateRequestFirmBankingCommand(
            command.getFromBankName(),
            command.getFromBankAccountNumber(),
            command.getToBankName(),
            command.getToBankAccountNumber(),
            command.getMoneyAmount()
        )).whenComplete((result, throwable) -> {
            if (throwable == null) {
                FirmBankingRequest firmBankingRequest = requestFirmbankingPort.createFirmBankingRequest(
                    new FromBankName(command.getFromBankName()),
                    new FromBankAccountNumber(command.getFromBankAccountNumber()),
                    new ToBankName(command.getToBankName()),
                    new ToBankAccountNumber(command.getToBankAccountNumber()),
                    new MoneyAmount(command.getMoneyAmount()),
                    new FirmBankingStatus(0),
                    new FirmBankingAggregateIdentifier(result.toString())
                );

                FirmBankingResult firmBankingResult = requestExternalFirmbankingPort.requestExternalFirmBanking(
                    new ExternalFirmBankingRequest(
                        command.getFromBankName(),
                        command.getFromBankAccountNumber(),
                        command.getToBankName(),
                        command.getToBankAccountNumber()
                    ));

                if (firmBankingResult.getResultCode() == 0) {
                    firmBankingRequest.setCompleted();
                } else {
                    firmBankingRequest.setFailed();
                }
                requestFirmbankingPort.modifyFirmBankingRequest(firmBankingRequest);
            } else {
                throw new RuntimeException(throwable);
            }
        });
    }
}