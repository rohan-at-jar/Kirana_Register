package com.kirana.Kirana_Register.appendix;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Service class for fetching exchange rates from an external API.
 */
@Service
public class ExchangeRateService {

    /**
     * The URL of the external API providing exchange rate data.
     */
    @Value("${api.fxratesapi.url}")
    private String apiUrl;

    /**
     * RestTemplate for making HTTP requests to the external API.
     */
    private final RestTemplate restTemplate;

    /**
     * Constructor for ExchangeRateService.
     *
     * @param restTemplate RestTemplate for making HTTP requests.
     */
    public ExchangeRateService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * Retrieves the latest exchange rates from the external API.
     *
     * @return ExchangeRateResponse containing the exchange rates.
     */
    public ExchangeRateResponse getExchangeRates() {
        return restTemplate.getForObject(apiUrl, ExchangeRateResponse.class);
    }
}
