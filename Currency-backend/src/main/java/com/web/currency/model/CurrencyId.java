package com.web.currency.model;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
public class CurrencyId implements Serializable {

    private LocalDate date;

    private String currencyCode;
}
