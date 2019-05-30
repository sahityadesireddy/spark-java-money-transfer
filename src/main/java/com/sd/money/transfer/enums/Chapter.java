/*
 * Copyright (c) 2018. Sahitya. All rights reserved.
 * 
 */

package com.sd.money.transfer.enums;

import java.util.Objects;

public enum Chapter {

    BALANCE("�?", "Балан�?овые �?чета"),
    TRUST_MANAGEMENT("Б", "Счета доверительного управлени�?"),
    OFF_BALANCE("В", "Внебалан�?овые �?чета");

    private final String code;
    private final String description;

    Chapter(String code, String description) {
        Objects.requireNonNull(code);
        Objects.requireNonNull(description);

        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
