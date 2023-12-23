package com.violetbeach.remittance.application.port.in;


import com.violetbeach.remittance.domain.RemittanceRequest;
import java.util.List;

public interface FindRemittanceUseCase {

    List<RemittanceRequest> findRemittanceHistory(FindRemittanceCommand command);
}
