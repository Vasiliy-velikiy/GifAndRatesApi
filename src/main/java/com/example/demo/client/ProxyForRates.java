package com.example.demo.client;

import com.example.demo.model.CurrencyBean;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

public interface ProxyForRates {

    CurrencyBean retrieveExchangeValue( String date, String app_id);
}
