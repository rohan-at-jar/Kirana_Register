package com.kirana.Kirana_Register.entities;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * Entity class representing a financial transaction.
 * This class is annotated with @Document to specify its storage in MongoDB.
 */
@Document(collection= "transaction")
public class Transaction {
    /**
     * Unique identifier for the transaction.
     */
    private int transaction_id;
    /**
     * Amount of money involved in the transaction.
     */
    private double amount;
    /**
     * Currency used for the transaction.
     */
    private String Currency;
    /**
     * Type of the transaction (e.g., credit, debit).
     */
    private String type;
    /**
     * Account ID of the source of funds.
     */
    private int from;
    /**
     * Account ID of the destination for funds.
     */
    private int to;

    /**
     * Timestamp indicating when the transaction occurred.
     */
    private Date timestamp;

    /**
     * Overridden toString method for better representation of the Transaction object.
     */
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

    /**
     * Default constructor for the Transaction class.
     */
    public Transaction() {
    }
    /**
     * Getter for the transaction timestamp.
     */
    public Date getTimestamp() {
        return timestamp;
    }

    /**
     * Setter for the transaction timestamp.
     */
    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public int getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(int transaction_id) {
        this.transaction_id = transaction_id;
    }

    /**
     * Getter for the amount.
     */
    public double getAmount() {
        return amount;
    }
    /**
     * Setter for the amount.
     */
    public void setAmount(double amount) {
        this.amount = amount;
    }

    /**
     * Getter for the currency.
     */
    public String getCurrency() {
        return Currency;
    }

    /**
     * Setter for the currency.
     */
    public void setCurrency(String Currency) {
        this.Currency = Currency;
    }

    /**
     * Getter for the type of transaction
     */
    public String getType() {
        return type;
    }

    /**
     * Setter for type of transaction
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Getter for the source account id
     */
    public int getFrom() {
        return from;
    }

    /**
     * Setter for the source account id
     */
    public void setFrom(int from) {
        this.from = from;
    }
    /**
     * Getter for the destination account id
     */
    public int getTo() {
        return to;
    }

    /**
     * Setter for the destination account id
     */
    public void setTo(int to) {
        this.to = to;
    }
}
