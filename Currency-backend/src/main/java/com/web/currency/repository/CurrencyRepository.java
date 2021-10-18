package com.web.currency.repository;

import com.web.currency.model.Currency;
import com.web.currency.model.CurrencyId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface CurrencyRepository extends JpaRepository<Currency, CurrencyId> {

    List<Currency> findByDate(LocalDate date);

    List<Currency> findByDateBetweenAndCurrencyCode(
            LocalDate publicationTimeStart,
            LocalDate publicationTimeEnd,
            String currencyCode);

}
