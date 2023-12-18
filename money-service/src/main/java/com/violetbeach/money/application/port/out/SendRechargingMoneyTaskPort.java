package com.violetbeach.money.application.port.out;

import com.violetbeach.common.task.RechargingMoneyTask;

public interface SendRechargingMoneyTaskPort {
    void sendRechargingMoneyTaskPort(RechargingMoneyTask task);
}
