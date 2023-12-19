package com.violetbeach.remittance.application.service;

import com.violetbeach.common.UseCase;
import com.violetbeach.remittance.application.port.in.RequestRemittanceCommand;
import com.violetbeach.remittance.application.port.in.RequestRemittanceUseCase;
import com.violetbeach.remittance.application.port.out.RequestRemittancePort;
import com.violetbeach.remittance.application.port.out.banking.BankingPort;
import com.violetbeach.remittance.application.port.out.membership.MembershipPort;
import com.violetbeach.remittance.application.port.out.membership.MembershipStatus;
import com.violetbeach.remittance.application.port.out.money.MoneyInfo;
import com.violetbeach.remittance.application.port.out.money.MoneyPort;
import com.violetbeach.remittance.domain.RemittanceRequest;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class RequestRemittanceService implements RequestRemittanceUseCase {
    private final RequestRemittancePort requestRemittancePort;
    private final MembershipPort membershipPort;
    private final MoneyPort moneyPort;
    private final BankingPort bankingPort;

    @Override
    public RemittanceRequest requestRemittance(RequestRemittanceCommand command) {
        // 0. 송금 요청 상태를 시작 상태로 기록
        RemittanceRequest remittanceRequest = requestRemittancePort.createRemittanceRequestHistory(command);

        // 1. from 멤버십 상태 확인 (membership Svc)
        MembershipStatus membershipStatus = membershipPort.getMembershipStatus(command.getFromMembershipId());
        if (!membershipStatus.isValid()) {
            return null;
        }

        // 2. 잔액 존재하는지 확인 (money svc)
        MoneyInfo moneyInfo = moneyPort.getMoneyInfo(command.getFromMembershipId());

        // 잔액이 충분치 않은 경우. -> 충전이 필요한 경우
        if(moneyInfo.balance() < command.getAmount()) {
            // command.getAmount() - moneyInfo.getBalance()
            // 만원 단위로 올림하는 Math 함수
            int rechargeAmount = (int) Math.ceil((command.getAmount() - moneyInfo.balance()) / 10000.0) * 10000;

            // 2-1. 잔액이 충분하지 않다면, 충전 요청 (money svc)
            boolean moneyResult = moneyPort.requestMoneyRecharging(command.getFromMembershipId(), rechargeAmount);
            if (!moneyResult){
                return null;
            }
        }

        // 3. 송금 타입 (고객/은행)
        if (command.getRemittanceType() == 0) {
            // 3-1. 내부 고객일 경우
            // from 고객 머니 감액, to 고객 머니 증액 (money svc)
            boolean remittanceResult1 = moneyPort.requestMoneyDecrease(command.getFromMembershipId(), command.getAmount());
            boolean remittanceResult2 = moneyPort.requestMoneyIncrease(command.getToMembershipId(), command.getAmount());
            if (!remittanceResult1 || !remittanceResult2) {
                return null;
            }

        } else if (command.getRemittanceType()==1) {
            // 3-2. 외부 은행 계좌
            // 외부 은행 계좌가 적절한지 확인 (banking svc)
            // 법인계좌 -> 외부 은행 계좌 펌뱅킹 요청 (banking svc)
            boolean remittanceResult = bankingPort.requestFirmBanking(command.getToBankName(), command.getToBankAccountNumber(), command.getAmount());
            if (!remittanceResult) {
                return null;
            }
        }

        // 4. 송금 요청 상태를 성공으로 기록 (persistence layer)
        remittanceRequest.setSuccess();
        requestRemittancePort.saveRemittanceRequestHistory(remittanceRequest);
        return null;
    }
}
