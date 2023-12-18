package com.violetbeach.money.adapter.out.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.violetbeach.common.web.CommonHttpClient;
import com.violetbeach.money.application.port.out.GetMembershipPort;
import com.violetbeach.money.domain.MembershipStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
class MembershipServiceAdapter implements GetMembershipPort {
    private final CommonHttpClient commonHttpClient;
    private final String membershipServiceUrl;
    private final ObjectMapper objectMapper;

    public MembershipServiceAdapter(CommonHttpClient commonHttpClient,
        @Value("${service.membership.url}") String membershipServiceUrl) {
        this.commonHttpClient = commonHttpClient;
        this.membershipServiceUrl = membershipServiceUrl;
        this.objectMapper = new ObjectMapper();
    }

    @Override
    public MembershipStatus getMembership(String membershipId) {
        String url = String.join("/", membershipServiceUrl, "membership", membershipId);
        try {
            String jsonResponse = commonHttpClient.sendGetRequest(url).body();
            MembershipResponse membership = objectMapper.readValue(jsonResponse, MembershipResponse.class);

            if (membership.isValid()){
                return new MembershipStatus(membership.membershipId(), true);
            } else {
                return new MembershipStatus(membership.membershipId(), false);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
