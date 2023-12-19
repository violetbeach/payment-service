package com.violetbeach.remittance.application.port.in;


import com.violetbeach.remittance.domain.RemittanceRequest;

public interface RequestRemittanceUseCase {
    RemittanceRequest requestRemittance(RequestRemittanceCommand command);
}
