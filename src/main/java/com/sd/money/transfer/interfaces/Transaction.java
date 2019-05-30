/*
 * Copyright (c) 2018. Sahitya. All rights reserved.
 * 
 */

package com.sd.money.transfer.interfaces;

import java.math.BigDecimal;

import com.sd.money.transfer.enums.TransactionState;

public interface Transaction extends Identifiable {

    Account getDebit();

    Account getCredit();

    BigDecimal getAmount();

    TransactionState getState();

    boolean run();
}
