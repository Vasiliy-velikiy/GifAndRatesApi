package com.example.demo.service.impl;

import com.example.demo.client.impl.CurrencyExchangeServiceClient;
import com.example.demo.model.CurrencyBean;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;

@RunWith(SpringRunner.class)
@SpringBootTest
@ComponentScan("com.example.demo")
class ServiceForRatesImplTest {

    @Value("${base}")
    private String base;

    @MockBean
    private CurrencyExchangeServiceClient client;

    private CurrencyBean currentRates;
    private CurrencyBean prevRates;


    /**
     * T1 - не меняется.
     * T2 - уменьшается.
     * T3 - увеличивается.
     */
    @BeforeEach
    public void init() {
        int time = 1609459199;
        this.currentRates = new CurrencyBean();
        this.currentRates.setTimestamp((double) time);
        this.currentRates.setBase("T_BASE");
        Map<String, Double> currentRatesMap = new HashMap<>();
        currentRatesMap.put("T1", 0.1);
        currentRatesMap.put("T2", 0.5);
        currentRatesMap.put("T3", 1.0);
        currentRatesMap.put(this.base, 73.108);
        currentRatesMap.put("T_BASE", 1.0);
        this.currentRates.setRates(currentRatesMap);

        time = 1609372799;
        this.prevRates = new CurrencyBean();
        this.prevRates.setTimestamp((double) time);
        this.prevRates.setBase("T_BASE");
        Map<String, Double> prevRatesMap = new HashMap<>();
        prevRatesMap.put("T1", 0.1);
        prevRatesMap.put("T2", 1.0);
        prevRatesMap.put("T3", 0.5);
        prevRatesMap.put(this.base, 73.108);
        prevRatesMap.put("T_BASE", 1.0);
        this.prevRates.setRates(prevRatesMap);

    }


    @Test
    public void whenRich() {
        Mockito.when(client.retrieveExchangeValue(anyString(), anyString()))
                .thenReturn(this.currentRates);
        double result = currentRates.getRates().get("T3")-prevRates.getRates().get("T3");
        double expected=0.5;
        assertEquals(expected, result);
    }

    @Test
    public void whenBroke() {
        Mockito.when(client.retrieveExchangeValue(anyString(), anyString()))
                .thenReturn(this.currentRates);
        double result = currentRates.getRates().get("T2")-prevRates.getRates().get("T2");
        double expected=-0.5;
        assertEquals(expected, result);
    }

    @Test
    public void whenZero() {
        Mockito.when(client.retrieveExchangeValue(anyString(), anyString()))
                .thenReturn(this.currentRates);
        double result = currentRates.getRates().get(base)-prevRates.getRates().get(base);
        double expected=0;
        assertEquals(expected, result);
    }

}