package com.violetbeach.money.application.port.in;

import com.violetbeach.money.domain.MoneyChangingRequest;

public interface IncreaseMoneyRequestUseCase {

    void increaseMoneyRequest(IncreaseMoneyRequestCommand command);

    MoneyChangingRequest increaseMoneyRequestAsync(IncreaseMoneyRequestCommand command);
}