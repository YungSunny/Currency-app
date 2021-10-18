package com.web.currency.service;

import com.web.currency.model.Currency;
import com.web.currency.repository.CurrencyRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@Slf4j
@EnableScheduling
public class CurrencyService {
    
    @Autowired
    private FetchService fetchService;

    @Autowired
    private CurrencyRepository currencyRepository;


    public void getCurrencyFromApi() {
        fetchService.getCurrency("", LocalDate.now().toString(),"");
    }

    @EventListener(ApplicationReadyEvent.class)
    public void fillDatabase() {
        String startDate = LocalDate.now().minusMonths(1).toString();
        fetchService.getCurrency("", startDate,LocalDate.now().toString());
    }

    public List<Currency> getTodayCurrencies() {
        return currencyRepository.findByDate(LocalDate.now());
    }

    public List<Currency> getCurrencyMonthRates(String currencyCode) {
        return currencyRepository.findByDateBetweenAndCurrencyCode(LocalDate.now().minusMonths(1),
                LocalDate.now(),
                currencyCode);
    }

    public List<Currency> getCurrencyTwoWeeksRates(String currencyCode) {
        return currencyRepository.findByDateBetweenAndCurrencyCode(LocalDate.now().minusWeeks(2),
                LocalDate.now(),
                currencyCode);
    }

    public List<Currency> getCurrencyWeekRates(String currencyCode) {
        return currencyRepository.findByDateBetweenAndCurrencyCode(LocalDate.now().minusWeeks(1),
                LocalDate.now(),
                currencyCode);
    }
}
