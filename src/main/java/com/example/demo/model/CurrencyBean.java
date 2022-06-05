package com.example.demo.model;

import java.util.Map;
import java.util.TreeMap;


/**
 * Модель для работы с курсами валют от openexchangerates.org
 * Пример json`а:
 * https://openexchangerates.org/api/historical/2020-12-21.json?app_id=0f318d468a314b898be437326759e093
 */
public class CurrencyBean {


    String disclaimer;
    String license;
    Double timestamp;
    String base;
    Map<String, Double> rates;

    public CurrencyBean() {
    }

    public CurrencyBean(Map<String, Double> rates, String disclaimer, String license, Double timestamp, String base) {
        this.rates = rates;
        this.disclaimer = disclaimer;
        this.license = license;
        this.timestamp = timestamp;
        this.base = base;
    }

    public Map<String, Double> getRates() {
        return rates;
    }

    public String getDisclaimer() {
        return disclaimer;
    }

    public String getLicense() {
        return license;
    }

    public Double getTimestamp() {
        return timestamp;
    }

    public String getBase() {
        return base;
    }

    public void setDisclaimer(String disclaimer) {
        this.disclaimer = disclaimer;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public void setTimestamp(Double timestamp) {
        this.timestamp = timestamp;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public void setRates(Map<String, Double> rates) {
        this.rates = rates;
    }

    @Override
    public String toString() {
        return "CurrencyBean{" +
                "rates=" + rates +'\n'+
                ", disclaimer='" + disclaimer + '\'' +'\n'+
                ", license='" + license + '\'' +'\n'+
                ", timestamp=" + timestamp +'\n'+
                ", base='" + base + '\'' +
                '}';
    }
}
