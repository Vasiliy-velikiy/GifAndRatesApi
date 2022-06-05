package com.example.demo.controller;


import com.example.demo.service.GifService;
import com.example.demo.service.ServiceForRates;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class RatesController {

    private ServiceForRates serviceForRates;

    public RatesController(ServiceForRates serviceForRates) {
        this.serviceForRates = serviceForRates;
    }

    @GetMapping("/currency")
    public String createTwoRequest() {
        String result=serviceForRates.createTwoRequests();
        return "redirect:" + result;
    }
}
