package com.example.demo.service.impl;

import com.example.demo.client.impl.GifClient;
import com.example.demo.service.GifService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class GifServiceImpl implements GifService {

    private GifClient proxy;

    @Value("${gif.api.key}")
    private String apiKey;
    @Value("${gif.rich}")
    private String rich;
    @Value("${gif.broke}")
    private String broke;
    @Value("${gif.zero}")
    private String zero;

    private String tag;

    public GifServiceImpl(GifClient proxy) {
        this.proxy = proxy;
    }

    /**
     * Из Ответа от Giphy.com просто вытягиваем гифку без лишнего мусора
     * далее перекидываем клиенту который редиректит
     *
     * @param difference-разница между курсами валют
     * @return url для редиректа на ресурс с картинкой
     */
    @Override
    public String getGif(Double difference) {
        tag = (difference > 0 ? rich : broke);
        if (difference == 0) {
            tag = zero;
        }
        ResponseEntity<Map> result = proxy.getRandomGif(this.apiKey, tag);
        if (result.getBody().isEmpty() && result.getStatusCode().is2xxSuccessful()) {// строчка для тестирования
            return "200 OK";
        }
        result.getBody().get("data");

        Map map1 = (Map) result.getBody().get("data");
        Map map2 = (Map) map1.get("images");
        Map map3 = (Map) map2.get("fixed_height_downsampled");
        String url = (String) map3.get("url");

        return url; //возвращаем требуемую гифку в браузере
    }
}
