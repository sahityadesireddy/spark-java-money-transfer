/*
 * Copyright (c) 2018. Sahitya. All rights reserved.
 * 
 */

package com.sd.money.transfer.models.currencies;

import org.junit.jupiter.api.Test;

import com.sd.money.transfer.interfaces.Currency;
import com.sd.money.transfer.models.currencies.BaseCurrency;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class InvalidCurrencyTest {

    @Test
    void invalidCurrency() {
        final Currency cc = BaseCurrency.getInvalid();
        assertNotNull(cc);

        assertFalse(cc.isValid());
        assertTrue(cc.isNotValid());

        assertEquals(BaseCurrency.getInvalid(), cc);
        assertEquals(-1, cc.hashCode());

        assertEquals("", cc.getISOCode());
    }
}