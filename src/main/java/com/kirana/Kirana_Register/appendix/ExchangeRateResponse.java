package com.kirana.Kirana_Register.appendix;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Map;

/**
 * Represents the response structure for exchange rate data.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ExchangeRateResponse {

    /**
     * The base currency for the exchange rates.
     */
    private String base;
    /**
     * The date associated with the exchange rates.
     */
    private String date;
    /**
     * Map containing currency codes as keys and corresponding exchange rates as values.
     */
    private Map<String, Double> rates;

    /**
     * Default constructor for ExchangeRateResponse.
     */
    public ExchangeRateResponse() {
    }

    /**
     * String representation of ExchangeRateResponse.
     *
     * @return A string representation of the ExchangeRateResponse.
     */
    @Override
    public String toString() {
        return "ExchangeRateResponse{" +
                "base='" + base + '\'' +
                ", date='" + date + '\'' +
                ", rates=" + rates +
                '}';
    }

    /**
     * Getter for the base currency.
     *
     * @return The base currency.
     */
    public String getBase() {
        return base;
    }

    /**
     * Setter for the base currency.
     *
     * @param base The base currency to set.
     */
    public void setBase(String base) {
        this.base = base;
    }

    /**
     * Getter for the date associated with the exchange rates.
     *
     * @return The date associated with the exchange rates.
     */
    public String getDate() {
        return date;
    }

    /**
     * Setter for the date associated with the exchange rates.
     *
     * @param date The date to set.
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * Getter for the map of currency codes and exchange rates.
     *
     * @return Map containing currency codes as keys and exchange rates as values.
     */
    public Map<String, Double> getRates() {
        return rates;
    }

    /**
     * Setter for the map of currency codes and exchange rates.
     *
     * @param rates Map containing currency codes as keys and exchange rates as values.
     */
    public void setRates(Map<String, Double> rates) {
        this.rates = rates;
    }

    // getters and setters
}
