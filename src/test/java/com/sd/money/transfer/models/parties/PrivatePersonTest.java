/*
 * Copyright (c) 2018. Sahitya. All rights reserved.
 * 
 */

package com.sd.money.transfer.models.parties;

import com.sd.money.transfer.enums.PartyType;
import com.sd.money.transfer.interfaces.Party;
import com.sd.money.transfer.models.parties.AbstractParty;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PrivatePersonTest {

    @Test
    void attributes() {
        final Party party = AbstractParty.makePrivatePerson(15L, "123456789012", "test", "best");
        assertNotNull(party);
        assertEquals(Long.valueOf(15L), party.getId());
        assertTrue(party.isValid());
        assertFalse(party.isNotValid());
        assertFalse(party.isLegalPerson());
        assertTrue(party.isPrivatePerson());
        assertEquals(PartyType.PRIVATE_PERSON, party.getPartyType());
        assertEquals("test best", party.getName());
        assertEquals("123456789012", party.getTaxIdentificationNumber());
    }
}