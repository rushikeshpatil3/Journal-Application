package com.rushikeshpatil.journalApp.serviceImpl;

import com.rushikeshpatil.journalApp.api.response.WeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class Weather {

        private static final String API ="http://api.weatherstack.com/current?access_key=ACCESS_KEY&query=CITY";
        private static final String API_ACCESS_KEY="296ef9e2ebd626ec1b732fbab0b47ae3";

        @Autowired
        private RestTemplate restTemplate;

        public WeatherResponse getWeather(String city){
                String finalApi=API.replace("ACCESS_KEY",API_ACCESS_KEY).replace("CITY",city);
                ResponseEntity<WeatherResponse> responseEntity = restTemplate.exchange(finalApi, HttpMethod.GET, null, WeatherResponse.class);
                WeatherResponse body = responseEntity.getBody();
                return body;
        }

}
