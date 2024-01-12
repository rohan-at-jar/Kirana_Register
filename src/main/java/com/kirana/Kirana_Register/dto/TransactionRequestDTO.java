package com.kirana.Kirana_Register.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Data Transfer Object (DTO) representing a transaction request.
 * Contains details such as 'from', 'to', 'currency', 'amount', and 'type'.
 */
public class TransactionRequestDTO {

    /**
     * Identifier of the source entity in the transaction.
     */
    @JsonProperty("from")
    private int from;

    /**
     * Identifier of the target entity in the transaction.
     */
    @JsonProperty("to")
    private int to;

    /**
     * Default constructor for TransactionRequestDTO.
     */
    public TransactionRequestDTO() {
    }

    /**
     * String representation of TransactionRequestDTO.
     */
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
    /**
     * Currency code for the transaction.
     */
    @JsonProperty("Currency")
    private String currency;

    /**
     * Amount involved in the transaction.
     */
    @JsonProperty("amount")
    private int amount;
    /**
     * Type of the transaction (e.g., credit or debit).
     */
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