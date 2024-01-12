package com.kirana.Kirana_Register.model;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class ExchangeRates {

    // Use WebClient in a production scenario
    static RestTemplate restTemplate = new RestTemplate();


    /**
     * Fetch data from the API and cache the response using Redis and Spring's caching mechanism.
     *
     * @param exchangeRateApiUrl The URL to fetch data from.
     * @return A Map containing the API response.
     */
    @Cacheable(value = "apiExchange",key="#exchangeRateApiUrl") // Cache the result of this method using the specified cache name
    public Map<String, Object> getApi(String exchangeRateApiUrl) {
        // Make the API call using RestTemplate
        Map<String, Object> response = restTemplate.getForObject(exchangeRateApiUrl, Map.class);
        return response;
    }
}