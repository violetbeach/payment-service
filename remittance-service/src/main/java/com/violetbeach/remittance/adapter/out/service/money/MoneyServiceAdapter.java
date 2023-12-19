package com.violetbeach.remittance.adapter.out.service.money;

import com.violetbeach.common.ExternalSystemAdapter;
import com.violetbeach.common.web.CommonHttpClient;
import com.violetbeach.remittance.application.port.out.money.MoneyInfo;
import com.violetbeach.remittance.application.port.out.money.MoneyPort;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

@ExternalSystemAdapter
@RequiredArgsConstructor
public class MoneyServiceAdapter implements MoneyPort {
    private final CommonHttpClient moneyServiceHttpClient;
    @Value("${service.money.url}")
    private String moneyServiceEndpoint;

    @Override
    public MoneyInfo getMoneyInfo(String membershipId) {
        return null;
    }

    @Override
    public boolean requestMoneyRecharging(String membershipId, int amount) {
        return false;
    }

    @Override
    public boolean requestMoneyIncrease(String membershipId, int amount) {
        return false;
    }

    @Override
    public boolean requestMoneyDecrease(String membershipId, int amount) {
        return false;
    }
}
