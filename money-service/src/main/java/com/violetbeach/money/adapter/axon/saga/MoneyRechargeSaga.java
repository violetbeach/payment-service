package com.violetbeach.money.adapter.axon.saga;

import com.violetbeach.common.saga.command.CheckRegisteredBankAccountCommand;
import com.violetbeach.common.saga.command.RequestFirmBankingCommand;
import com.violetbeach.common.saga.command.RollbackFirmBankingRequestCommand;
import com.violetbeach.common.saga.event.CheckedRegisteredBankAccountEvent;
import com.violetbeach.common.saga.event.RequestFirmBankingFinishedEvent;
import com.violetbeach.common.saga.event.RollbackFirmBankingFinishedEvent;
import com.violetbeach.money.adapter.axon.event.RechargingRequestCreatedEvent;
import com.violetbeach.money.application.port.out.IncreaseMoneyPort;
import com.violetbeach.money.domain.MemberMoney.MembershipId;
import java.util.UUID;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.modelling.saga.EndSaga;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.SagaLifecycle;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.spring.stereotype.Saga;
import org.springframework.beans.factory.annotation.Autowired;

@Saga
@Slf4j
public class MoneyRechargeSaga {

    @NonNull
    private transient CommandGateway commandGateway;

    @Autowired
    public void setCommandGateway(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @StartSaga
    @SagaEventHandler(associationProperty = "rechargingRequestId")
    public void handle(RechargingRequestCreatedEvent event) {
        String checkRegisteredBankAccountId = UUID.randomUUID().toString();
        SagaLifecycle.associateWith("checkRegisteredBankAccountId", checkRegisteredBankAccountId);

        commandGateway.send(new CheckRegisteredBankAccountCommand(
            event.getBankingAccountAggregateIdentifier(),
            event.toString(),
            checkRegisteredBankAccountId,
            event.getMembershipId(),
            event.getBankName(),
            event.getBankAccountNumber(),
            event.getAmount()
        )).whenComplete(
            (result, throwable) -> {
                if (throwable != null) {
                    log.error("CheckRegisteredBankAccountCommand Command failed");
                    throw new RuntimeException(throwable);
                } else {
                    log.info("CheckRegisteredBankAccountCommand Command success");
                }
            }
        );
    }

    @SagaEventHandler(associationProperty = "checkRegisteredBankAccountId")
    public void handle(CheckedRegisteredBankAccountEvent event) {
        boolean status = event.isChecked();
        if (status) {
            log.info("CheckedRegisteredBankAccountEvent event success");
        } else {
            log.error("CheckedRegisteredBankAccountEvent event Failed");
        }

        String requestFirmBankingId = UUID.randomUUID().toString();
        SagaLifecycle.associateWith("requestFirmBankingId", requestFirmBankingId);

        commandGateway.send(new RequestFirmBankingCommand(
            requestFirmBankingId,
            event.getFirmBankingRequestAggregateIdentifier(),
            event.getRechargingRequestId(),
            event.getMembershipId(),
            "하나은행",
            "000-000-0000",
            event.getToBankName(),
            event.getToBankAccountNumber(),
            event.getAmount()
        )).whenComplete(
            (result, throwable) -> {
                if (throwable != null) {
                    log.error("RequestFirmBankingCommand Command failed");
                    throw new RuntimeException(throwable);
                } else {
                    log.info("RequestFirmBankingCommand Command success");
                }
            }
        );
    }

    @SagaEventHandler(associationProperty = "requestFirmBankingId")
    public void handle(RequestFirmBankingFinishedEvent event, IncreaseMoneyPort increaseMoneyPort) {
        boolean status = event.getStatus() == 0;
        if (status) {
            log.info("RequestFirmBankingFinishedEvent event success");
        } else {
            log.error("RequestFirmBankingFinishedEvent event Failed");
        }

        // DB Update 명령.
        try {
            increaseMoneyPort.increaseMoney(
                new MembershipId(event.getMembershipId()), event.getMoneyAmount());
            SagaLifecycle.end();
        } catch (Throwable e) {
            // 실패 시, 롤백 이벤트
            String rollbackFirmBankingId = UUID.randomUUID().toString();
            SagaLifecycle.associateWith("rollbackFirmBankingId", rollbackFirmBankingId);
            commandGateway.send(new RollbackFirmBankingRequestCommand(
                rollbackFirmBankingId
                , event.getRequestFirmBankingAggregateIdentifier()
                , event.getRechargingRequestId()
                , event.getMembershipId()
                , event.getToBankName()
                , event.getToBankAccountNumber()
                , event.getMoneyAmount()
            )).whenComplete(
                (result, throwable) -> {
                    if (throwable != null) {
                        log.error("RollbackFirmBankingRequestCommand Command failed");
                    } else {
                        log.info("Saga success : " + result.toString());
                        SagaLifecycle.end();
                    }
                }
            );
        }
    }

    @EndSaga
    @SagaEventHandler(associationProperty = "rollbackFirmBankingId")
    public void handle(RollbackFirmBankingFinishedEvent event) {
        log.info("RollbackFirmBankingFinishedEvent saga: " + event.toString());
    }

}
