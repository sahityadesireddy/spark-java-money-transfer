/*
 * Copyright (c) 2018. Sahitya. All rights reserved.
 * 
 */

package com.sd.money.transfer.interfaces.repositories;

import java.math.BigDecimal;
import java.util.Collection;

import com.sd.money.transfer.interfaces.Account;
import com.sd.money.transfer.interfaces.Currency;
import com.sd.money.transfer.interfaces.Party;

public interface AccountsRepository extends Repository<Account> {

    Account addOurBankAccount(Currency currency, String number, BigDecimal balance);

    Account addOurBankAccount(String number, BigDecimal balance);

    Account getOurBankMainAccount();

    Account addPassiveAccount(Currency currency, String number, Party holder);

    Account addPassiveAccount(String number, Party holder);

    BigDecimal getInitialBalance();

    void validateBalance();

    Collection<Account> getByHolder(Party holder);
}
