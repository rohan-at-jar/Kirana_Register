package com.kirana.Kirana_Register.dump;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ExchangeRateService {

    @Value("${api.fxratesapi.url}")
    private String apiUrl;

    private final RestTemplate restTemplate;

    public ExchangeRateService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ExchangeRateResponse getExchangeRates() {
        return restTemplate.getForObject(apiUrl, ExchangeRateResponse.class);
    }
}
