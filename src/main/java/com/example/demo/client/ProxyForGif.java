package com.example.demo.client;

import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface ProxyForGif {

   ResponseEntity<Map> getRandomGif(String apiKey, String tag);
}
