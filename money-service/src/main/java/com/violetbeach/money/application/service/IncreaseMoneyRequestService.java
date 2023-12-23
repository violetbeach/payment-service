package com.violetbeach.money.application.service;

import com.violetbeach.common.CountDownLatchManager;
import com.violetbeach.common.UseCase;
import com.violetbeach.common.task.RechargingMoneyTask;
import com.violetbeach.common.task.SubTask;
import com.violetbeach.money.adapter.axon.command.IncreaseMoneyRequestEventCommand;
import com.violetbeach.money.application.port.in.IncreaseMoneyRequestCommand;
import com.violetbeach.money.application.port.in.IncreaseMoneyRequestUseCase;
import com.violetbeach.money.application.port.out.GetMemberMoneyPort;
import com.violetbeach.money.application.port.out.IncreaseMoneyPort;
import com.violetbeach.money.application.port.out.SendRechargingMoneyTaskPort;
import com.violetbeach.money.domain.MemberMoney;
import com.violetbeach.money.domain.MemberMoney.MembershipId;
import com.violetbeach.money.domain.MoneyChangingRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;

@UseCase
@RequiredArgsConstructor
public class IncreaseMoneyRequestService implements IncreaseMoneyRequestUseCase {

    private final IncreaseMoneyPort increaseMoneyPort;
    private final SendRechargingMoneyTaskPort sendRechargingMoneyTaskPort;
    private final GetMemberMoneyPort getMemberMoneyPort;
    private final CountDownLatchManager countDownLatchManager;
    private final CommandGateway commandGateway;

    /**
     * 1. 고객 정보가 정상인지 확인 (멤버)
     * 2. 고객의 연동된 계좌가 있는지, 고객의 연동된 계좌의 잔액이 충분한지도 확인 (뱅킹)
     * 3. 법인 계좌 상태도 정상인지 확인 (뱅킹)
     * 4. 증액을 위한 "기록". 요청 상태로 MoneyChangingRequest 를 생성한다.
     * 5. 펌뱅킹을 수행하고 (고객의 연동된 계좌 -> 패캠페이 법인 계좌) (뱅킹)
     * 6-1. 결과가 정상적이라면. 성공으로 MoneyChangingRequest 상태값을 변동 후에 리턴
     * 6-2. 실패 시 MoneyChangingRequest 상태값 변동 후 리턴
     * 6-3. 성공 시  멤버의 MemberMoney 값 증액이 필요
     * @param command
     * @return
     */
    @Override
    public void increaseMoneyRequest(IncreaseMoneyRequestCommand command) {
        MemberMoney memberMoney = getMemberMoneyPort.getMemberMoney(
            new MembershipId(command.getTargetMembershipId()));
        String moneyIdentifier = memberMoney.getAggregateIdentifier();

        // String moneyIdentifier = memberMoneyEntity.getAggregateIdentifier();
        IncreaseMoneyRequestEventCommand eventCommand = IncreaseMoneyRequestEventCommand.builder()
            .aggregateIdentifier(moneyIdentifier)
            .targetMembershipId(command.getTargetMembershipId())
            .amount(command.getAmount())
            .build();

        commandGateway.send(eventCommand)
            .whenComplete((Object result, Throwable throwable) -> {
                if (throwable == null) {
                    System.out.println("Aggregate ID:" + result.toString());

                    increaseMoneyPort.increaseMoney(
                        new MemberMoney.MembershipId(command.getTargetMembershipId())
                        , command.getAmount());

                } else {
                    throw new RuntimeException(throwable);
                }
            });
    }

    @Override
    public MoneyChangingRequest increaseMoneyRequestAsync(IncreaseMoneyRequestCommand command) {
        // Membership Validation
        SubTask validMemberTask = SubTask.builder()
            .subTaskName("validMemberTask : " + "멤버십 유효성 검사")
            .membershipID(command.getTargetMembershipId())
            .taskType("membership")
            .status("ready")
            .build();

        // Banking Account Validation
        SubTask validBankingAccountTask = SubTask.builder()
            .subTaskName("validBankingAccountTask : " + "뱅킹 계좌 유효성 검사")
            .membershipID(command.getTargetMembershipId())
            .taskType("banking")
            .status("ready")
            .build();

        // Amount Money Firmbanking - 무조건 ok 받았다고 가정

        List<SubTask> subTaskList = new ArrayList<>();
        subTaskList.add(validMemberTask);
        subTaskList.add(validBankingAccountTask);

        RechargingMoneyTask task = RechargingMoneyTask.builder()
            .taskID(UUID.randomUUID().toString())
            .taskName("Increase Money Task / 머니 충전 Task")
            .subTaskList(subTaskList)
            .moneyAmount(command.getAmount())
            .membershipID(command.getTargetMembershipId())
            .toBankName("Kakao")
            .build();

        // Task Produce
        sendRechargingMoneyTaskPort.sendRechargingMoneyTaskPort(task);
        countDownLatchManager.addCountDownLatch(task.getTaskID());

        // 3. Wait
        try {
            countDownLatchManager.getCountDownLatch(task.getTaskID()).await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // 3-1. task-consumer
        // sub-task, status 모두 ok -> task 결과를 Produce

        // 4. Task Result Consume
        String result = countDownLatchManager.getDataForKey(task.getTaskID());
        if (result.equals("success")) {
            // 4-1. Consume ok, Logic
            MemberMoney memberMoney = increaseMoneyPort.increaseMoney(
                new MembershipId(command.getTargetMembershipId())
                , command.getAmount());

            if (memberMoney == null) {
                return null;
            }

            return increaseMoneyPort.createMoneyChangingRequest(
                new MoneyChangingRequest.TargetMembershipId(command.getTargetMembershipId()),
                new MoneyChangingRequest.MoneyChangingType(1),
                new MoneyChangingRequest.ChangingMoneyAmount(command.getAmount()),
                new MoneyChangingRequest.MoneyChangingStatus(1),
                new MoneyChangingRequest.Uuid(UUID.randomUUID().toString())
            );
        } else {
            // 4-2. Consume fail, Logic
            return null;
        }
    }
}