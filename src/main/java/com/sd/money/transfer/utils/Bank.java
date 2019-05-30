/*
 * Copyright (c) 2018. Sahitya. All rights reserved.
 * 
 */

package com.sd.money.transfer.utils;

import com.sd.money.transfer.interfaces.Account;
import com.sd.money.transfer.interfaces.Transaction;
import com.sd.money.transfer.interfaces.repositories.Repository;
import com.sd.money.transfer.repositories.Context;
import com.sd.money.transfer.utils.generators.DataGenerator;

import lombok.Getter;

import java.util.NoSuchElementException;
import java.util.Objects;

public final class Bank {

    @Getter
    private final Context context;

    private Bank(Context context) {
        this.context = context;
    }

    public void generateData() {
        DataGenerator.getInstance(context)
                .withPartiesCount(100)
                .withAccountsPerClient(2)
                .withClientTransactions(10_000)
                .generate();
    }

    public Transaction transfer(TransactionPayload payload) {
        Objects.requireNonNull(payload, "Transaction data cannot be null");

        final Repository<Account> accountRepository = context.getAccountsRepository();
        final Account debit = accountRepository.getById(payload.getDebitAccountId());
        validateAccount(debit, payload.getDebitAccountId());
        final Account credit = accountRepository.getById(payload.getCreditAccountId());
        validateAccount(credit, payload.getCreditAccountId());

        final Transaction trn = context.getTransactionRepository().add(debit, credit, payload.getAmount());
        trn.run();
        return trn;
    }

    private void validateAccount(Account account, Long id) {
        if (account.isNotValid()) {
            throw new NoSuchElementException(String.format("Account with id %d is not found", id));
        }
    }

    private static class LazyHolder {
        static final Bank INSTANCE = new Bank(Context.create());
    }

    public static Bank getInstance() {
        return LazyHolder.INSTANCE;
    }
}
