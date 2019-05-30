/*
 * Copyright (c) 2018. Sahitya. All rights reserved.
 * 
 */

package com.sd.money.transfer.interfaces;

public interface Currency extends Validatable {

    int ISO_CODE_LENGTH = 3;

    String getISOCode();
}
