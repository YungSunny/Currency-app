package com.web.currency.scheduler.config;

import com.web.currency.scheduler.CurrencyScheduler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableScheduling
public class SchedulerConfig {

    @Bean
    public CurrencyScheduler currencyScheduler() {
        return new CurrencyScheduler();
    }
}
