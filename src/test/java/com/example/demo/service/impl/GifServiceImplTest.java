package com.example.demo.service.impl;

import com.example.demo.client.impl.CurrencyExchangeServiceClient;
import com.example.demo.client.impl.GifClient;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@ComponentScan("com.example.demo")
class GifServiceImplTest {

    @Autowired
    private GifServiceImpl gifService;
    @MockBean
    private GifClient gifClient;

    @Test
    void getGif() {
        ResponseEntity<Map> testEntity = new ResponseEntity<>(new HashMap(), HttpStatus.OK);
        Mockito.when(gifClient.getRandomGif(anyString(), anyString()))
                .thenReturn(testEntity);
        double difference=-1;
        String status =  gifService.getGif(difference);
        assertEquals(testEntity.getStatusCode().toString(), status);
    }
}