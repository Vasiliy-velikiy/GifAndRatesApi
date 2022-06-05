package com.example.demo.client.impl;


import com.example.demo.client.ProxyForGif;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * Клиент для получения гифок
 */
@FeignClient(name = "GifClient", url = "${gif.url}")
public interface GifClient extends ProxyForGif {

    @Override
    @GetMapping("/random")
    ResponseEntity<Map> getRandomGif(@RequestParam("api_key") String apiKey, @RequestParam("tag") String tag);

}
