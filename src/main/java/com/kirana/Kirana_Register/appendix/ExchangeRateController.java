package com.kirana.Kirana_Register.appendix;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.Cacheable;

import java.io.IOException;
import java.util.Map;

/**
 * Controller class for handling HTTP requests related to exchange rates.
 */
@RestController
public class ExchangeRateController {

    /**
     * Service for fetching exchange rates.
     */
    private final ExchangeRateService exchangeRateService;

    /**
     * Constructor for initializing ExchangeRateController with dependencies.
     *
     * @param exchangeRateService Service for fetching exchange rates.
     */
    @Autowired
    public ExchangeRateController(ExchangeRateService exchangeRateService) {
        this.exchangeRateService = exchangeRateService;
    }

    /**
     * Handles HTTP GET request to retrieve the latest exchange rates.
     *
     * @return A map containing the base currency and exchange rates.
     * @throws IOException If an error occurs during the exchange rate retrieval process.
     */
    @GetMapping("/exchange-rates")
    public Map<String, Object> getExchangeRates() throws IOException {
        ExchangeRateResponse response = exchangeRateService.getExchangeRates();

        // Extract values from the response
        String baseCurrency = response.getBase();
        Map<String, Double> rates = response.getRates();

        // Create a result map with base currency and rates map
        Map<String, Object> result = Map.of(
                "baseCurrency", baseCurrency,
                "exchangeRates", rates
        );

        return result;
    }
}
