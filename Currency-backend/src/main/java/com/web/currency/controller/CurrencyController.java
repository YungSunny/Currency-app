package com.web.currency.controller;

import com.web.currency.model.Currency;
import com.web.currency.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/currency/api")
public class CurrencyController {

    @Autowired
    private CurrencyService currencyService;

    @GetMapping(path = "/get/all")
    public ResponseEntity<List<Currency>> getCurrencies() {
        List<Currency> currencies = currencyService.getTodayCurrencies();
        return new ResponseEntity<>(currencies, HttpStatus.OK);
    }

    @GetMapping(path = "/get/month/{code}")
    public ResponseEntity<List<Currency>> getCurrencyMonthRates(@PathVariable String code) {
        List<Currency> currencies = currencyService.getCurrencyMonthRates(code);
        return new ResponseEntity<>(currencies, HttpStatus.OK);
    }

    @GetMapping(path = "/get/weeks/{code}")
    public ResponseEntity<List<Currency>> getCurrencyTwoWeeksRates(@PathVariable String code) {
        List<Currency> currencies = currencyService.getCurrencyTwoWeeksRates(code);
        return new ResponseEntity<>(currencies, HttpStatus.OK);
    }

    @GetMapping(path = "/get/week/{code}")
    public ResponseEntity<List<Currency>> getCurrencyWeekRates(@PathVariable String code) {
        List<Currency> currencies = currencyService.getCurrencyWeekRates(code);
        return new ResponseEntity<>(currencies, HttpStatus.OK);
    }
}
