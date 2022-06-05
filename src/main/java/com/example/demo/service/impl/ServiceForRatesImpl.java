package com.example.demo.service.impl;


import com.example.demo.client.ProxyForRates;
import com.example.demo.model.CurrencyBean;
import com.example.demo.service.GifService;
import com.example.demo.service.ServiceForRates;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Сервис для сравнения курсов между сегодня и вчера
 */
@Service
public class ServiceForRatesImpl implements ServiceForRates {
    private final ProxyForRates proxy;
    private final GifService gifService;

    SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");//форматтер для даты 2022-06-02

    @Value("${seconds}")
    long SECONDS_IN_DAY;  //количество милисекунд в дне
    @Value("${openexchangerates.app.id}")
    private String appId; //уникальный индентификатор приложения для api exchange
    @Value("${base}")
    private String base; //валюта по отношению к доллару(берем рубль)

    public ServiceForRatesImpl(ProxyForRates proxy, GifService gifService) {
        this.proxy = proxy;
        this.gifService = gifService;
    }

    /**
     * Создание 2х запросов через клиент на сегодня и на вчера, сравнение курса валют и отправка на другой сервис
     */
    @Override
    public String createTwoRequests() {
        Date today = new Date(System.currentTimeMillis());
        Date tomorrow = new Date(System.currentTimeMillis() - SECONDS_IN_DAY);
        String formattedToday = format1.format(today.getTime());
        String formattedTomorrow = format1.format(tomorrow.getTime());

        CurrencyBean responseOne = proxy.retrieveExchangeValue(formattedToday + ".json", appId);
        CurrencyBean responseTwo = proxy.retrieveExchangeValue(formattedTomorrow + ".json", appId);

        Double s1 = responseOne.getRates().get(base);
        Double s2 = responseTwo.getRates().get(base);

        double difference = s1 - s2; //сравнение курса между двумя днями

        System.out.println(difference);

        String url = gifService.getGif(difference);
        return url;
    }
}
