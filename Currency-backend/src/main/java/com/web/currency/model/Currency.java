package com.web.currency.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@IdClass(CurrencyId.class)
public class Currency {

    @Id
    private LocalDate date;

    @Id
    private String currencyCode;

    private Double currencyRate;
}



