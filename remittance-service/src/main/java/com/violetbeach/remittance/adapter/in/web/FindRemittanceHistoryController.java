package com.violetbeach.remittance.adapter.in.web;

import com.violetbeach.common.WebAdapter;
import com.violetbeach.remittance.application.port.in.FindRemittanceCommand;
import com.violetbeach.remittance.application.port.in.FindRemittanceUseCase;
import com.violetbeach.remittance.domain.RemittanceRequest;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@WebAdapter
@RequiredArgsConstructor
public class FindRemittanceHistoryController {
    private final FindRemittanceUseCase findRemittanceUseCase;

    @GetMapping( "/remittance/{membershipId}")
    List<RemittanceRequest> findRemittanceHistory(@PathVariable String membershipId) {
        FindRemittanceCommand command = FindRemittanceCommand.builder()
                .membershipId(membershipId)
                .build();
        return findRemittanceUseCase.findRemittanceHistory(command);
    }
}
