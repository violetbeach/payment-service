package com.violetbeach.remittance.application.port.out;

import com.violetbeach.remittance.application.port.in.FindRemittanceCommand;
import com.violetbeach.remittance.domain.RemittanceRequest;
import java.util.List;

public interface FindRemittancePort {
    List<RemittanceRequest> findRemittanceHistory(FindRemittanceCommand command);
}
