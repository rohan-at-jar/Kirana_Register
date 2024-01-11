package com.kirana.Kirana_Register.dump;

import com.kirana.Kirana_Register.dump.ExchangeRateResponse;
import com.kirana.Kirana_Register.dump.ExchangeRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Map;

@RestController
public class ExchangeRateController {

    private final ExchangeRateService exchangeRateService;

    @Autowired
    public ExchangeRateController(ExchangeRateService exchangeRateService) {
        this.exchangeRateService = exchangeRateService;
    }

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
