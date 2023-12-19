package com.violetbeach.remittance.adapter.in.web;

import com.violetbeach.common.WebAdapter;
import com.violetbeach.remittance.application.port.in.RequestRemittanceCommand;
import com.violetbeach.remittance.application.port.in.RequestRemittanceUseCase;
import com.violetbeach.remittance.domain.RemittanceRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@WebAdapter
@RequiredArgsConstructor
public class RequestRemittanceController {
    private final RequestRemittanceUseCase requestRemittanceUseCase;

    @PostMapping(path = "/remittance/request")
    RemittanceRequest requestRemittance(@RequestBody RequestRemittanceRequest request) {
        RequestRemittanceCommand command = RequestRemittanceCommand.builder()
                .fromMembershipId(request.fromMembershipId())
                .toMembershipId(request.toMembershipId())
                .toBankName(request.toBankName())
                .toBankAccountNumber(request.toBankAccountNumber())
                .amount(request.amount())
                .remittanceType(request.remittanceType())
                .build();
        return requestRemittanceUseCase.requestRemittance(command);
    }
}
