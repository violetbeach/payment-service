package com.violetbeach.remittance.application.port.out;

import com.violetbeach.remittance.application.port.in.RequestRemittanceCommand;
import com.violetbeach.remittance.domain.RemittanceRequest;

public interface RequestRemittancePort {
    RemittanceRequest createRemittanceRequestHistory(RequestRemittanceCommand command);
    boolean saveRemittanceRequestHistory(RemittanceRequest remittanceRequest);
}
