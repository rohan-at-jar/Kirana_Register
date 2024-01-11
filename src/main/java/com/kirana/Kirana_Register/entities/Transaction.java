package com.kirana.Kirana_Register.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection= "transaction")
public class Transaction {
//    @JsonProperty("transaction_id")
    private int transaction_id;
//    @JsonProperty("amount")
    private double amount;

//    @JsonProperty("Currency")
    private String Currency;
//    @JsonProperty("type")
    private String type;
    private int from;
    private int to;

    private Date timestamp;

    @Override
    public String toString() {
        return "Transaction{" +
                "transaction_id=" + transaction_id +
                ", amount=" + amount +
                ", Currency='" + Currency + '\'' +
                ", type='" + type + '\'' +
                ", from=" + from +
                ", to=" + to +
                ", timestamp=" + timestamp +
                '}';
    }

    public Transaction() {
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public int getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(int transaction_id) {
        this.transaction_id = transaction_id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return Currency;
    }

    public void setCurrency(String Currency) {
        this.Currency = Currency;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

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
}
