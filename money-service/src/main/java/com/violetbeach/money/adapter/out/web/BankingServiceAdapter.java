package com.violetbeach.money.adapter.out.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.violetbeach.common.web.CommonHttpClient;
import com.violetbeach.money.application.port.out.GetRegisteredBankAccountPort;
import com.violetbeach.money.application.port.out.RegisteredBankAccountAggregateIdentifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
class BankingServiceAdapter implements GetRegisteredBankAccountPort {

    private final CommonHttpClient commonHttpClient;
    private final String bankingServiceUrl;
    private final ObjectMapper mapper;

    public BankingServiceAdapter(CommonHttpClient commonHttpClient,
        @Value("${service.banking.url}") String bankingServiceUrl) {
        this.commonHttpClient = commonHttpClient;
        this.bankingServiceUrl = bankingServiceUrl;
        this.mapper = new ObjectMapper();
    }

    @Override
    public RegisteredBankAccountAggregateIdentifier getRegisteredBankAccount(String membershipId) {
        String url = String.join("/", bankingServiceUrl, "banking/account", membershipId);
        try {
            String jsonResponse = commonHttpClient.sendGetRequest(url).body();

            RegisteredBankAccount registeredBankAccount = mapper.readValue(jsonResponse,
                RegisteredBankAccount.class);

            return new RegisteredBankAccountAggregateIdentifier(
                registeredBankAccount.getRegisteredBankAccountId(),
                registeredBankAccount.getAggregateIdentifier(),
                registeredBankAccount.getBankName(),
                registeredBankAccount.getBankName(),
                registeredBankAccount.getBankAccountNumber()
            );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
