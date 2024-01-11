package com.kirana.Kirana_Register.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class ConvertCurrencyService {

    @Value("${api.fxratesapi.url}")
    private String exchangeRateApiUrl;

    private final RestTemplate restTemplate;
    @Autowired
    public ConvertCurrencyService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public double getExchangeRate(String currency) {
//        // Call the exchange rate API to get the latest rates
//        Map<String, Double> exchangeRates = restTemplate.getForObject(exchangeRateApiUrl, Map.class);
//
//        // Mock method to get exchange rate (replace with actual implementation)
//        return exchangeRates != null ? exchangeRates.getOrDefault(currency, 1.0) : 1.0;

        try {
            // Call the exchange rate API to get the latest rates
            Map<String, Object> response = restTemplate.getForObject(exchangeRateApiUrl, Map.class);

            if (response != null && response.containsKey("rates")) {
                Map<String, Double> rates = (Map<String, Double>) response.get("rates");
//                System.err.println(currency); dead code
//                System.err.println(rates != null ? rates.getOrDefault(currency, 1.0) : 1.0); dead code
                return rates != null ? rates.getOrDefault(currency, 1.0) : 1.0;
            } else {
                System.err.println("Invalid API response format");
//                System.err.println(response); dead code
                return 1.0;
            }
        } catch (Exception e) {
            System.err.println("Error fetching exchange rate: " + e.getMessage());
            return 1.0;
        }
    }

    public double convertAmount(double amount, double exchangeRate) {
        // Perform currency conversion
        return amount / exchangeRate;
    }
}
