package com.violetbeach.remittance.application.service;

import com.violetbeach.common.UseCase;
import com.violetbeach.remittance.application.port.in.FindRemittanceCommand;
import com.violetbeach.remittance.application.port.in.FindRemittanceUseCase;
import com.violetbeach.remittance.application.port.out.FindRemittancePort;
import com.violetbeach.remittance.domain.RemittanceRequest;
import java.util.List;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class FindRemittanceService implements FindRemittanceUseCase {
    private final FindRemittancePort findRemittancePort;

    @Override
    public List<RemittanceRequest> findRemittanceHistory(FindRemittanceCommand command) {
        return findRemittancePort.findRemittanceHistory(command);
    }
}
