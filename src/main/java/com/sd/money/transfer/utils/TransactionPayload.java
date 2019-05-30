/*
 * Copyright (c) 2018. Sahitya. All rights reserved.
 * 
 */

package com.sd.money.transfer.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public final class TransactionPayload {

    private final Long debitAccountId;
    private final Long creditAccountId;
    private final BigDecimal amount;
}
