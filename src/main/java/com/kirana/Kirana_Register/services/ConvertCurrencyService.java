package com.kirana.Kirana_Register.services;

import com.kirana.Kirana_Register.model.ExchangeRates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * Service class for converting currency amounts based on exchange rates.
 */
@Service
public class ConvertCurrencyService {

    /**
     * The URL of the exchange rate API.
     */
    @Value("${api.fxratesapi.url}")
    private String exchangeRateApiUrl;

    /**
     * Instance of ExchangeRates used to retrieve the latest rates from the API.
     */
    private ExchangeRates exchangeRate;

    /**
     * RestTemplate for making HTTP requests to the exchange rate API.
     */
    private final RestTemplate restTemplate;

    /**
     * Constructor for initializing ConvertCurrencyService with dependencies.
     *
     * @param restTemplate RestTemplate for making HTTP requests to the exchange rate API.
     * @param exchangeRate Instance of ExchangeRates used to retrieve the latest rates from the API.
     */
    @Autowired
    public ConvertCurrencyService(RestTemplate restTemplate,ExchangeRates exchangeRate) {
        this.exchangeRate = exchangeRate;
        this.restTemplate = restTemplate;
    }

    /**
     * Retrieves the exchange rate for a specified currency from the exchange rate API.
     *
     * @param currency The currency code for which the exchange rate is requested.
     * @return The exchange rate for the specified currency.
     */
    public double getExchangeRate(String currency) {

        try {
            // Call the exchange rate API to get the latest rates
            Map<String, Object> response = exchangeRate.getApi(exchangeRateApiUrl);


            if (response != null && response.containsKey("rates")) {
                Map<String, Double> rates = (Map<String, Double>) response.get("rates");
                return rates != null ? rates.getOrDefault(currency, 1.0) : 1.0;
            } else {
                System.err.println("Invalid API response format");
                return 1.0;
            }
        } catch (Exception e) {
            System.err.println("Error fetching exchange rate: " + e.getMessage());
            return 1.0;
        }
    }

    /**
     * Converts a given amount using a specified exchange rate.
     *
     * @param amount      The amount to be converted.
     * @param exchangeRate The exchange rate to be used for the conversion.
     * @return The converted amount.
     */
    public double convertAmount(double amount, double exchangeRate) {
        // Perform currency conversion
        return amount / exchangeRate;
    }
}
