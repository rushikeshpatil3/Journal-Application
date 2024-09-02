package com.rushikeshpatil.journalApp.serviceImpl;

import com.rushikeshpatil.journalApp.api.response.WeatherResponse;
import com.rushikeshpatil.journalApp.cache.AppCache;
import com.rushikeshpatil.journalApp.constants.PlaceHolders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class Weather {

//        private static final String API ="http://api.weatherstack.com/current?access_key=ACCESS_KEY&query=CITY";

        @Value("${weather.api.key}")
        private String API_ACCESS_KEY;

        @Autowired
        private RestTemplate restTemplate;

        @Autowired
        private AppCache appCache;

        public WeatherResponse getWeather(String city){
                String finalApi=appCache.APP_CACHE.get(AppCache.keys.WEATHER_API.toString()).replace(PlaceHolders.API_KEY,API_ACCESS_KEY).replace(PlaceHolders.CITY,city);
                ResponseEntity<WeatherResponse> responseEntity = restTemplate.exchange(finalApi, HttpMethod.GET, null, WeatherResponse.class);
                WeatherResponse body = responseEntity.getBody();
                return body;
        }

}
