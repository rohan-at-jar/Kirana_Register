package com.kirana.Kirana_Register.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TransactionRequestDTO {

    @JsonProperty("from")
    private int from;
    @JsonProperty("to")
    private int to;

    public TransactionRequestDTO() {
    }

    @Override
    public String toString() {
        return "TransactionRequestDTO{" +
                "from=" + from +
                ", to=" + to +
                ", currency='" + currency + '\'' +
                ", amount=" + amount +
                ", type='" + type + '\'' +
                '}';
    }

    @JsonProperty("Currency")
    private String currency;
    @JsonProperty("amount")
    private int amount;
    @JsonProperty("type")
    private String type;

    public int getFrom() {
        return from;
    }

    public void setFrom(int from) {
        this.from = from;
    }

    public int getTo() {
        return to;
    }

    public void setTo(int to) {
        this.to = to;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    // Constructors, getters, setters...
}