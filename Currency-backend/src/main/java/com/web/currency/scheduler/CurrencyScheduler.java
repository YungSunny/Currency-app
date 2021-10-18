package com.web.currency.scheduler;

import com.web.currency.service.CurrencyService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

@Log4j2
public class CurrencyScheduler {

    @Autowired
    private CurrencyService currencyService;

    @Scheduled(cron = "0 0 0 * * *")
    void fetchCurrenciesJob() {
        log.info("Starting scheduled job for currency fetching");
        currencyService.getCurrencyFromApi();
    }
}
