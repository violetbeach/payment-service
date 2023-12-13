package com.violetbeach.money.application.service;

import com.violetbeach.common.UseCase;
import com.violetbeach.money.application.port.in.IncreaseMoneyRequestCommand;
import com.violetbeach.money.application.port.in.IncreaseMoneyRequestUseCase;
import com.violetbeach.money.application.port.out.IncreaseMoneyPort;
import com.violetbeach.money.domain.MemberMoney;
import com.violetbeach.money.domain.MoneyChangingRequest;
import java.util.UUID;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class IncreaseMoneyRequestService implements IncreaseMoneyRequestUseCase {
    private final IncreaseMoneyPort increaseMoneyPort;

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
    public MoneyChangingRequest increaseMoneyRequest(IncreaseMoneyRequestCommand command) {
        // 6-3
        increaseMoneyPort.increaseMoney(
            new MemberMoney.MembershipId(command.getTargetMembershipId()),
            command.getAmount());

        return increaseMoneyPort.createMoneyChangingRequest(
            new MoneyChangingRequest.TargetMembershipId(command.getTargetMembershipId()),
            new MoneyChangingRequest.MoneyChangingType(1),
            new MoneyChangingRequest.ChangingMoneyAmount(command.getAmount()),
            new MoneyChangingRequest.MoneyChangingStatus(1),
            new MoneyChangingRequest.Uuid(UUID.randomUUID().toString())
        );
    }
}