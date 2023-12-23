package com.violetbeach.money.adapter.in.web;

import com.violetbeach.common.WebAdapter;
import com.violetbeach.money.application.port.in.CreateMemberMoneyRequestCommand;
import com.violetbeach.money.application.port.in.CreateMemberMoneyRequestUseCase;
import com.violetbeach.money.application.port.in.IncreaseMoneyRequestCommand;
import com.violetbeach.money.application.port.in.IncreaseMoneyRequestUseCase;
import com.violetbeach.money.domain.MoneyChangingRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@WebAdapter(path = "/money")
@RequiredArgsConstructor
public class RequestMoneyChangingController {

    private final IncreaseMoneyRequestUseCase increaseMoneyRequestUseCase;
    private final CreateMemberMoneyRequestUseCase createMemberMoneyRequestUseCase;

    @PostMapping(path = "/increase")
    void increaseMoneyChangingRequest(
        @RequestBody IncreaseMoneyChangingRequest request) {
        IncreaseMoneyRequestCommand command = IncreaseMoneyRequestCommand.builder()
            .targetMembershipId(request.targetMembershipId())
            .amount(request.amount())
            .build();

        increaseMoneyRequestUseCase.increaseMoneyRequest(command);
    }

    @PostMapping(path = "/increase-async")
    MoneyChangingResultDetail increaseMoneyChangingRequestAsync(
        @RequestBody IncreaseMoneyChangingRequest request) {
        IncreaseMoneyRequestCommand command = IncreaseMoneyRequestCommand.builder()
            .targetMembershipId(request.targetMembershipId())
            .amount(request.amount())
            .build();

        MoneyChangingRequest moneyChangingRequest = increaseMoneyRequestUseCase.increaseMoneyRequestAsync(
            command);

        return new MoneyChangingResultDetail(
            moneyChangingRequest.getMoneyChangingRequestId(),
            0,
            0,
            moneyChangingRequest.getChangingMoneyAmount());
    }

    @PostMapping
    void createMemberMoney(@RequestBody CreateMemberMoneyRequest request) {
        CreateMemberMoneyRequestCommand command = CreateMemberMoneyRequestCommand.builder()
            .targetMembershipId(request.targetMembershipId())
            .build();
        createMemberMoneyRequestUseCase.createMoney(command);
    }
}
