package com.example.demo.client.impl;


import com.example.demo.client.ProxyForRates;
import com.example.demo.model.CurrencyBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Клиент для получения курса валют
 */
@FeignClient(name = "CurrencyValue", url = "${openexchangerates.url}")
public interface CurrencyExchangeServiceClient  extends ProxyForRates {

    @Override
    @GetMapping("{date}")
    public CurrencyBean retrieveExchangeValue(@PathVariable("date") String date, @RequestParam(value = "app_id") String app_id);
}
