package com.violetbeach.banking.domain;

import lombok.Getter;

@Getter
public class FirmBankingResult {
    private final int resultCode; // 0: 성공, 1: 실패

    public FirmBankingResult(int resultCode) {
        this.resultCode = resultCode;
    }
}
